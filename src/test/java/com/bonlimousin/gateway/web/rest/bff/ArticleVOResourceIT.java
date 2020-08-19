package com.bonlimousin.gateway.web.rest.bff;

import static com.bonlimousin.gateway.web.rest.bff.ArticleVOResourceIT.TEST_USER_LOGIN;
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
import org.springframework.util.Base64Utils;

import com.bonlimousin.gateway.BonGatewayApp;
import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.FragmentEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.LocalizedEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.StoryEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.TagEntity;
import com.bonlimousin.gateway.config.Constants;
import com.bonlimousin.gateway.config.RibbonTestConfiguration;
import com.bonlimousin.gateway.web.rest.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = RibbonTestConfiguration.PORT)
@AutoConfigureMockMvc
@WithMockUser(value = TEST_USER_LOGIN)
@SpringBootTest(classes = { BonGatewayApp.class, RibbonTestConfiguration.class })
class ArticleVOResourceIT {
	static final String TEST_USER_LOGIN = "test";

	private static final ObjectMapper OM = TestUtil.createObjectMapper();

	@Autowired
	private MockMvc restMockMvc;

	@Test
	void findArticles() throws Exception {
		StoryEntity se = new StoryEntity();
		se.setId(1L);
		se.setName("art1");
		se.setVisibility(StoryEntity.VisibilityEnum.ANONYMOUS);
		se.setCategory(StoryEntity.CategoryEnum.CATTLE);

		FragmentEntity fe = new FragmentEntity();
		fe.setId(1L);
		fe.setName("ddd");
		fe.setBody("aaa");
		fe.setIngress("ccc");
		fe.setOrderNo(1);
		fe.setTemplate(FragmentEntity.TemplateEnum.V1);
		fe.setTitle("eee");
		fe.setVisibility(FragmentEntity.VisibilityEnum.ANONYMOUS);

		fe.setHeight(100);
		fe.setWidth(200);
		fe.setImageContentType("image/png");
		fe.setImage(TestUtil.createByteArray(1, "0"));
		fe.setCaption("bbb");

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/stories.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(se)))));

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/fragments.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(fe)))));

		ResultActions ra = restMockMvc
				.perform(get("/api/public/articles?i18n={i18n}", Constants.DEFAULT_LANGUAGE).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		
		ra.andExpect(jsonPath("$").isArray());
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));
		verifyArticlePart(ra, "$[0]", se);
		
		ra.andExpect(jsonPath("$[0].sections").isArray());
		ra.andExpect(jsonPath("$[0].sections", Matchers.hasSize(1)));
		verifySectionPart(ra, "$[0].sections[0]", fe);		
	}
	
	@Test
	void findSpecficArticleByHandle() throws Exception {
		StoryEntity se = new StoryEntity().id(1L).name("art1")
			.visibility(StoryEntity.VisibilityEnum.ANONYMOUS).category(StoryEntity.CategoryEnum.CATTLE);

		FragmentEntity fe = new FragmentEntity().id(1L).name("ddd").body("aaa").ingress("ccc")
			.orderNo(1).template(FragmentEntity.TemplateEnum.V1).title("eee").visibility(FragmentEntity.VisibilityEnum.ANONYMOUS)
			.height(100).width(200).imageContentType("image/png")
			.image(TestUtil.createByteArray(1, "0")).caption("bbb");

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/stories.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(se)))));

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/fragments.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(fe)))));

		ResultActions ra = restMockMvc
				.perform(get("/api/public/articles/{id}?i18n={i18n}&isHandle={isHandle}", "handleid", Constants.DEFAULT_LANGUAGE, true).accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
				
		verifyArticlePart(ra, "$", se);		
		verifySectionPart(ra, "$.sections[0]", fe);		
	}
	
	@Test
	void searchInternationalArticles() throws Exception {
		StoryEntity se = new StoryEntity();
		se.setId(2L);
		se.setName("art2");
		se.setVisibility(StoryEntity.VisibilityEnum.ANONYMOUS);
		se.setCategory(StoryEntity.CategoryEnum.NEWS);

		FragmentEntity fe = new FragmentEntity();
		fe.setId(2L);
		fe.setName("ddd");
		fe.setBody("aaa");
		fe.setIngress("ccc");
		fe.setOrderNo(1);
		fe.setTemplate(FragmentEntity.TemplateEnum.V1);
		fe.setTitle("eee");
		fe.setVisibility(FragmentEntity.VisibilityEnum.ANONYMOUS);

		fe.setHeight(100);
		fe.setWidth(200);
		fe.setImageContentType("image/png");
		fe.setImage(TestUtil.createByteArray(1, "0"));
		fe.setCaption("bbb");
		fe.setStory(se);
		
		LocalizedEntity le = new LocalizedEntity();
		le.setId(2L);
		le.setI18n("en");
		le.setVisibility(LocalizedEntity.VisibilityEnum.ANONYMOUS);
		le.setBody("aaa2");
		le.setIngress("ccc2");				
		le.setTitle("eee2");		
		le.setCaption("bbb2");
		le.setFragment(fe);
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/stories.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(se)))));

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/_search/localizeds.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(le)))));
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/fragments.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(fe)))));
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/localizeds.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(le)))));		
		
		ResultActions ra = restMockMvc
				.perform(get("/api/public/_search/articles?i18n={i18n}&query={query}", "en", "test").accept(MediaType.APPLICATION_JSON));
		
		ra.andExpect(jsonPath("$").isArray());
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));
		verifyArticlePart(ra, "$[0]", se);
		
		ra.andExpect(jsonPath("$[0].sections").isArray());
		ra.andExpect(jsonPath("$[0].sections", Matchers.hasSize(1)));
		verifySectionPart(ra, "$[0].sections[0]", fe, le);
	}
	
	@Test
	void faultyArticleId() throws Exception {		
		ResultActions ra = restMockMvc
				.perform(get("/api/public/articles/{id}", "abc").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().is4xxClientError());	
	}
	
	@Test
	void findTags() throws Exception {
		TagEntity te = new TagEntity();
		te.setId(1L);
		te.setName("t1");

		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/api/tags.*"))
				.willReturn(WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withHeader(BFFUtil.HEADER_X_TOTAL_COUNT, "1")
						.withBody(OM.writeValueAsString(Arrays.array(te)))));

		ResultActions ra = restMockMvc
				.perform(get("/api/public/tags").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		
		ra.andExpect(jsonPath("$").isArray());
		ra.andExpect(jsonPath("$", Matchers.hasSize(1)));				
		ra.andExpect(jsonPath("$[0].id").value(te.getId()));
		ra.andExpect(jsonPath("$[0].name").value(te.getName()));
	}

	private void verifyArticlePart(ResultActions ra, String basePath, StoryEntity se) throws Exception {
		ra.andExpect(jsonPath(basePath + ".id").value(se.getId()));
		ra.andExpect(jsonPath(basePath + ".name").value(se.getName()));
		ra.andExpect(jsonPath(basePath + ".visibility").value(se.getVisibility().getValue()));
		ra.andExpect(jsonPath(basePath + ".category").value(se.getCategory().getValue()));
	}

	private void verifySectionPart(ResultActions ra, String basePath, FragmentEntity fe) throws Exception {
		ra.andExpect(jsonPath(basePath + ".id").value(Matchers.is(fe.getId().intValue())));
//		ra.andExpect(jsonPath(basePath + ".name").value(Matchers.is(fe.getName())));
		ra.andExpect(jsonPath(basePath + ".body").value(fe.getBody()));
		ra.andExpect(jsonPath(basePath + ".ingress").value(fe.getIngress()));
		ra.andExpect(jsonPath(basePath + ".orderNo").value(fe.getOrderNo()));
		ra.andExpect(jsonPath(basePath + ".template").value(fe.getTemplate().getValue()));
		ra.andExpect(jsonPath(basePath + ".title").value(fe.getTitle()));
		ra.andExpect(jsonPath(basePath + ".visibility").value(fe.getVisibility().getValue()));

		ra.andExpect(jsonPath(basePath + ".height").value(fe.getHeight()));
		ra.andExpect(jsonPath(basePath + ".width").value(fe.getWidth()));
		ra.andExpect(jsonPath(basePath + ".imageContentType").value(fe.getImageContentType()));
		ra.andExpect(jsonPath(basePath + ".image").value(Matchers.is(new String(Base64Utils.encode(fe.getImage())))));
		ra.andExpect(jsonPath(basePath + ".caption").value(fe.getCaption()));
	}
	
	private void verifySectionPart(ResultActions ra, String basePath, FragmentEntity fe, LocalizedEntity le) throws Exception {
		ra.andExpect(jsonPath(basePath + ".id").value(Matchers.is(le.getId().intValue())));
//		ra.andExpect(jsonPath(basePath + ".name").value(Matchers.is(fe.getName())));
		ra.andExpect(jsonPath(basePath + ".body").value(le.getBody()));
		ra.andExpect(jsonPath(basePath + ".ingress").value(le.getIngress()));
		ra.andExpect(jsonPath(basePath + ".orderNo").value(fe.getOrderNo()));
		ra.andExpect(jsonPath(basePath + ".template").value(fe.getTemplate().getValue()));
		ra.andExpect(jsonPath(basePath + ".title").value(le.getTitle()));
		ra.andExpect(jsonPath(basePath + ".visibility").value(le.getVisibility().getValue()));

		ra.andExpect(jsonPath(basePath + ".height").value(fe.getHeight()));
		ra.andExpect(jsonPath(basePath + ".width").value(fe.getWidth()));
		ra.andExpect(jsonPath(basePath + ".imageContentType").value(fe.getImageContentType()));
		ra.andExpect(jsonPath(basePath + ".image").value(Matchers.is(new String(Base64Utils.encode(fe.getImage())))));
		ra.andExpect(jsonPath(basePath + ".caption").value(le.getCaption()));
	}
}
