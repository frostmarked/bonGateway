package com.bonlimousin.gateway.web.graphql;

import java.io.IOException;
import java.util.Arrays;

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
import com.bonlimousin.gateway.web.api.model.LinageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

@RunWith(SpringRunner.class)
@GraphQLTest
@MockBeans({ @MockBean(CowVOResourceDelegateImpl.class), @MockBean(ArticleVOResourceDelegateImpl.class) })
public class GraphqlLinageQueriesIT {

	@MockBean
	private LinageVOResourceDelegateImpl mockLinageResource;

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	@Test
	public void findLinages() throws IOException {
		LinageVO vo = createRandomLinageVO();

		Mockito.when(mockLinageResource.findAllLinageVOs(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(ResponseEntity.ok(Arrays.asList(vo)));

		GraphQLResponse response = this.graphQLTestTemplate
				.postForResource("graphql/testoperations/findlinages.query.graphql");
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String linagePath = "$.data.apiPublicLinages[0]";
		validateLinageVO(response, linagePath, vo, true);
	}

	@Test
	public void getLinage() throws IOException {
		LinageVO vo = createRandomLinageVO();

		Mockito.when(mockLinageResource.getLinageVO(Mockito.any())).thenReturn(ResponseEntity.ok(vo));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("earTagId", vo.getEarTagId().toString());
		
		GraphQLResponse response = this.graphQLTestTemplate
				.perform("graphql/testoperations/linage.query.graphql", variables);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String linagePath = "$.data.linageVO";
		validateLinageVO(response, linagePath, vo, false);
	}

	protected LinageVO createRandomLinageVO() {
		LinageVO vo = new LinageVO();
		vo.setId(1L);
		vo.setEarTagId(RandomUtils.nextInt(100));
		vo.setName(RandomStringUtils.random(10));
		vo.setCountry("se");
		vo.setFamilyname(RandomStringUtils.random(20));

		vo.setPatriId(RandomUtils.nextInt(100));
		vo.setPatriCountry("fr");
		vo.setPatriName(RandomStringUtils.random(10));

		vo.setPolled(true);
		vo.setStoryHandle(RandomStringUtils.random(10));
		vo.setVisibility(LinageVO.VisibilityEnum.USER);
		return vo;
	}

	protected void validateLinageVO(GraphQLResponse response, String linagePath, LinageVO vo, boolean isSummary) {
		Assert.assertEquals(vo.getId().floatValue() + "", response.get(linagePath + ".id"));
		Assert.assertEquals(vo.getEarTagId().toString(), response.get(linagePath + ".earTagId"));
		Assert.assertEquals(vo.getName(), response.get(linagePath + ".name"));
		Assert.assertEquals(vo.getCountry(), response.get(linagePath + ".country"));
		Assert.assertEquals(vo.getFamilyname(), response.get(linagePath + ".familyname"));
		if (!isSummary) {
			Assert.assertEquals(vo.getPatriId() + "", response.get(linagePath + ".patriId"));
			Assert.assertEquals(vo.getPatriCountry(), response.get(linagePath + ".patriCountry"));
			Assert.assertEquals(vo.getPatriName(), response.get(linagePath + ".patriName"));
			Assert.assertEquals(vo.getStoryHandle(), response.get(linagePath + ".storyHandle"));
		}
		Assert.assertEquals(vo.getPolled().toString(), response.get(linagePath + ".polled"));
		Assert.assertEquals(vo.getVisibility().getValue(), response.get(linagePath + ".visibility"));
	}

}
