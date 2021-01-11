package com.bonlimousin.gateway.bff.service;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.config.ApplicationProperties;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executor;

@Service
public class CowPictureSourceService extends AbstractPictureSourceService<PhotoEntity> {

    private final Logger log = LoggerFactory.getLogger(CowPictureSourceService.class);

    private final Executor taskExecutor;

	public CowPictureSourceService(ApplicationProperties applicationProperties, Executor taskExecutor) {
		super(applicationProperties.getImageBaseDir() + "/cow", "cow");
		this.taskExecutor = taskExecutor;
	}

	@Override
	public Optional<PictureSourceVO> createPictureSourceVO(PhotoEntity entity, PictureSize pictureSize)
			throws IOException, MimeTypeException {
        Optional<BufferedImage> orgBI = extractImage(entity.getImage());
        if(!orgBI.isPresent()) {
            return Optional.empty();
        }
        if(pictureSize != PictureSize.ORIGINAL && pictureSize.pixelWidth() > orgBI.get().getWidth()) {
            return Optional.empty();
        }

        Integer earTagId = entity.getCattle().getEarTagId();
        String imageExt = getImageExtension(entity.getImageContentType());
        String imageName = getImageName(earTagId, entity.getId(), pictureSize, imageExt);
        BufferedImage bi = createThumbnail(orgBI.get(), pictureSize);

        taskExecutor.execute(() -> {
            try {
                storeImageOnDiskIfNotExists(imageName, bi);
            } catch (IOException e) {
                log.warn("Failed to store thumbnail", e);
            }
        });

		PictureSourceVO ps = new PictureSourceVO();
		ps.setContentType(entity.getImageContentType());
		ps.setName(imageName);
		ps.setWidth(bi.getWidth());
		ps.setHeight(bi.getHeight());

		return Optional.of(ps);
	}
}
