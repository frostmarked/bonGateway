package com.bonlimousin.gateway.web.rest.bff;

import static com.bonlimousin.gateway.web.rest.bff.LineageVOResourceIT.TEST_USER_LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity.VisibilityEnum;
import com.bonlimousin.gateway.config.RibbonTestConfiguration;
import com.bonlimousin.gateway.web.rest.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = RibbonTestConfiguration.PORT)
@AutoConfigureMockMvc
@WithMockUser(value = TEST_USER_LOGIN)
@SpringBootTest(classes = { BonGatewayApp.class, RibbonTestConfiguration.class })
class LineageVOResourceIT {

	static final String TEST_USER_LOGIN = "test";

	private static final ObjectMapper OM = TestUtil.createObjectMapper();

	@Autowired
	private MockMvc restMockMvc;

	@Test
	void findLineages() throws Exception {
		MatrilinealityEntity me = new MatrilinealityEntity();
		me.setId(1L);
		me.setName("cow1");
		me.setVisibility(VisibilityEnum.ANONYMOUS);
		me.setCattleNameRegexPattern(".*");
		me.setCountry("se");
		me.setDescription("abc");
		me.setEarTagId(10);
		me.setFamilyname("asdf");
		me.setPatriCountry("fr");
		me.setPatriId(90);
		me.setPatriName("pat");
		me.setPolled(true);
		me.setStoryHandle("shan");

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/matrilinealities.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1").withBody(OM.writeValueAsString(Arrays.array(me)))));

		ResultActions ra = restMockMvc.perform(get("/api/public/linages").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));
		ra.andExpect(jsonPath("$[0].id").value(me.getId()));
		ra.andExpect(jsonPath("$[0].name").value(me.getName()));
		ra.andExpect(jsonPath("$[0].visibility").value(me.getVisibility().getValue()));
		ra.andExpect(jsonPath("$[0].familyname").value(me.getFamilyname()));
		ra.andExpect(jsonPath("$[0].earTagId").value(me.getEarTagId()));
		ra.andExpect(jsonPath("$[0].country").value(me.getCountry()));
		ra.andExpect(jsonPath("$[0].patriId").value(me.getPatriId()));
		ra.andExpect(jsonPath("$[0].patriName").value(me.getPatriName()));
		ra.andExpect(jsonPath("$[0].patriCountry").value(me.getPatriCountry()));
		ra.andExpect(jsonPath("$[0].polled").value(me.getPolled()));
		ra.andExpect(jsonPath("$[0].storyHandle").value(me.getStoryHandle()));
	}

	@Test
	void findSpecficLineage() throws Exception {
		MatrilinealityEntity me = new MatrilinealityEntity();
		me.id(2L).name("cow2").visibility(VisibilityEnum.ANONYMOUS).cattleNameRegexPattern(".*").country("dk")
				.description("abc2").earTagId(20).familyname("asdf2").patriCountry("no").patriId(92).patriName("pat2")
				.polled(true).storyHandle("shan2");

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/matrilinealities.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody(OM.writeValueAsString(Arrays.array(me)))));

		ResultActions ra = restMockMvc
				.perform(get("/api/public/linages/{id}", me.getEarTagId()).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		ra.andExpect(jsonPath("$.id").value(me.getId()));
		ra.andExpect(jsonPath("$.name").value(me.getName()));
		ra.andExpect(jsonPath("$.visibility").value(me.getVisibility().getValue()));
		ra.andExpect(jsonPath("$.familyname").value(me.getFamilyname()));
		ra.andExpect(jsonPath("$.earTagId").value(me.getEarTagId()));
		ra.andExpect(jsonPath("$.country").value(me.getCountry()));
		ra.andExpect(jsonPath("$.patriId").value(me.getPatriId()));
		ra.andExpect(jsonPath("$.patriName").value(me.getPatriName()));
		ra.andExpect(jsonPath("$.patriCountry").value(me.getPatriCountry()));
		ra.andExpect(jsonPath("$.polled").value(me.getPolled()));
		ra.andExpect(jsonPath("$.storyHandle").value(me.getStoryHandle()));
	}
	
	@Test
	void lineageNotFound() throws Exception {		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/matrilinealities.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody(OM.writeValueAsString(Arrays.array()))));

		ResultActions ra = restMockMvc
				.perform(get("/api/public/linages/{id}", 1).accept(MediaType.APPLICATION_JSON));		
		ra.andExpect(status().isNotFound());
		ra.andExpect(jsonPath("$.title").exists());
	}
}