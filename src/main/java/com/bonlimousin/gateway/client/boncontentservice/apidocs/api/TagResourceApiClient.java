package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${tagResource.name:tagResource}", url="${tagResource.url:http://localhost:9003}", configuration = ClientConfiguration.class)
public interface TagResourceApiClient extends TagResourceApi {
}
