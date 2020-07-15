package com.bonlimousin.gateway.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.FragmentResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.LocalizedResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.StoryResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.TagResourceApiClient;

@Profile("mockFeignClients")
@Configuration
public class MockFeignClientsConfiguration {
	
	@MockBean
	private StoryResourceApiClient storyResourceApiClient;

	@MockBean
	private FragmentResourceApiClient fragmentResourceApiClient;

	@MockBean
	private LocalizedResourceApiClient localizedResourceApiClient;

	@MockBean
	private TagResourceApiClient tagResourceApiClient;
}