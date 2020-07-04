package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.tag.name}", url="${application.bff.resource.tag.url}", configuration = ClientConfiguration.class)
public interface TagResourceApiClient extends TagResourceApi {
}
