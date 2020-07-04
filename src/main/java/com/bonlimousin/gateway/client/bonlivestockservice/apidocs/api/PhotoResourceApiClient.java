package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.photo.name}", url="${application.bff.resource.photo.url}", configuration = ClientConfiguration.class)
public interface PhotoResourceApiClient extends PhotoResourceApi {
}
