package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${bovineResource.name:bovineResource}", url="${bovineResource.url:http://localhost:9002}", configuration = ClientConfiguration.class)
public interface BovineResourceApiClient extends BovineResourceApi {
}
