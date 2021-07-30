package com.bonlimousin.gateway.bff.delegate;

import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.bff.mapper.CowVOContextParentMapper;
import com.bonlimousin.gateway.bff.mapper.CowVOMapper;
import com.bonlimousin.gateway.bff.mapper.PictureVOMapper;
import com.bonlimousin.gateway.bff.service.AbstractPictureSourceService.PictureSize;
import com.bonlimousin.gateway.bff.service.CowPictureSourceService;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api.CattleResourceApiClient;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api.PhotoResourceApiClient;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.querymap.CattleCriteria;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.querymap.PhotoCriteria;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api.BovineResourceApiClient;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.querymap.BovineCriteria;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.querymap.BovineCriteria.GenderFilter;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.querymap.BovineCriteria.HornStatusFilter;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.querymap.Gender;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.querymap.HornStatus;
import com.bonlimousin.gateway.security.AuthoritiesConstants;
import com.bonlimousin.gateway.security.SecurityUtils;
import com.bonlimousin.gateway.web.api.model.CowVO;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import com.bonlimousin.gateway.web.api.model.PictureVO;
import com.bonlimousin.gateway.web.problem.AlertProblem;
import com.bonlimousin.gateway.web.problem.AlertProblemSeverity;
import com.bonlimousin.gateway.web.rest.errors.WhileFetchingDataException;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zalando.problem.Status;

