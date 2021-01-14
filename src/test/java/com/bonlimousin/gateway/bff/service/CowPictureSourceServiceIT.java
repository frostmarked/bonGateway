package com.bonlimousin.gateway.bff.service;

import com.bonlimousin.gateway.BonGatewayApp;
import com.bonlimousin.gateway.bff.service.AbstractPictureSourceService.PictureSize;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;
import org.apache.commons.io.IOUtils;
import org.apache.tika.mime.MimeTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BonGatewayApp.class)
class CowPictureSourceServiceIT {

	@Autowired
	private CowPictureSourceService cowPictureSourceService;

	private CattleEntity cattleEntity;

	@BeforeEach
    public void setup() throws IOException {
		this.cattleEntity = new CattleEntity();
		this.cattleEntity.id(1L).earTagId(1000).name("Europe");
		this.cattleEntity.alert(false).upForSale(false);
		this.cattleEntity.visibility(CattleEntity.VisibilityEnum.ANONYMOUS);
		this.cattleEntity.storyHandle("cattle1000story");

        createPNG();
    }

    private PhotoEntity createPNG() throws IOException {
        PhotoEntity pePNG = new PhotoEntity().cattle(this.cattleEntity).id(192L);
        pePNG.width(192).height(192).imageContentType("image/png");
        pePNG.image(IOUtils.resourceToByteArray("/content/images/hipster.png"));
        pePNG.taken(OffsetDateTime.now());
        pePNG.caption("hipster").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
        return pePNG;
    }

    private PhotoEntity createJpeg34() throws IOException {
        PhotoEntity peJpeg34 = new PhotoEntity().cattle(this.cattleEntity).id(34L);
        peJpeg34.width(3000).height(4000).imageContentType("image/jpeg");
        peJpeg34.image(IOUtils.resourceToByteArray("/content/images/gone_fishing_34.jpg"));
        peJpeg34.taken(OffsetDateTime.now().minusDays(1));
        peJpeg34.caption("gone fishing").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
        return peJpeg34;
    }

    private PhotoEntity createJpeg43() throws IOException {
        PhotoEntity peJpeg43 = new PhotoEntity().cattle(this.cattleEntity).id(43L);
        peJpeg43.width(4000).height(3000).imageContentType("image/jpeg");
        peJpeg43.image(IOUtils.resourceToByteArray("/content/images/gone_fishing_43.jpg"));
        peJpeg43.taken(OffsetDateTime.now().minusDays(1));
        peJpeg43.caption("gone fishing rot").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
        return peJpeg43;
    }

    @Test
    void testPNG() throws IOException, MimeTypeException {
        Map<PictureSize, PictureSourceVO> map = cowPictureSourceService.createPictureSourceVOs(createPNG(), "/test");
		assertThat(map).isNotEmpty().hasSize(1);
		PictureSourceVO ps = map.entrySet().iterator().next().getValue();
		assertThat(ps.getWidth()).isEqualTo(192);
		assertThat(ps.getHeight()).isEqualTo(192);
	}

	@Test
    void testJpegHigh() throws IOException, MimeTypeException {
        Map<PictureSize, PictureSourceVO> map = cowPictureSourceService.createPictureSourceVOs(createJpeg34(), "/test");
		assertThat(map).isNotEmpty().hasSize(PictureSize.values().length);
	}

	@Test
    void testJpegWide() throws IOException, MimeTypeException {
		Map<PictureSize, PictureSourceVO> map = cowPictureSourceService.createPictureSourceVOs(createJpeg43(), "/test");
		assertThat(map).isNotEmpty().hasSize(PictureSize.values().length);
	}

	@Test
    void testJpegOriginal() throws IOException, MimeTypeException {
	    PhotoEntity peJpeg43 = createJpeg43();
		Optional<PictureSourceVO> opt = cowPictureSourceService.createPictureSourceVO(peJpeg43, "/test", PictureSize.ORIGINAL);
		assertThat(opt).isPresent();
		PictureSourceVO ps = opt.get();
		assertThat(ps.getWidth()).isEqualTo(peJpeg43.getWidth());
		assertThat(ps.getHeight()).isEqualTo(peJpeg43.getHeight());
		assertThat(ps.getContentType()).isEqualTo("image/jpeg");
		assertThat(ps.getName()).endsWith(".jpg");
        assertThat(ps.getUrl()).endsWith(".jpg");
	}

	@Test
    void testJpegSmall() throws IOException, MimeTypeException {
		PictureSize s = PictureSize.SMALL;
        PhotoEntity peJpeg43 = createJpeg43();
		Optional<PictureSourceVO> opt = cowPictureSourceService.createPictureSourceVO(peJpeg43, "/test", s);
		assertThat(opt).isPresent();
		PictureSourceVO ps = opt.get();
		assertThat(ps.getWidth()).isEqualTo(s.pixelWidth());
		assertThat(ps.getHeight()).isEqualTo(405);
		assertThat(ps.getContentType()).isEqualTo("image/jpeg");
		assertThat(ps.getName()).endsWith(s.pixelWidth() + "w.jpg");
        assertThat(ps.getUrl()).endsWith(".jpg");
	}
}
