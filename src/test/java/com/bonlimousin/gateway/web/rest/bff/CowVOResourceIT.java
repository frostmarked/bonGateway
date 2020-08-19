package com.bonlimousin.gateway.web.rest.bff;

import static com.bonlimousin.gateway.web.rest.bff.LineageVOResourceIT.TEST_USER_LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

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
import org.springframework.util.Base64Utils;

import com.bonlimousin.gateway.BonGatewayApp;
import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.bonlimousin.gateway.config.RibbonTestConfiguration;
import com.bonlimousin.gateway.web.rest.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = RibbonTestConfiguration.PORT)
@AutoConfigureMockMvc
@WithMockUser(value = TEST_USER_LOGIN)
@SpringBootTest(classes = { BonGatewayApp.class, RibbonTestConfiguration.class })
class CowVOResourceIT {

	static final String TEST_USER_LOGIN = "test";
	
	private static final ObjectMapper OM = TestUtil.createObjectMapper();

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
		CattleEntity ce = new CattleEntity();
		ce.id(1L);
		ce.earTagId(100);
		ce.name("Muuu");
		ce.alert(false);
		ce.upForSale(false);
		ce.visibility(CattleEntity.VisibilityEnum.ANONYMOUS);
		ce.storyHandle("muuuhandle");
		
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
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/cattles.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(ce)))));
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/bovines.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(be)))));

		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}", 1).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));		
		verifyCow(ra, "$", ce, be);
	}
	
	@Test
	void findCowPhotos() throws Exception {
		CattleEntity ce = new CattleEntity();
		ce.setId(1L);
		ce.setEarTagId(100);
		ce.setName("Muuu");
		ce.setAlert(false);
		ce.setUpForSale(false);
		ce.setVisibility(CattleEntity.VisibilityEnum.ANONYMOUS);
		ce.setStoryHandle("muuuhandle");
		
		PhotoEntity pe = new PhotoEntity();
		pe.setHeight(100);
		pe.setWidth(200);
		pe.setImageContentType("image/png");
		pe.setImage(TestUtil.createByteArray(1, "0"));
		pe.setCaption("bbb");
		pe.setTaken(OffsetDateTime.now());
		pe.setVisibility(PhotoEntity.VisibilityEnum.ANONYMOUS);
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/cattles.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(ce)))));
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/photos.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(pe)))));

		ResultActions ra = restMockMvc.perform(get("/api/public/cows/{earTagId}/photographs", 1).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));
		
		ra.andExpect(jsonPath("$[0].caption").value(pe.getCaption()));
		ra.andExpect(jsonPath("$[0].height").value(pe.getHeight()));
		ra.andExpect(jsonPath("$[0].width").value(pe.getWidth()));
		ra.andExpect(jsonPath("$[0].imageContentType").value(pe.getImageContentType()));
		ra.andExpect(jsonPath("$[0].image").value(Matchers.is(new String(Base64Utils.encode(pe.getImage())))));		
		ra.andExpect(jsonPath("$[0].taken").exists());
		ra.andExpect(jsonPath("$[0].visibility").value(pe.getVisibility().getValue()));		
	}
	
	@Test
	void cowNotFound() throws Exception {		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/cattles.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody(OM.writeValueAsString(Arrays.array()))));

		ResultActions ra = restMockMvc
				.perform(get("/api/public/cows/{earTagId}", 1).accept(MediaType.APPLICATION_JSON));		
		ra.andExpect(status().isNotFound());
		ra.andExpect(jsonPath("$.title").exists());
	}
	
	private void verifyCow(ResultActions ra, String basePath, CattleEntity ce, BovineEntity be) throws Exception {		
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
}