import javax.validation.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CowVOResourceDelegateImpl {

    private final Logger log = LoggerFactory.getLogger(CowVOResourceDelegateImpl.class);

    private final CowPictureSourceService cowPictureSourceService;
    private final CattleResourceApiClient cattleResourceApiClient;
    private final BovineResourceApiClient bovineResourceApiClient;
    private final PhotoResourceApiClient photoResourceApiClient;

    public CowVOResourceDelegateImpl(CowPictureSourceService pictureSourceVOHelper,
                                     CattleResourceApiClient cattleResourceApiClient,
                                     BovineResourceApiClient bovineResourceApiClient,
                                     PhotoResourceApiClient photoResourceApiClient) {
        super();
        this.cowPictureSourceService = pictureSourceVOHelper;
        this.cattleResourceApiClient = cattleResourceApiClient;
        this.bovineResourceApiClient = bovineResourceApiClient;
        this.photoResourceApiClient = photoResourceApiClient;
    }

    public ResponseEntity<List<CowVO>> findCowVOs(Integer page, Integer size, List<String> sort, String context,
                                                  Integer linageIdEquals, OffsetDateTime birthDateGreaterThan, OffsetDateTime birthDateLessThan, String genderEquals,
                                                  List<String> hornStatusIn, Integer matriIdEquals, Integer patriIdEquals,
                                                  Integer weight0GreaterThan, Integer weight0LessThan, Boolean weight0Specified,
                                                  Integer weight200GreaterThan, Integer weight200LessThan, Boolean weight200Specified,
                                                  Integer weight365GreaterThan, Integer weight365LessThan, Boolean weight365Specified) {

        CattleCriteria cattleCriteria = new CattleCriteria();
        cattleCriteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
        if (linageIdEquals != null) {
            cattleCriteria.setMatrilinealityId((LongFilter) new LongFilter().setEquals(linageIdEquals.longValue()));
        }
        ResponseEntity<List<CattleEntity>> cattleResponse = this.cattleResourceApiClient
            .getAllCattlesUsingGET(cattleCriteria, 0, 1000, null);
        Map<Integer, CattleEntity> cattleIdMap = cattleResponse.getBody().stream()
            .collect(Collectors.toMap(CattleEntity::getEarTagId, Function.identity()));
        List<Integer> cattleIds = cattleIdMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        if (cattleIds.isEmpty()) {
            return BFFUtil.createResponse(Collections.emptyList(), page, size, sort, 0);
        }

        BovineCriteria bovineCriteria = createBovineCriteria(
            cattleIds, birthDateGreaterThan, birthDateLessThan,
            genderEquals, hornStatusIn,
            matriIdEquals, patriIdEquals,
            weight0GreaterThan, weight0LessThan,
            weight0Specified, weight200GreaterThan, weight200LessThan, weight200Specified, weight365GreaterThan,
            weight365LessThan, weight365Specified);
        ResponseEntity<List<BovineEntity>> bovineResponse = this.bovineResourceApiClient
            .getAllBovinesUsingGET(bovineCriteria, page, size, sort);
        long totalCount = BFFUtil.extractTotalCount(bovineResponse);

        List<CowVO> vos = new ArrayList<>();
        for (BovineEntity bovineEntity : bovineResponse.getBody()) {
            CattleEntity cattleEntity = cattleIdMap.get(bovineEntity.getEarTagId());
            MatrilinealityEntity matrilinealityEntity = cattleEntity.getMatrilineality();
            CowVO vo = CowVOMapper.INSTANCE.entitiesToCowVO(matrilinealityEntity, cattleEntity, bovineEntity);
            vos.add(vo);
        }

        return BFFUtil.createResponse(vos, page, size, sort, totalCount);
    }

    private BovineCriteria createBovineCriteria(List<Integer> cattleIdIn,
                                                OffsetDateTime birthDateGreaterThan, OffsetDateTime birthDateLessThan,
                                                String genderEquals, List<String> hornStatusIn,
                                                Integer matriIdEquals, Integer patriIdEquals,
                                                Integer weight0GreaterThan, Integer weight0LessThan, Boolean weight0Specified,
                                                Integer weight200GreaterThan, Integer weight200LessThan, Boolean weight200Specified,
                                                Integer weight365GreaterThan, Integer weight365LessThan, Boolean weight365Specified) {
        BovineCriteria bovineCriteria = new BovineCriteria();

        bovineCriteria.setEarTagId((IntegerFilter) new IntegerFilter().setIn(cattleIdIn));

        InstantFilter birthDateFilter = new InstantFilter();
        if (birthDateGreaterThan != null) {
            birthDateFilter.setGreaterThan(birthDateGreaterThan.toInstant());
        }
        if (birthDateLessThan != null) {
            birthDateFilter.setLessThan(birthDateLessThan.toInstant());
        }
        bovineCriteria.setBirthDate(birthDateFilter);

        GenderFilter genderFilter = new GenderFilter();
        if (genderEquals != null) {
            try {
                Gender gender = Gender.valueOf(genderEquals);
                genderFilter.setEquals(gender);
            } catch (IllegalArgumentException e) {
                // its a validation error and should be treated that way. throw something
                throw new ValidationException("gender.equals is not valid");
            }
        }
        bovineCriteria.setGender(genderFilter);

        HornStatusFilter hornStatusFilter = new HornStatusFilter();
        if (hornStatusIn != null && !hornStatusIn.isEmpty()) {
            try {
                List<HornStatus> hornStatusList = new ArrayList<>();
                for (String hornStatus : hornStatusIn) {
                    hornStatusList.add(HornStatus.valueOf(hornStatus));
                }
                hornStatusFilter.setIn(hornStatusList);
            } catch (IllegalArgumentException e) {
                // its a validation error and should be treated that way. throw something
                throw new ValidationException("hornStatus.in is not valid");
            }
        }
        bovineCriteria.setHornStatus(hornStatusFilter);

        bovineCriteria.setMatriId((IntegerFilter) new IntegerFilter().setEquals(matriIdEquals));

        bovineCriteria.setPatriId((IntegerFilter) new IntegerFilter().setEquals(patriIdEquals));

        IntegerFilter weight0Filter = (IntegerFilter) new IntegerFilter()
            .setGreaterThan(weight0GreaterThan)
            .setLessThan(weight0LessThan)
            .setSpecified(weight0Specified);
        bovineCriteria.setWeight0(weight0Filter);

        IntegerFilter weight200Filter = (IntegerFilter) new IntegerFilter()
            .setGreaterThan(weight200GreaterThan)
            .setLessThan(weight200LessThan)
            .setSpecified(weight200Specified);
        bovineCriteria.setWeight200(weight200Filter);

        IntegerFilter weight365Filter = (IntegerFilter) new IntegerFilter()
            .setGreaterThan(weight365GreaterThan)
            .setLessThan(weight365LessThan)
            .setSpecified(weight365Specified);
        bovineCriteria.setWeight365(weight365Filter);

        return bovineCriteria;
    }

    public ResponseEntity<CowVO> getCowVO(Long earTagId, String context) {
        ResponseEntity<List<CattleEntity>> cattleResponse = fetchCattleByEarTagIdAndRole(earTagId, context);
        if (cattleResponse.getBody().isEmpty()) {
            throw new WhileFetchingDataException(new AlertProblem("CowVO does not exist", Status.NOT_FOUND, AlertProblemSeverity.WARNING, "entitynotfound", String.valueOf(earTagId)));
        }
        CattleEntity cattleEntity = cattleResponse.getBody().get(0);
        if (StringUtils.trimToEmpty(context).equals("PARENT")) {
            CowVO vo = CowVOContextParentMapper.INSTANCE.entitiesToCowVO(cattleEntity);
            return ResponseEntity.ok(vo);
        }

        BovineCriteria bovineCriteria = new BovineCriteria();
        bovineCriteria.setEarTagId((IntegerFilter) new IntegerFilter().setEquals(earTagId.intValue()));
        ResponseEntity<List<BovineEntity>> bovineResponse = this.bovineResourceApiClient
            .getAllBovinesUsingGET(bovineCriteria, 0, 1, null);
        if (bovineResponse.getBody().isEmpty()) {
            throw new WhileFetchingDataException(new AlertProblem("CowVO does not exist", Status.NOT_FOUND, AlertProblemSeverity.WARNING, "entitynotfound", String.valueOf(earTagId)));
        }
        BovineEntity bovineEntity = bovineResponse.getBody().get(0);

        CowVO vo = CowVOMapper.INSTANCE.entitiesToCowVO(cattleEntity.getMatrilineality(), cattleEntity, bovineEntity);
        return ResponseEntity.ok(vo);
    }

    public ResponseEntity<List<PictureVO>> getAllPictureVOsByCow(Long earTagId, Integer page, Integer size, List<String> sort) {
        ResponseEntity<List<PhotoEntity>> response = fetchPhotosByEarTagId(earTagId, page, size, sort);
        List<PictureVO> list = new ArrayList<>();
        for (PhotoEntity entity : response.getBody()) {
            PictureVO vo = getPictureVO(earTagId, entity);
            list.add(vo);
        }
        long totalCount = BFFUtil.extractTotalCount(response);
        return BFFUtil.createResponse(list, page, size, sort, totalCount);
    }

    private PictureVO getPictureVO(Long earTagId, PhotoEntity entity) {
        PictureVO vo = PictureVOMapper.INSTANCE.photoEntityToPictureVO(entity);
        String baseUrl = getCowImageBaseUrl(earTagId, entity.getId());
        try {
            Map<PictureSize, PictureSourceVO> map = cowPictureSourceService.createPictureSourceVOs(entity, baseUrl);
            vo.setSources(new ArrayList<>(map.values()));
        } catch (MimeTypeException e) {
            log.warn("Failed to extract image extension", e);
        }
        return vo;
    }

    private String getCowImageBaseUrl(long earTagId, long pictureId) {
        return MessageFormat.format("/api/public/cows/{0}/pictures/{1}",
            String.valueOf(earTagId), String.valueOf(pictureId));
    }

    public ResponseEntity<Resource> getImageForCow(Long earTagId, Long pictureId, String name) {
        ResponseEntity<List<CattleEntity>> cattleResponse = this.fetchCattleByEarTagIdAndRole(earTagId, null);
        if (cattleResponse.getBody().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CattleEntity cattleEntity = cattleResponse.getBody().get(0);

        PhotoCriteria criteria = new PhotoCriteria();
        criteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
        criteria.setCattleId((LongFilter) new LongFilter().setEquals(cattleEntity.getId()));
        criteria.setId((LongFilter) new LongFilter().setEquals(pictureId));
        ResponseEntity<List<PhotoEntity>> photoResponse = this.photoResourceApiClient.getAllPhotosUsingGET(criteria, 0, 1, null);
        if (photoResponse.getBody().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PhotoEntity photoEntity = photoResponse.getBody().get(0);

        try {
            List<String> availableImageNames = cowPictureSourceService.getImageNames(earTagId, pictureId, photoEntity.getImageContentType());
            if (!availableImageNames.contains(name)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            String baseUrl = getCowImageBaseUrl(earTagId, photoEntity.getId());
            CacheControl cacheControl = getImageCacheControl(cattleEntity, photoEntity);
            return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(photoEntity.getImageContentType()))
                .cacheControl(cacheControl)
                .body(new InputStreamResource(cowPictureSourceService.createImageInputStream(photoEntity, baseUrl, name)));
        } catch (IOException | MimeTypeException e) {
            log.warn("Image with name {} for cow {} and id {} not found", name, earTagId, pictureId, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private CacheControl getImageCacheControl(CattleEntity cattleEntity, PhotoEntity photoEntity) {
        boolean isAnonAccessCattle = cattleEntity.getVisibility() == CattleEntity.VisibilityEnum.ANONYMOUS;
        boolean isAnonAccessPhoto = photoEntity.getVisibility() == PhotoEntity.VisibilityEnum.ANONYMOUS;
        if (isAnonAccessCattle && isAnonAccessPhoto) {
            return CacheControl.maxAge(Duration.ofDays(90)).cachePublic();
        } else if (isAnonAccessPhoto) {
            return CacheControl.maxAge(Duration.ofDays(30)).cachePublic().proxyRevalidate();
        } else if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            return CacheControl.maxAge(Duration.ofHours(1)).cachePrivate().mustRevalidate();
        } else if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
            return CacheControl.maxAge(Duration.ofDays(7)).cachePrivate().mustRevalidate();
        }
        return CacheControl.empty();
    }

    private ResponseEntity<List<PhotoEntity>> fetchPhotosByEarTagId(Long earTagId, Integer page, Integer size, List<String> sort) {
        ResponseEntity<List<CattleEntity>> cattleResponse = this.fetchCattleByEarTagIdAndRole(earTagId, null);
        if (cattleResponse.getBody().isEmpty()) {
            return BFFUtil.createResponse(Collections.emptyList(), page, size, sort, 0);
        }
        Long cId = cattleResponse.getBody().get(0).getId();
        return this.fetchPhotosByCattleId(cId, page, size, sort);
    }

    private ResponseEntity<List<CattleEntity>> fetchCattleByEarTagIdAndRole(Long earTagId, String context) {
        CattleCriteria cattleCriteria = new CattleCriteria();
        if (!StringUtils.trimToEmpty(context).equals("PARENT")) {
            cattleCriteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
        }
        cattleCriteria.setEarTagId((IntegerFilter) new IntegerFilter().setEquals(earTagId.intValue()));
        return this.cattleResourceApiClient
            .getAllCattlesUsingGET(cattleCriteria, 0, 1, null);
    }

    private ResponseEntity<List<PhotoEntity>> fetchPhotosByCattleId(Long cattleId, Integer page, Integer size, List<String> sort) {
        PhotoCriteria criteria = new PhotoCriteria();
        criteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
        criteria.setCattleId((LongFilter) new LongFilter().setEquals(cattleId));
        return this.photoResourceApiClient.getAllPhotosUsingGET(criteria, page, size, sort);
    }
}
