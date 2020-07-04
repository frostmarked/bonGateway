package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${fragmentResource.name:fragmentResource}", url="${fragmentResource.url:http://localhost:9003}", configuration = ClientConfiguration.class)
public interface FragmentResourceApiClient extends FragmentResourceApi {
}
