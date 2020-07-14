package com.bonlimousin.gateway.web.graphql;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
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
import com.bonlimousin.gateway.web.api.model.CowVO;
import com.bonlimousin.gateway.web.api.model.PhotographVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

@RunWith(SpringRunner.class)
@GraphQLTest
@MockBeans({ @MockBean(ArticleVOResourceDelegateImpl.class), @MockBean(LinageVOResourceDelegateImpl.class) })
public class GraphqlCowQueriesIT {

	@MockBean
	private CowVOResourceDelegateImpl mockCowResource;

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	@Test
	public void findCowPhotos() throws IOException {
		PhotographVO vo = new PhotographVO();
		vo.setId(1L);
		vo.setCaption(RandomStringUtils.random(10));
		vo.setEarTagId(RandomUtils.nextInt(100000));
		vo.setHeight(RandomUtils.nextInt(1000));
		vo.setWidth(RandomUtils.nextInt(1000));
		vo.setImage("image".getBytes());
		vo.setImageContentType("ict");
		vo.setTaken(OffsetDateTime.now().minusHours(6));
		vo.setVisibility(PhotographVO.VisibilityEnum.ANONYMOUS);

		Mockito.when(
				mockCowResource.getAllPhotographVOsByCow(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(ResponseEntity.ok(Arrays.asList(vo)));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("earTagId", "1");
		variables.put("excludeImage", "true");

		String graphqlOps = "graphql/testoperations";
		GraphQLResponse response = this.graphQLTestTemplate.perform(graphqlOps + "/findcowphotos.query.graphql",
				variables);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		Assert.assertEquals(vo.getId().floatValue() + "", response.get("$.data.apiPublicCowPhotographs[0].id"));
		Assert.assertEquals(vo.getCaption(), response.get("$.data.apiPublicCowPhotographs[0].caption"));
		Assert.assertEquals(vo.getEarTagId().toString(), response.get("$.data.apiPublicCowPhotographs[0].earTagId"));
		Assert.assertEquals(vo.getHeight().toString(), response.get("$.data.apiPublicCowPhotographs[0].height"));
		Assert.assertEquals(vo.getWidth().toString(), response.get("$.data.apiPublicCowPhotographs[0].width"));
		Assert.assertEquals(vo.getTaken().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)),
				response.get("$.data.apiPublicCowPhotographs[0].taken"));
	}

	@Test
	public void getCow() throws IOException {
		CowVO cow1 = this.createRandomCowVO();
		Mockito.when(mockCowResource.getCowVO(Mockito.any())).thenReturn(ResponseEntity.ok(cow1));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("earTagId", "1");

		String graphqlOps = "graphql/testoperations";
		GraphQLResponse response = this.graphQLTestTemplate.perform(graphqlOps + "/cow.query.graphql", variables);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String cowPath = "$.data.cowVO";
		validateCow(response, cowPath, cow1);
	}

	@Test
	public void findCows() throws IOException {
		CowVO cow1 = this.createRandomCowVO();
		Mockito.when(mockCowResource.findCowVOs(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(ResponseEntity.ok(Arrays.asList(cow1)));

		ObjectNode variables = new ObjectMapper().createObjectNode();
		variables.put("size", "100");

		String graphqlOps = "graphql/testoperations";
		GraphQLResponse response = this.graphQLTestTemplate.perform(graphqlOps + "/findcows.query.graphql", variables);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.isOk());

		String cowPath = "$.data.apiPublicCows[0]";
		validateCow(response, cowPath, cow1);
	}

	protected CowVO createRandomCowVO() {
		CowVO vo = new CowVO();
		vo.setEarTagId(RandomUtils.nextInt(100000));
		vo.setName(RandomStringUtils.random(10));
		vo.setBirthDate(OffsetDateTime.now().minusMonths(3));
		vo.setGender(CowVO.GenderEnum.HEIFER);
		vo.setHornStatus(CowVO.HornStatusEnum.POLLED);
		vo.setLinageId(1L);
		vo.setLinageName(RandomStringUtils.random(10));
		vo.setMatriId(RandomUtils.nextInt(100000));
		vo.setPatriId(RandomUtils.nextInt(100000));
		vo.setStoryHandle(RandomStringUtils.random(10));
		vo.setVisibility(CowVO.VisibilityEnum.ANONYMOUS);
		vo.setWeight0(30 + RandomUtils.nextInt(20));
		vo.setWeight200(220 + RandomUtils.nextInt(100));
		vo.setWeight365(400 + RandomUtils.nextInt(150));
		return vo;
	}

	protected void validateCow(GraphQLResponse response, String cowPath, CowVO cow) {
		Assert.assertEquals(cow.getEarTagId().toString(), response.get(cowPath + ".earTagId"));
		Assert.assertEquals(cow.getName(), response.get(cowPath + ".name"));
		Assert.assertEquals(cow.getBirthDate().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)),
				response.get(cowPath + ".birthDate"));
		Assert.assertEquals(cow.getGender().name(), response.get(cowPath + ".gender"));
		Assert.assertEquals(cow.getHornStatus().name(), response.get(cowPath + ".hornStatus"));
		Assert.assertEquals(cow.getLinageId().doubleValue() + "", response.get(cowPath + ".linageId"));
		Assert.assertEquals(cow.getLinageName(), response.get(cowPath + ".linageName"));
		Assert.assertEquals(cow.getMatriId().toString(), response.get(cowPath + ".matriId"));
		Assert.assertEquals(cow.getPatriId().toString(), response.get(cowPath + ".patriId"));
		Assert.assertEquals(cow.getStoryHandle(), response.get(cowPath + ".storyHandle"));
		Assert.assertEquals(cow.getVisibility().getValue(), response.get(cowPath + ".visibility"));
		Assert.assertEquals(cow.getWeight0().toString(), response.get(cowPath + ".weight0"));
		Assert.assertEquals(cow.getWeight200().toString(), response.get(cowPath + ".weight200"));
		Assert.assertEquals(cow.getWeight365().toString(), response.get(cowPath + ".weight365"));
	}
}
