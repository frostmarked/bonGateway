package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.localized.name}", url="${application.bff.resource.localized.url}", configuration = ClientConfiguration.class)
public interface LocalizedResourceApiClient extends LocalizedResourceApi {
}
