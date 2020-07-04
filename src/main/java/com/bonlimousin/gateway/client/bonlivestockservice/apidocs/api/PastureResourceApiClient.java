package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${pastureResource.name:pastureResource}", url="${pastureResource.url:http://localhost:9001}", configuration = ClientConfiguration.class)
public interface PastureResourceApiClient extends PastureResourceApi {
}
