package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${matrilinealityResource.name:matrilinealityResource}", url="${matrilinealityResource.url:http://localhost:9001}", configuration = ClientConfiguration.class)
public interface MatrilinealityResourceApiClient extends MatrilinealityResourceApi {
}
