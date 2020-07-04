package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.blup.name}", url="${application.bff.resource.blup.url}", configuration = ClientConfiguration.class)
public interface BlupResourceApiClient extends BlupResourceApi {
}
