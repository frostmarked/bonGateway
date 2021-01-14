package com.bonlimousin.gateway.bff.service;

import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;

public abstract class AbstractPictureSourceService<T> {

	private final String imagePrefix;
	private final String imageDir;

	protected AbstractPictureSourceService(String imageDir, String imagePrefix) {
		super();
		this.imageDir = imageDir;
		this.imagePrefix = imagePrefix;
	}

	// Bootstrap max container widths
	public enum PictureSize {
		ORIGINAL(null), SMALL(540), MEDIUM(720), LARGE(960), XL(1140);

		private final Integer pixelWidth;

		PictureSize(Integer pixelWidth) {
			this.pixelWidth = pixelWidth;
		}

		public Integer pixelWidth() {
			return pixelWidth;
		}
	}

    public Map<PictureSize, PictureSourceVO> createPictureSourceVOs(T entity, String baseUrl) throws MimeTypeException {
        Map<PictureSize, PictureSourceVO> map = new HashMap<>();
        for(PictureSize picSize : PictureSize.values()) {
            createPictureSourceVO(entity, baseUrl, picSize).ifPresent(ps -> map.put(picSize, ps));
        }
        return map;
    }

	public abstract Optional<PictureSourceVO> createPictureSourceVO(T entity, String baseUrl, PictureSize pictureSize) throws MimeTypeException;

    public String getImageDir() {
        return imageDir;
    }

    public String getImageExtension(String contentType) throws MimeTypeException {
		String imageExt = MimeTypes.getDefaultMimeTypes().forName(contentType).getExtension();
		if (StringUtils.trimToEmpty(imageExt).isEmpty()) {
			throw new MimeTypeException("Unknow file-extension for content-type " + contentType);
		}
		return imageExt;
	}

	public String getImageName(long domainId, long pictureId, PictureSize pictureSize, String imageExt) {
		return pictureSize.pixelWidth() != null
				? MessageFormat.format("{0}{1}_{2}_{3}w{4}", imagePrefix, String.valueOf(domainId),
						String.valueOf(pictureId), String.valueOf(pictureSize.pixelWidth()), imageExt)
				: MessageFormat.format("{0}{1}_{2}{3}", imagePrefix, String.valueOf(domainId),
						String.valueOf(pictureId), imageExt);

	}

	public List<String> getImageNames(long domainId, long pictureId, String contentType) throws MimeTypeException {
		String imageExt = getImageExtension(contentType);
		List<String> list = new ArrayList<>();
		for (PictureSize pictureSize : PictureSize.values()) {
			list.add(getImageName(domainId, pictureId, pictureSize, imageExt));
		}
		return list;
	}

	public Path getImagePath(String imageName) {
		return Paths.get(imageDir, imageName);
	}

    public Path createThumbnailOndisk(String imageName, byte[] image, PictureSize pictureSize) throws IOException {
        Path path = Paths.get(imageDir, imageName);
        Path imageBasePath = Paths.get(imageDir);
        if (!Files.exists(imageBasePath)) {
            Files.createDirectories(imageBasePath);
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(image)) {
            if (pictureSize.pixelWidth() != null) {
                Thumbnails.of(bais).width(pictureSize.pixelWidth()).toFile(path.toFile());
            } else {
                Thumbnails.of(bais).scale(1).toFile(path.toFile());
            }
        }
        return path;
    }
}
