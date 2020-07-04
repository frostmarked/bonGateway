package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${storyResource.name:storyResource}", url="${storyResource.url:http://localhost:9003}", configuration = ClientConfiguration.class)
public interface StoryResourceApiClient extends StoryResourceApi {
}
