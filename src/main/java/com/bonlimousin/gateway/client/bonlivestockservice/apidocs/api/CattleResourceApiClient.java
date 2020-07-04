package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.cattle.name}", url="${application.bff.resource.cattle.url}", configuration = ClientConfiguration.class)
public interface CattleResourceApiClient extends CattleResourceApi {
}
