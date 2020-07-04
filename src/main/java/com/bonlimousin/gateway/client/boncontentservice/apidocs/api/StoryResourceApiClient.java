package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.story.name}", url="${application.bff.resource.story.url}", configuration = ClientConfiguration.class)
public interface StoryResourceApiClient extends StoryResourceApi {
}
