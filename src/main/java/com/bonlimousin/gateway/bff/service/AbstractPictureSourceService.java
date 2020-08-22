package com.bonlimousin.gateway.bff.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import com.bonlimousin.gateway.web.api.model.PictureSourceVO;

import net.coobird.thumbnailator.Thumbnails;

public abstract class AbstractPictureSourceService<T> {

	private final String imagePrefix;
	private final String imageBaseDir;

	public AbstractPictureSourceService(String imageBaseDir, String imagePrefix) {
		super();
		this.imageBaseDir = imageBaseDir;
		this.imagePrefix = imagePrefix;
	}

	// Bootstrap max container widths
	public enum PictureSize {
		ORIGINAL(null), SMALL(540), MEDIUM(720), LARGE(960), XL(1140);

		private final Integer pixelWidth;

		private PictureSize(Integer pixelWidth) {
			this.pixelWidth = pixelWidth;
		}

		public Integer pixelWidth() {
			return pixelWidth;
		}
	}

	public List<PictureSourceVO> createPictureSourceVOs(T entity) throws MimeTypeException, IOException {
		List<PictureSourceVO> list = new ArrayList<>();
		for (PictureSize picSize : PictureSize.values()) {
			createPictureSourceVO(entity, picSize).ifPresent(list::add);			
		}
		return list;
	}

	public abstract Optional<PictureSourceVO> createPictureSourceVO(T entity, PictureSize pictureSize)
			throws IOException, MimeTypeException;

	public String getImageExtension(String contentType) throws MimeTypeException {
		String imageExt = MimeTypes.getDefaultMimeTypes().forName(contentType).getExtension();
		if (StringUtils.trimToEmpty(imageExt).isBlank()) {
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
		return Paths.get(imageBaseDir, imageName);
	}

	public Path getImagePath(String imageName, T entity) throws MimeTypeException, IOException {
		Path imagePath = Paths.get(imageBaseDir, imageName);
		if (Files.exists(imagePath)) {
			return imagePath;
		}
		createPictureSourceVOs(entity);
		return imagePath;
	}

	public ImmutablePair<Integer, Integer> getImageWidthAndHeight(Path imagePath) throws IOException {
		BufferedImage bimg = ImageIO.read(imagePath.toFile());
		return new ImmutablePair<>(bimg.getWidth(), bimg.getHeight());
	}

	public Path storeImageOnDisk(String imageName, byte[] image, PictureSize pictureSize) throws IOException {
		Path imageBasePath = Paths.get(imageBaseDir);
		if (!Files.exists(imageBasePath)) {
			Files.createDirectories(imageBasePath);
		}

		Path path = Paths.get(imageBaseDir, imageName);
		if (!Files.exists(path)) {
			try (ByteArrayInputStream bais = new ByteArrayInputStream(image)) {
				if (pictureSize.pixelWidth() != null) {
					Thumbnails.of(bais).width(pictureSize.pixelWidth()).toFile(path.toFile());
				} else {
					Thumbnails.of(bais).scale(1).toFile(path.toFile());
				}
			}
		}
		return path;
	}
}
