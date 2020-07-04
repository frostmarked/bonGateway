package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${localizedResource.name:localizedResource}", url="${localizedResource.url:http://localhost:9003}", configuration = ClientConfiguration.class)
public interface LocalizedResourceApiClient extends LocalizedResourceApi {
}
