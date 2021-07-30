package com.bonlimousin.gateway.bff.service;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api.PhotoResourceApiClient;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.config.ApplicationProperties;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import org.apache.tika.mime.MimeTypeException;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class CowPictureSourceService extends AbstractPictureSourceService<PhotoEntity> {

    private final Logger log = LoggerFactory.getLogger(CowPictureSourceService.class);

    private final PhotoResourceApiClient photoResourceApiClient;

    public CowPictureSourceService(ApplicationProperties applicationProperties, PhotoResourceApiClient photoResourceApiClient) {
        super("cow", applicationProperties);
        this.photoResourceApiClient = photoResourceApiClient;
    }

    @Override
    public Map<PictureSize, PictureSourceVO> createPictureSourceVOs(PhotoEntity entity, String baseUrl) throws MimeTypeException {
        Map<PictureSize, PictureSourceVO> map = new EnumMap<>(PictureSize.class);
        for(PictureSize picSize : PictureSize.values()) {
            createPictureSourceVO(entity, baseUrl, picSize).ifPresent(ps -> map.put(picSize, ps));
        }

        if(this.applicationProperties.getThumbnails().getImageCreationAot()) {
            Supplier<byte[]> orgImageBytesSupplier = createOriginalBytesSupplier(entity);
            this.createThumbnailsIfNotExists(orgImageBytesSupplier, map);
        }

        Date expiration = new Date();
        if(entity.getVisibility() == PhotoEntity.VisibilityEnum.ANONYMOUS) {
            expiration.setTime(Instant.now().plus(7, ChronoUnit.DAYS).toEpochMilli());
        } else {
            expiration.setTime(Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli());
        }
        map.values().forEach(ps -> this.generatePresignedUrl(ps, expiration).ifPresent(ps::setUrl));
        return map;
    }

    @Override
    protected Optional<PictureSourceVO> createPictureSourceVO(PhotoEntity entity, String baseUrl, PictureSize pictureSize) throws MimeTypeException {
        if (pictureSize != PictureSize.ORIGINAL && pictureSize.pixelWidth() > entity.getWidth()) {
            return Optional.empty();
        }

        Integer earTagId = entity.getCattle().getEarTagId();
        String imageExt = this.getImageExtension(entity.getImageContentType());
        String imageName = this.getImageName(earTagId, entity.getId(), pictureSize, imageExt);
        String imageUrl = baseUrl + "/" + imageName;
        double scale = pictureSize != PictureSize.ORIGINAL
            ? pictureSize.pixelWidth().doubleValue() / entity.getWidth().doubleValue()
            : 1;
        int thumbWidth = (int) Math.round(entity.getWidth() * scale);
        int thumbHeight = (int) Math.round(entity.getHeight() * scale);

        PictureSourceVO ps = new PictureSourceVO();
        ps.setContentType(entity.getImageContentType());
        ps.setName(imageName);
        ps.setWidth(thumbWidth);
        ps.setHeight(thumbHeight);
        ps.setUrl(imageUrl);

        return Optional.of(ps);
    }

    @Override
    public InputStream createImageInputStream(PhotoEntity entity, String baseUrl, String name) throws IOException, MimeTypeException {
        return this.createImageInputStream(entity, createOriginalBytesSupplier(entity), baseUrl, name);
    }

    private Supplier<byte[]> createOriginalBytesSupplier(PhotoEntity entity) {
        return () -> {
            ResponseEntity<PhotoEntity> photoResp = this.photoResourceApiClient.getPhotoUsingGET(entity.getId());
            return photoResp.getBody() != null ? photoResp.getBody().getImage() : null;
        };
    }
}
