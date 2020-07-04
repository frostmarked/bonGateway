package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.bovine.name}", url="${application.bff.resource.bovine.url}", configuration = ClientConfiguration.class)
public interface BovineResourceApiClient extends BovineResourceApi {
}
