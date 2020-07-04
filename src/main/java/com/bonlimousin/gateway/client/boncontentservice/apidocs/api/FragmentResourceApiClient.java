package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.fragment.name}", url="${application.bff.resource.fragment.url}", configuration = ClientConfiguration.class)
public interface FragmentResourceApiClient extends FragmentResourceApi {
}
