package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.pasture.name}", url="${application.bff.resource.pasture.url}", configuration = ClientConfiguration.class)
public interface PastureResourceApiClient extends PastureResourceApi {
}
