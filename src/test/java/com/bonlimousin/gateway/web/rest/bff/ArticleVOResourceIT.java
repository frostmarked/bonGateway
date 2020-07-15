package com.bonlimousin.gateway.web.rest.bff;

import static com.bonlimousin.gateway.web.rest.bff.ArticleVOResourceIT.TEST_USER_LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bonlimousin.gateway.BonGatewayApp;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.StoryResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.StoryEntity;

@Disabled("until mockbeans for feign is fixed") 
// mocking of feign client is currently broken
// wait until regular release train fixes it
// then create acceptable amount of tests
// https://github.com/spring-cloud/spring-cloud-openfeign/issues/337
@ActiveProfiles("mockFeignClients")
@AutoConfigureMockMvc
@WithMockUser(value = TEST_USER_LOGIN)
@SpringBootTest(classes = BonGatewayApp.class)
@ExtendWith(MockitoExtension.class)
public class ArticleVOResourceIT {
	static final String TEST_USER_LOGIN = "test";

	@Autowired
	private StoryResourceApiClient storyResourceApiClient;

	@Autowired
	private MockMvc restAccountMockMvc;

	@Test
	public void findArticles() throws Exception {
		StoryEntity story = new StoryEntity();
		story.setId(1L);
		story.setName("art1");
		story.setVisibility(StoryEntity.VisibilityEnum.ANONYMOUS);

		Mockito.when(storyResourceApiClient.getAllStoriesUsingGET(Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(ResponseEntity.ok(Arrays.asList(story)));

		ResultActions ra = restAccountMockMvc
				.perform(get("/api/public/articles?i18n={i18n}", "sv").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk());
		ra.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		ra.andExpect(jsonPath("$.id").value("1"));
		ra.andExpect(jsonPath("$.name").value("art1"));
		ra.andExpect(jsonPath("$.visibility").value("ROLE_ANONYMOUS"));
	}
}
