package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${cattleResource.name:cattleResource}", url="${cattleResource.url:http://localhost:9001}", configuration = ClientConfiguration.class)
public interface CattleResourceApiClient extends CattleResourceApi {
}
