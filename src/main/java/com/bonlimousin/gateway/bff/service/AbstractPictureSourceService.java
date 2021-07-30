package com.bonlimousin.gateway.bff.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.bonlimousin.gateway.config.ApplicationProperties;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Supplier;

public abstract class AbstractPictureSourceService<T> {

    private final Logger log = LoggerFactory.getLogger(AbstractPictureSourceService.class);

    protected final ApplicationProperties applicationProperties;
	private final String imagePrefix;
	private final String imageDir;
	private final String imageBucketName;
    private final AmazonS3 s3Client;
    private final TransferManager s3tm;

    protected AbstractPictureSourceService(String imagePrefix, ApplicationProperties applicationProperties) {
        this.imagePrefix = imagePrefix;
        this.imageDir = applicationProperties.getThumbnails().getImageBaseDir() + "/" + imagePrefix;
        this.applicationProperties = applicationProperties;
        if(this.applicationProperties.getThumbnails().getImageStorage() == ApplicationProperties.ImageStorage.AWS) {
            this.imageBucketName = applicationProperties.getAws().getImageBucketName();
            this.s3Client = createS3Client(applicationProperties);
            this.s3tm = createS3TransferManager(s3Client);
        } else {
            this.imageBucketName = null;
            this.s3Client = null;
            this.s3tm = null;
        }
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

    private AmazonS3 createS3Client(ApplicationProperties applicationProperties) {
        String accessKey = applicationProperties.getAws().getAccessKey();
        String secretKey = applicationProperties.getAws().getSecretKey();
        String regionName = applicationProperties.getAws().getRegionName();
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(regionName)
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();
    }

    private TransferManager createS3TransferManager(AmazonS3 s3Client) {
        return TransferManagerBuilder.standard()
            .withS3Client(s3Client)
            .build();
    }

    public abstract Map<PictureSize, PictureSourceVO> createPictureSourceVOs(T entity, String baseUrl) throws MimeTypeException;

    protected abstract Optional<PictureSourceVO> createPictureSourceVO(T entity, String baseUrl, PictureSize pictureSize) throws MimeTypeException;

    public abstract InputStream createImageInputStream(T entity, String baseUrl, String name) throws IOException, MimeTypeException;

    public String getImageExtension(String contentType) throws MimeTypeException {
        String imageExt = MimeTypes.getDefaultMimeTypes().forName(contentType).getExtension();
        if (StringUtils.trimToEmpty(imageExt).isEmpty()) {
            throw new MimeTypeException("Unknow file-extension for content-type " + contentType);
        }
        return imageExt;
    }

    public String getImageName(long domainId, long pictureId, PictureSize pictureSize, String imageExt) {
        return pictureSize.pixelWidth() != null
            ? MessageFormat.format("{0}{1}_{2}_{3}w{4}", this.imagePrefix, String.valueOf(domainId),
            String.valueOf(pictureId), String.valueOf(pictureSize.pixelWidth()), imageExt)
            : MessageFormat.format("{0}{1}_{2}{3}", this.imagePrefix, String.valueOf(domainId),
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

    public byte[] createThumbnail(byte[] image, PictureSize pictureSize) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(image); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            if (pictureSize.pixelWidth() != null) {
                Thumbnails.of(bais).width(pictureSize.pixelWidth()).toOutputStream(os);
            } else {
                Thumbnails.of(bais).scale(1).toOutputStream(os);
            }
            return os.toByteArray();
        }
    }

    public Optional<String> generatePresignedUrl(PictureSourceVO ps, Date expiration) {
        if(this.applicationProperties.getThumbnails().getImageStorage() == ApplicationProperties.ImageStorage.AWS) {
            return this.generatePresignedUrlForS3(ps.getUrl(), expiration);
        } else {
            return Optional.of(ps.getUrl());
        }
    }

    public boolean doesThumbnailExist(String imageDir, String baseUrl, String imageName) {
        if(this.applicationProperties.getThumbnails().getImageStorage() == ApplicationProperties.ImageStorage.AWS) {
            return doesThumbnailExistOnS3(baseUrl, imageName);
        } else {
            return doesThumbnailExistOnDisk(imageDir, imageName);
        }
    }

    public void createThumbnailsIfNotExists(Supplier<byte[]> originalImageBytesSupplier, Map<PictureSize, PictureSourceVO> map) {
        if(this.applicationProperties.getThumbnails().getImageStorage() == ApplicationProperties.ImageStorage.AWS) {
            createThumbnailsOnS3IfNotExists(originalImageBytesSupplier, map);
        } else {
            createThumbnailsOnDiskIfNotExists(originalImageBytesSupplier, map, this.imageDir);
        }
    }

    public InputStream createImageInputStream(T entity, Supplier<byte[]> originalImageBytesSupplier, String baseUrl, String name) throws IOException, MimeTypeException {
        if(this.applicationProperties.getThumbnails().getImageStorage() == ApplicationProperties.ImageStorage.AWS) {
            return this.createImageInputStreamFromS3(entity, originalImageBytesSupplier, baseUrl, name);
        } else {
            return this.createImageInputStreamFromDiskPath(entity, originalImageBytesSupplier, baseUrl, this.imageDir, name);
        }
    }

    // Disk
    protected Path getImagePath(String imageDir, String imageName) {
        return Paths.get(imageDir, imageName);
    }

    protected boolean doesThumbnailExistOnDisk(String imageDir, String imageName) {
        return Files.exists(getImagePath(imageDir, imageName));
    }

    protected InputStream createImageInputStreamFromDiskPath(T entity, Supplier<byte[]> originalImageBytesSupplier, String baseUrl, String imageDir, String name) throws IOException, MimeTypeException {
        Path imagePath = getImagePath(imageDir, name);
        if (Files.exists(imagePath)) {
            return new FileInputStream(imagePath.toFile());
        } else {
            for (PictureSize picSize : PictureSize.values()) {
                Optional<PictureSourceVO> opt = createPictureSourceVO(entity, baseUrl, picSize);
                if (opt.isPresent() && opt.get().getName().equals(name)) {
                    imagePath = storeThumbnailOnDisk(this.imageDir, name, originalImageBytesSupplier.get(), picSize);
                    return new FileInputStream(imagePath.toFile());
                }
            }
        }
        throw new IOException("Image not found");
    }

    protected List<String> createThumbnailsOnDiskIfNotExists(Supplier<byte[]> originalImageBytesSupplier, Map<PictureSize, PictureSourceVO> map, String imageDir) {
        List<String> names = new ArrayList<>();
        byte[] imageBytes = null;
        for (Map.Entry<PictureSize, PictureSourceVO> picEntry : map.entrySet()) {
            PictureSize picSize = picEntry.getKey();
            PictureSourceVO ps = picEntry.getValue();
            if (!doesThumbnailExistOnDisk(imageDir, ps.getName())) {
                if (imageBytes == null) {
                    imageBytes = originalImageBytesSupplier.get();
                    if(imageBytes == null) {
                        log.warn("Original image is missing for thumbnail {}", ps.getName());
                        return names;
                    }
                }
                try {
                    storeThumbnailOnDisk(imageDir, ps.getName(), imageBytes, picSize);
                    names.add(ps.getName());
                } catch (IOException e) {
                    log.warn("Failed to store image {} on disk", ps.getName(), e);
                }
            } else {
                names.add(ps.getName());
            }
        }
        return names;
    }

    protected Path storeThumbnailOnDisk(String imageDir, String imageName, byte[] image, PictureSize pictureSize) throws IOException {
        Path path = Paths.get(imageDir, imageName);
        Path imageBasePath = Paths.get(imageDir);
        if (!Files.exists(imageBasePath)) {
            Files.createDirectories(imageBasePath);
        }

        byte[] thumbnail = createThumbnail(image, pictureSize);
        Files.write(path, thumbnail);
        return path;
    }

    // S3
    protected InputStream createImageInputStreamFromS3(T entity, Supplier<byte[]> originalImageBytesSupplier, String baseUrl, String name) throws IOException, MimeTypeException {
        throw new UnsupportedOperationException("Get image directly from S3...");
    }

    protected List<String> createThumbnailsOnS3IfNotExists(Supplier<byte[]> originalImageBytesSupplier, Map<PictureSize, PictureSourceVO> map) {
        List<String> names = new ArrayList<>();
        byte[] imageBytes = null;
        for (Map.Entry<PictureSize, PictureSourceVO> picEntry : map.entrySet()) {
            PictureSize pictureSize = picEntry.getKey();
            PictureSourceVO ps = picEntry.getValue();
            String keyName = ps.getUrl();
            boolean doesPhotoExist = s3Client.doesObjectExist(this.imageBucketName, keyName);
            if (!doesPhotoExist) {
                if (imageBytes == null) {
                    imageBytes = originalImageBytesSupplier.get();
                    if(imageBytes == null) {
                        log.warn("Original image is missing for thumbnail {}", ps.getName());
                        return names;
                    }
                }
                try {
                    byte[] thumbnail = createThumbnail(imageBytes, pictureSize);
                    String contentType = ps.getContentType();
                    uploadThumbnailToS3(thumbnail, contentType, this.imageBucketName, keyName);
                    names.add(ps.getName());
                } catch (IOException e) {
                    log.warn("Failed to store image {} on s3", ps.getName(), e);
                }
            }
        }
        return names;
    }

    protected boolean doesThumbnailExistOnS3(String baseUrl, String imageName) {
        String keyName = baseUrl + "/" + imageName;
        return s3Client.doesObjectExist(this.imageBucketName, keyName);
    }

    private void uploadThumbnailToS3(byte[] thumbnail, String contentType, String bucketName, String keyName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        try (InputStream is = new ByteArrayInputStream(thumbnail)) {
            Upload upload = s3tm.upload(bucketName, keyName, is, metadata);
            upload.waitForCompletion();
        } catch (AmazonServiceException e) {
            log.error("The call was transmitted successfully, but Amazon S3 couldn't process it, so it returned an error response.", e);
        } catch (SdkClientException e) {
            log.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3.", e);
        } catch (InterruptedException e) {
            log.error("Something interrupted the upload", e);
        }
    }

    private Optional<String> generatePresignedUrlForS3(String objectKey, Date expiration) {
        String bucketName = this.applicationProperties.getAws().getImageBucketName();
        try {
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            return Optional.of(url.toString());
        } catch (AmazonServiceException e) {
            log.error("The call was transmitted successfully, but Amazon S3 couldn't process it, so it returned an error response.", e);
        } catch (SdkClientException e) {
            log.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3.", e);
        }
        return Optional.empty();
    }
}
