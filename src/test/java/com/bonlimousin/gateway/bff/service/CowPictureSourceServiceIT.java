package com.bonlimousin.gateway.bff.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.tika.mime.MimeTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bonlimousin.gateway.BonGatewayApp;
import com.bonlimousin.gateway.bff.service.AbstractPictureSourceService.PictureSize;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.web.api.model.PictureSourceVO;

@SpringBootTest(classes = BonGatewayApp.class)
class CowPictureSourceServiceIT {

	@Autowired
	private CowPictureSourceService cowPictureSourceService;
	
	private CattleEntity cattleEntity;
	private PhotoEntity pePNG;
	private PhotoEntity peJpeg34;
	private PhotoEntity peJpeg43;
	
	@BeforeEach
    public void setup() throws IOException {
		this.cattleEntity = new CattleEntity();
		this.cattleEntity.id(1L).earTagId(1000).name("Europe");
		this.cattleEntity.alert(false).upForSale(false);
		this.cattleEntity.visibility(CattleEntity.VisibilityEnum.ANONYMOUS);
		this.cattleEntity.storyHandle("cattle1000story");
		
		this.pePNG = new PhotoEntity().cattle(this.cattleEntity).id(192L);
		this.pePNG.width(192).height(192).imageContentType("image/png");		
		this.pePNG.image(IOUtils.resourceToByteArray("/content/images/hipster.png"));
		this.pePNG.taken(OffsetDateTime.now());			
		this.pePNG.caption("hipster").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
		
		this.peJpeg34 = new PhotoEntity().cattle(this.cattleEntity).id(34L);
		this.peJpeg34.width(3000).height(4000).imageContentType("image/jpeg");		
		this.peJpeg34.image(IOUtils.resourceToByteArray("/content/images/gone_fishing_34.jpg"));
		this.peJpeg34.taken(OffsetDateTime.now().minusDays(1));			
		this.peJpeg34.caption("gone fishing").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
		
		this.peJpeg43 = new PhotoEntity().cattle(this.cattleEntity).id(43L);
		this.peJpeg43.width(4000).height(3000).imageContentType("image/jpeg");		
		this.peJpeg43.image(IOUtils.resourceToByteArray("/content/images/gone_fishing_43.jpg"));
		this.peJpeg43.taken(OffsetDateTime.now().minusDays(1));			
		this.peJpeg43.caption("gone fishing rot").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
	}
	
	@Test
    void testPNG() throws IOException, MimeTypeException {		
		List<PictureSourceVO> psList = cowPictureSourceService.createPictureSourceVOs(pePNG);
		assertThat(psList).isNotEmpty().hasSize(1);
		PictureSourceVO ps = psList.get(0);
		assertThat(ps.getWidth()).isEqualTo(192);
		assertThat(ps.getHeight()).isEqualTo(192);
	}
	
	@Test
    void testJpegHigh() throws IOException, MimeTypeException {		
		List<PictureSourceVO> psList = cowPictureSourceService.createPictureSourceVOs(peJpeg34);
		assertThat(psList).isNotEmpty().hasSize(PictureSize.values().length);
	}
	
	@Test
    void testJpegWide() throws IOException, MimeTypeException {		
		List<PictureSourceVO> psList = cowPictureSourceService.createPictureSourceVOs(peJpeg43);
		assertThat(psList).isNotEmpty().hasSize(PictureSize.values().length);
	}
	
	@Test
    void testJpegOriginal() throws IOException, MimeTypeException {		
		Optional<PictureSourceVO> opt = cowPictureSourceService.createPictureSourceVO(peJpeg43, PictureSize.ORIGINAL);
		assertThat(opt).isPresent();
		PictureSourceVO ps = opt.get();
		assertThat(ps.getWidth()).isEqualTo(peJpeg43.getWidth());
		assertThat(ps.getHeight()).isEqualTo(peJpeg43.getHeight());
		assertThat(ps.getContentType()).isEqualTo("image/jpeg");
		assertThat(ps.getName()).endsWith(".jpg");
	}
	
	@Test
    void testJpegSmall() throws IOException, MimeTypeException {
		PictureSize s = PictureSize.SMALL;
		Optional<PictureSourceVO> opt = cowPictureSourceService.createPictureSourceVO(peJpeg43, s);
		assertThat(opt).isPresent();
		PictureSourceVO ps = opt.get();
		assertThat(ps.getWidth()).isEqualTo(s.pixelWidth());
		assertThat(ps.getHeight()).isEqualTo(405);
		assertThat(ps.getContentType()).isEqualTo("image/jpeg");
		assertThat(ps.getName()).endsWith(s.pixelWidth() + "w.jpg");
	}
}
