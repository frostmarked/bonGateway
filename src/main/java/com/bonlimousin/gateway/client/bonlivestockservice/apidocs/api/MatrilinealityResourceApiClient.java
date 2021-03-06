package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.matrilineality.name}", url="${application.bff.resource.matrilineality.url}", configuration = ClientConfiguration.class)
public interface MatrilinealityResourceApiClient extends MatrilinealityResourceApi {
}
