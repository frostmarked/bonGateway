package com.bonlimousin.gateway.bff.delegate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zalando.problem.Status;

import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.bff.mapper.CowVOMapper;
import com.bonlimousin.gateway.bff.mapper.PhotographVOMapper;
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
import com.bonlimousin.gateway.web.api.model.CowVO;
import com.bonlimousin.gateway.web.api.model.PhotographVO;
import com.bonlimousin.gateway.web.problem.AlertProblem;
import com.bonlimousin.gateway.web.problem.AlertProblemSeverity;
import com.bonlimousin.gateway.web.rest.errors.WhileFetchingDataException;

import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;

@Service
public class CowVOResourceDelegateImpl {

//	private static final Logger log = LoggerFactory.getLogger(CowVOResourceDelegateImpl.class);
	
	private final CattleResourceApiClient cattleResourceApiClient;
	private final BovineResourceApiClient bovineResourceApiClient;
	private final PhotoResourceApiClient photoResourceApiClient;

	public CowVOResourceDelegateImpl(CattleResourceApiClient cattleResourceApiClient,
			BovineResourceApiClient bovineResourceApiClient,
			PhotoResourceApiClient photoResourceApiClient) {
		super();
		this.cattleResourceApiClient = cattleResourceApiClient;
		this.bovineResourceApiClient = bovineResourceApiClient;
		this.photoResourceApiClient = photoResourceApiClient;
	}

	public ResponseEntity<List<CowVO>> findCowVOs(Integer page, Integer size, List<String> sort, Integer linageIdEquals,
			OffsetDateTime birthDateGreaterThan, OffsetDateTime birthDateLessThan, 
			String genderEquals, 
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
		List<Integer> cattleIds = cattleIdMap.entrySet().stream().map(ent -> ent.getKey()).collect(Collectors.toList());
		if(cattleIds.size() == 0) {
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
		if(birthDateGreaterThan != null) {
			birthDateFilter.setGreaterThan(birthDateGreaterThan.toInstant());
		}
		if(birthDateLessThan != null) {
			birthDateFilter.setLessThan(birthDateLessThan.toInstant());
		}
		bovineCriteria.setBirthDate(birthDateFilter);
		
		GenderFilter genderFilter = new GenderFilter();
		if(genderEquals != null) {
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
		if(genderEquals != null) {
			try {
				List<HornStatus> hornStatusList = new ArrayList<>();
				for(String hornStatus : hornStatusIn) {
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

	public ResponseEntity<CowVO> getCowVO(Long cattleId) {
		CattleCriteria cattleCriteria = new CattleCriteria();
		cattleCriteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		cattleCriteria.setEarTagId((IntegerFilter) new IntegerFilter().setEquals(cattleId.intValue()));
		ResponseEntity<List<CattleEntity>> cattleResponse = this.cattleResourceApiClient
				.getAllCattlesUsingGET(cattleCriteria, 0, 1, null);
		if (cattleResponse.getBody().size() == 0) {
			throw new WhileFetchingDataException(new AlertProblem("CowVO does not exist", Status.NOT_FOUND, AlertProblemSeverity.WARNING, "entitynotfound", String.valueOf(cattleId)));
		}
		CattleEntity cattleEntity = cattleResponse.getBody().get(0);

		BovineCriteria bovineCriteria = new BovineCriteria();
		bovineCriteria.setEarTagId((IntegerFilter) new IntegerFilter().setEquals(cattleId.intValue()));
		ResponseEntity<List<BovineEntity>> bovineResponse = this.bovineResourceApiClient
				.getAllBovinesUsingGET(bovineCriteria, 0, 1, null);
		if (bovineResponse.getBody().size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		BovineEntity bovineEntity = bovineResponse.getBody().get(0);

		CowVO vo = CowVOMapper.INSTANCE.entitiesToCowVO(cattleEntity.getMatrilineality(), cattleEntity, bovineEntity);

		return ResponseEntity.ok(vo);
	}
	
	public ResponseEntity<List<PhotographVO>> getAllPhotographVOsByCow(Long cattleId, Integer page, Integer size, List<String> sort) {		
		CattleCriteria cattleCriteria = new CattleCriteria();
		cattleCriteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		cattleCriteria.setEarTagId((IntegerFilter) new IntegerFilter().setEquals(cattleId.intValue()));
		ResponseEntity<List<CattleEntity>> cattleResponse = this.cattleResourceApiClient
				.getAllCattlesUsingGET(cattleCriteria, 0, 1, null);
		if(cattleResponse.getBody().size() == 0) {
			return BFFUtil.createResponse(Collections.emptyList(), page, size, sort, 0);
		}
		Long cId = cattleResponse.getBody().get(0).getId();
		
		PhotoCriteria criteria = new PhotoCriteria();
		criteria.setCattleId((LongFilter) new LongFilter().setEquals(cId));
		ResponseEntity<List<PhotoEntity>> response = this.photoResourceApiClient.getAllPhotosUsingGET(criteria, page, size, sort);
		List<PhotoEntity> photoEntities = response.getBody();
		long totalCount = BFFUtil.extractTotalCount(response);
				
		List<PhotographVO> list = photoEntities.stream().map(entity -> PhotographVOMapper.INSTANCE.photoEntityToPhotographVO(entity))
				.collect(Collectors.toList());
		return BFFUtil.createResponse(list, page, size, sort, totalCount);
	}
}
