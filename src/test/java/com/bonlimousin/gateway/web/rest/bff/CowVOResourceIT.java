package com.bonlimousin.gateway.web.rest.bff;

import static com.bonlimousin.gateway.web.rest.bff.LineageVOResourceIT.TEST_USER_LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.OffsetDateTime;

import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bonlimousin.gateway.BonGatewayApp;
import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.bff.service.AbstractPictureSourceService.PictureSize;
import com.bonlimousin.gateway.bff.service.CowPictureSourceService;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.bonlimousin.gateway.config.RibbonTestConfiguration;
import com.bonlimousin.gateway.web.rest.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = RibbonTestConfiguration.PORT)
@AutoConfigureMockMvc
@WithMockUser(value = TEST_USER_LOGIN)
@SpringBootTest(classes = { BonGatewayApp.class, RibbonTestConfiguration.class })
class CowVOResourceIT {

	static final String TEST_USER_LOGIN = "test";	
	
	private static final ObjectMapper OM = TestUtil.createObjectMapper();

	@Autowired
	private CowPictureSourceService cowPictureSourceService;
	
	@Autowired
	private MockMvc restMockMvc;

	@Test
	void findCows() throws Exception {
		CattleEntity ce = new CattleEntity();
		ce.setId(1L);
		ce.setEarTagId(100);
		ce.setName("Muuu");
		ce.setAlert(false);
		ce.setUpForSale(false);
		ce.setVisibility(CattleEntity.VisibilityEnum.ANONYMOUS);
		ce.setStoryHandle("muuuhandle");
		
		BovineEntity be = new BovineEntity();
		be.setId(2L);
		be.setEarTagId(ce.getEarTagId());
		be.setCountry("se");
		be.setBirthDate(OffsetDateTime.now().minusDays(200));
		be.setHerdId(111);
		be.setMasterIdentifier(ce.getEarTagId().toString());
		be.setName("Uuu");		
		be.setBovineStatus(BovineEntity.BovineStatusEnum.ON_FARM);
		be.setGender(BovineEntity.GenderEnum.HEIFER);
		be.setHornStatus(BovineEntity.HornStatusEnum.POLLED);
		be.setMatriId(10);
		be.setPatriId(20);
		be.setWeight0(38);
		be.setWeight200(290);
		be.setWeight365(500);
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/cattles.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(ce)))));
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/bovines.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(be)))));

		ResultActions ra = restMockMvc.perform(get("/api/public/cows").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));
		verifyCow(ra, "$[0]", ce, be);
	}
	
	@Test
	void findSpecficCow() throws Exception {
		CattleEntity ce = createCattleEntity();		
		BovineEntity be = createBovineEntity(ce);						
		stubCattleEndpoint(Arrays.array(ce));
		stubBovineEndpoint(Arrays.array(be));
		
		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}", 1).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));		
		verifyCow(ra, "$", ce, be);
	}
	
	@Test
	void cowNotFound() throws Exception {		
		stubCattleEndpoint(Arrays.array());
		ResultActions ra = restMockMvc
				.perform(get("/api/public/cows/{earTagId}", 1).accept(MediaType.APPLICATION_JSON));		
		ra.andExpect(status().isNotFound());
		ra.andExpect(jsonPath("$.title").exists());
	}
	
	@Test
	void findCowPictures() throws Exception {
		CattleEntity ce = createCattleEntity();		
		PhotoEntity pe = createPhotoEntity(ce);						
		stubCattleEndpoint(Arrays.array(ce));
		stubPhotoEndpoint(Arrays.array(pe));
		
		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}/pictures", 1).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));
		
		ra.andExpect(jsonPath("$[0].id").value(pe.getId()));		
		ra.andExpect(jsonPath("$[0].caption").value(pe.getCaption()));
		ra.andExpect(jsonPath("$[0].taken").exists());
		ra.andExpect(jsonPath("$[0].visibility").value(pe.getVisibility().getValue()));
				
		ra.andExpect(jsonPath("$[0].sources[0].name").value(Matchers.notNullValue()));
		ra.andExpect(jsonPath("$[0].sources[0].width").value(pe.getWidth()));
		ra.andExpect(jsonPath("$[0].sources[0].height").value(pe.getHeight()));		
		ra.andExpect(jsonPath("$[0].sources[0].contentType").value(pe.getImageContentType()));
		ra.andExpect(jsonPath("$[0].sources[0].url").value(Matchers.notNullValue()));								
	}
	
	@Test
	void getCowImage() throws Exception {
		CattleEntity ce = createCattleEntity();		
		PhotoEntity pe = createPhotoEntity(ce);						
		stubCattleEndpoint(Arrays.array(ce));
		stubPhotoEndpoint(Arrays.array(pe));
		String imgName = cowPictureSourceService.getImageName(ce.getEarTagId(), pe.getId(), PictureSize.ORIGINAL, ".png");
		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}/pictures/{pictureId}/{imageName}", ce.getEarTagId(), pe.getId(), imgName));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.IMAGE_PNG_VALUE));
	}
	
	@Test
	void cowImageNotFound1() throws Exception {										
		stubCattleEndpoint(Arrays.array());
		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}/pictures/{pictureId}/{imageName}", 1, 1, "cow1_1.png"));
		ra.andExpect(status().isNotFound());
	}
	
	@Test
	void cowImageNotFound2() throws Exception {		
		CattleEntity ce = createCattleEntity();										
		stubCattleEndpoint(Arrays.array(ce));
		stubPhotoEndpoint(Arrays.array());		
		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}/pictures/{pictureId}/{imageName}", 1, 1, "cow1_1.png"));
		ra.andExpect(status().isNotFound());
	}
	
	@Test
	void cowImageNotFound3() throws Exception {
		CattleEntity ce = createCattleEntity();		
		PhotoEntity pe = createPhotoEntity(ce);						
		stubCattleEndpoint(Arrays.array(ce));
		stubPhotoEndpoint(Arrays.array(pe));		
		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}/pictures/{pictureId}/{imageName}", 1, 1, "dummy.png"));
		ra.andExpect(status().isNotFound());
	}
	
	private static void verifyCow(ResultActions ra, String basePath, CattleEntity ce, BovineEntity be) throws Exception {		
		ra.andExpect(jsonPath(basePath + ".earTagId").value(ce.getEarTagId()));
		ra.andExpect(jsonPath(basePath + ".name").value(ce.getName()));
		ra.andExpect(jsonPath(basePath + ".visibility").value(ce.getVisibility().getValue()));
		ra.andExpect(jsonPath(basePath + ".storyHandle").value(ce.getStoryHandle()));
		
		ra.andExpect(jsonPath(basePath + ".matriId").value(be.getMatriId()));
		ra.andExpect(jsonPath(basePath + ".patriId").value(be.getPatriId()));
		
		ra.andExpect(jsonPath(basePath + ".gender").value(be.getGender().getValue()));
		ra.andExpect(jsonPath(basePath + ".hornStatus").value(be.getHornStatus().getValue()));		
		
		ra.andExpect(jsonPath(basePath + ".weight0").value(be.getWeight0()));
		ra.andExpect(jsonPath(basePath + ".weight200").value(be.getWeight200()));
		ra.andExpect(jsonPath(basePath + ".weight365").value(be.getWeight365()));
		
		ra.andExpect(jsonPath(basePath + ".birthDate").exists());
	}
	
	private CattleEntity createCattleEntity() {
		CattleEntity ce = new CattleEntity();
		ce.id(1L).earTagId(100).name("Muuu");
		ce.alert(false).upForSale(false);
		ce.visibility(CattleEntity.VisibilityEnum.ANONYMOUS);
		ce.storyHandle("muuuhandle");
		return ce;
	}

	private BovineEntity createBovineEntity(CattleEntity ce) {
		BovineEntity be = new BovineEntity();
		be.id(2L);
		be.earTagId(ce.getEarTagId());
		be.country("se");
		be.birthDate(OffsetDateTime.now().minusDays(200));
		be.herdId(111);
		be.masterIdentifier(ce.getEarTagId().toString());
		be.name("Uuu");		
		be.bovineStatus(BovineEntity.BovineStatusEnum.ON_FARM);
		be.gender(BovineEntity.GenderEnum.HEIFER);
		be.hornStatus(BovineEntity.HornStatusEnum.POLLED);
		be.matriId(10);
		be.patriId(20);
		be.weight0(38);
		be.weight200(290);
		be.weight365(500);
		return be;
	}
	
	private PhotoEntity createPhotoEntity(CattleEntity ce) throws IOException {
		PhotoEntity pe = new PhotoEntity().cattle(ce).id(2L);
		pe.width(192).height(192).imageContentType("image/png");
		byte[] imageBytes = IOUtils.resourceToByteArray("/content/images/hipster.png");
		pe.image(imageBytes).taken(OffsetDateTime.now());			
		pe.caption("bbb").visibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
		return pe;
	}
	
	private static StubMapping stubCattleEndpoint(CattleEntity[] entities) throws JsonProcessingException {
		return WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/cattles.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, String.valueOf(entities.length))
						.withBody(OM.writeValueAsString(entities))));		
	}
	
	private static StubMapping stubBovineEndpoint(BovineEntity[] entities) throws JsonProcessingException {		
		return WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/bovines.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, String.valueOf(entities.length))
						.withBody(OM.writeValueAsString(entities))));
	}
	
	private static StubMapping stubPhotoEndpoint(PhotoEntity[] entities) throws JsonProcessingException {
		return WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/photos.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, String.valueOf(entities.length))
						.withBody(OM.writeValueAsString(entities))));
	}
}

