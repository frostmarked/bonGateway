package com.bonlimousin.gateway.bff.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.stereotype.Service;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.config.ApplicationProperties;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;

@Service
public class CowPictureSourceService extends AbstractPictureSourceService<PhotoEntity> {

	public CowPictureSourceService(ApplicationProperties applicationProperties) {
		super(applicationProperties.getImageBaseDir() + "/cow", "cow");
	}

	@Override
	public Optional<PictureSourceVO> createPictureSourceVO(PhotoEntity entity, PictureSize pictureSize)
			throws IOException, MimeTypeException {
        if(pictureSize.pixelWidth() == null) {
            return Optional.empty();
        }
        ImmutablePair<Integer, Integer> orgWidthHeightPair = getImageWidthAndHeight(entity.getImage());
        if(pictureSize.pixelWidth() > orgWidthHeightPair.getLeft()) {
            return Optional.empty();
        }

        Integer earTagId = entity.getCattle().getEarTagId();
        String imageExt = getImageExtension(entity.getImageContentType());
        String imageName = getImageName(earTagId, entity.getId(), pictureSize, imageExt);
        Path imagePath = storeImageOnDisk(imageName, entity.getImage(), pictureSize);
        ImmutablePair<Integer, Integer> widthHeightPair = getImageWidthAndHeight(imagePath);

		PictureSourceVO ps = new PictureSourceVO();
		ps.setContentType(entity.getImageContentType());
		ps.setName(imageName);
		ps.setWidth(widthHeightPair.getLeft());
		ps.setHeight(widthHeightPair.getRight());

		return Optional.of(ps);
	}
}
