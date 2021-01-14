package com.bonlimousin.gateway.bff.service;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api.PhotoResourceApiClient;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.config.ApplicationProperties;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;

@Service
public class CowPictureSourceService extends AbstractPictureSourceService<PhotoEntity> {

    private final Logger log = LoggerFactory.getLogger(CowPictureSourceService.class);

    private final Executor taskExecutor;
    private final PhotoResourceApiClient photoResourceApiClient;

    public CowPictureSourceService(ApplicationProperties applicationProperties, Executor taskExecutor, PhotoResourceApiClient photoResourceApiClient) {
        super(applicationProperties.getImageBaseDir() + "/cow", "cow");
        this.taskExecutor = taskExecutor;
        this.photoResourceApiClient = photoResourceApiClient;
    }

    @Override
    public Optional<PictureSourceVO> createPictureSourceVO(PhotoEntity entity, String baseUrl, PictureSize pictureSize) throws MimeTypeException {
        if (pictureSize != PictureSize.ORIGINAL && pictureSize.pixelWidth() > entity.getWidth()) {
            return Optional.empty();
        }

        Integer earTagId = entity.getCattle().getEarTagId();
        String imageExt = getImageExtension(entity.getImageContentType());
        String imageName = getImageName(earTagId, entity.getId(), pictureSize, imageExt);
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

    public Path getOrCreateImagePath(Long pictureId, String name, String baseUrl) throws MimeTypeException, IOException {
        Path imagePath = getImagePath(name);
        if (!Files.exists(imagePath)) {
            PhotoEntity fullPhoto = this.photoResourceApiClient.getPhotoUsingGET(pictureId).getBody();
            for (PictureSize picSize : PictureSize.values()) {
                Optional<PictureSourceVO> opt = createPictureSourceVO(fullPhoto, baseUrl, picSize);
                if (opt.isPresent() && opt.get().getName().equals(name)) {
                    imagePath = createThumbnailOndisk(name, fullPhoto.getImage(), picSize);
                    break;
                }
            }
        }
        return imagePath;
    }

    public void asyncCreateThumbnailsOnDiskIfNotExists(Long pictureId, Map<PictureSize, PictureSourceVO> map) {
        taskExecutor.execute(() -> {
            try {
                createThumbnailsOnDiskIfNotExists(pictureId, map);
            } catch (MimeTypeException | IOException e) {
                log.warn("Async creation of thumbnail on disk failed", e);
            }
        });
    }

    public void createThumbnailsOnDiskIfNotExists(Long pictureId, Map<PictureSize, PictureSourceVO> map) throws MimeTypeException, IOException {
        PhotoEntity fullPhoto = null;
        for (Map.Entry<PictureSize, PictureSourceVO> picEntry : map.entrySet()) {
            PictureSize picSize = picEntry.getKey();
            PictureSourceVO ps = picEntry.getValue();
            if (!Files.exists(getImagePath(ps.getName()))) {
                if (fullPhoto == null) {
                    fullPhoto = this.photoResourceApiClient.getPhotoUsingGET(pictureId).getBody();
                }
                createThumbnailOndisk(ps.getName(), fullPhoto.getImage(), picSize);
            }
        }
    }
}
