package com.bonlimousin.gateway.web.graphql;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bonlimousin.gateway.bff.delegate.ArticleVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.delegate.CowVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.delegate.LinageVOResourceDelegateImpl;
import com.bonlimousin.gateway.web.api.model.ArticleVO;
import com.bonlimousin.gateway.web.api.model.SectionVO;
import com.bonlimousin.gateway.web.api.model.TagVO;
import com.bonlimousin.gateway.web.graphql.model.I18nTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

@RunWith(SpringRunner.class)
@GraphQLTest
@MockBeans({ @MockBean(CowVOResourceDelegateImpl.class), @MockBean(LinageVOResourceDelegateImpl.class) })
public class GraphqlArticleQueriesIT {

	@MockBean
	private ArticleVOResourceDelegateImpl mockArticleResource;

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	@Test
	public void findTags() throws IOException {		
		TagVO vo = new TagVO().id(1L).name(RandomStringUtils.random(5));		
		Mockito.when(mockArticleResource.getAllTagVOs(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(ResponseEntity.ok(Arrays.asList(vo)));

		GraphQLResponse response = this.graphQLTestTemplate
				.postForResource("graphql/testoperations/findtags.query.graphql");
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());
		Assert.assertEquals(vo.getName(), response.get("$.data.apiPublicTags[0].name"));
	}

	@Test
	public void getArticle() throws IOException {
		ArticleVO article1 = this.createRandomArticleVO(1);
		Mockito.when(mockArticleResource.getArticleVOByIdOrHandle(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(ResponseEntity.ok(article1));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("i18n", I18nTO.SV.name());
		variables.put("id", "1");
		variables.put("isSummary", "false");

		String graphqlOps = "graphql/testoperations";
		GraphQLResponse response = this.graphQLTestTemplate.perform(graphqlOps + "/article.query.graphql", variables,
				Arrays.asList(graphqlOps + "/sectionimage.fragment.graphql"));

		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String articlePath = "$.data.articleVO";
		validateArticle(response, articlePath, article1);

		String sectionPath = articlePath + ".sections[0]";
		validateSection(response, sectionPath, article1.getSections().get(0), true);

		validateTag(response, sectionPath + ".tags[0]", article1.getSections().get(0).getTags().get(0));
	}

	@Test
	public void findArticles() throws IOException {
		ArticleVO article1 = this.createRandomArticleVO(1);
		Mockito.when(mockArticleResource.getAllArticleVOs(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(ResponseEntity.ok(Arrays.asList(article1)));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("i18n", I18nTO.SV.name());
		variables.put("isSummary", "false");

		String graphqlOps = "graphql/testoperations";
		GraphQLResponse response = this.graphQLTestTemplate.perform(graphqlOps + "/findarticles.query.graphql",
				variables, Arrays.asList(graphqlOps + "/sectionimage.fragment.graphql"));

		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String articlePath = "$.data.apiPublicArticles[0]";
		validateArticle(response, articlePath, article1);

		String sectionPath = articlePath + ".sections[0]";
		validateSection(response, sectionPath, article1.getSections().get(0), true);

		validateTag(response, sectionPath + ".tags[0]", article1.getSections().get(0).getTags().get(0));
	}

	@Test
	public void searchArticles() throws IOException {
		ArticleVO article1 = this.createRandomArticleVO(1);
		Mockito.when(mockArticleResource.searchArticleVOs(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(Arrays.asList(article1)));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("query", "abc");
		variables.put("i18n", I18nTO.SV.name());
		variables.put("isSummary", "false");

		String graphqlOps = "graphql/testoperations";
		GraphQLResponse response = this.graphQLTestTemplate.perform(graphqlOps + "/searcharticles.query.graphql",
				variables, Arrays.asList(graphqlOps + "/sectionimage.fragment.graphql"));

		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String articlePath = "$.data.apiPublicSearchArticles[0]";
		validateArticle(response, articlePath, article1);

		String sectionPath = articlePath + ".sections[0]";
		validateSection(response, sectionPath, article1.getSections().get(0), true);

		validateTag(response, sectionPath + ".tags[0]", article1.getSections().get(0).getTags().get(0));
	}

	protected ArticleVO createRandomArticleVO(int noOfSections) {
		ArticleVO vo = new ArticleVO();
		vo.setId((long) RandomUtils.nextInt(100000));
		vo.setName(RandomStringUtils.random(10));
		vo.setCategory(ArticleVO.CategoryEnum.NEWS);
		vo.setVisibility(ArticleVO.VisibilityEnum.ANONYMOUS);
		IntStream.range(0, noOfSections).forEach(i -> vo.addSectionsItem(createRandomSectionVO(i)));
		return vo;
	}

	protected SectionVO createRandomSectionVO(long id) {
		SectionVO sec = new SectionVO();
		sec.setId(id);
		sec.setBody(RandomStringUtils.random(100));
		sec.setCaption(RandomStringUtils.random(20));
		sec.setImage("imagebytes".getBytes());
		sec.setHeight(RandomUtils.nextInt(1000));
		sec.setWidth(RandomUtils.nextInt(1000));
		sec.setImageContentType("whatever");
		sec.setIngress(RandomStringUtils.random(30));
		sec.setOrderNo(RandomUtils.nextInt(10));
		sec.setTitle(RandomStringUtils.random(12));
		sec.template(SectionVO.TemplateEnum.V1);
		sec.setVisibility(SectionVO.VisibilityEnum.ANONYMOUS);
		sec.addTagsItem(new TagVO().id(RandomUtils.nextLong()).name(RandomStringUtils.random(5)));
		return sec;
	}

	protected void validateArticle(GraphQLResponse response, String articlePath, ArticleVO article) {
		Assert.assertEquals(article.getId().floatValue() + "", response.get(articlePath + ".id"));
		Assert.assertEquals(article.getName(), response.get(articlePath + ".name"));
		Assert.assertEquals(article.getCategory().name(), response.get(articlePath + ".category"));
		Assert.assertEquals(article.getVisibility().getValue(), response.get(articlePath + ".visibility"));
	}

	protected void validateSection(GraphQLResponse response, String sectionPath, SectionVO section,
			boolean includeImageFragment) {
		Assert.assertEquals(section.getBody(), response.get(sectionPath + ".body"));
		Assert.assertEquals(section.getIngress(), response.get(sectionPath + ".ingress"));
		Assert.assertEquals(section.getTitle(), response.get(sectionPath + ".title"));
		Assert.assertEquals(section.getId().floatValue() + "", response.get(sectionPath + ".id"));
		Assert.assertEquals(section.getOrderNo().toString(), response.get(sectionPath + ".orderNo"));
		Assert.assertEquals(section.getTemplate().name(), response.get(sectionPath + ".template"));
		Assert.assertEquals(section.getVisibility().getValue(), response.get(sectionPath + ".visibility"));
		if (includeImageFragment) {
			Assert.assertEquals(section.getCaption(), response.get(sectionPath + ".caption"));
			Assert.assertEquals(section.getImageContentType(), response.get(sectionPath + ".imageContentType"));
			Assert.assertEquals(section.getHeight().toString(), response.get(sectionPath + ".height"));
			Assert.assertEquals(section.getWidth().toString(), response.get(sectionPath + ".width"));
		}
	}

	protected void validateTag(GraphQLResponse response, String tagPath, TagVO tag) {
		Assert.assertEquals(tag.getName(), response.get(tagPath + ".name"));
	}
}
