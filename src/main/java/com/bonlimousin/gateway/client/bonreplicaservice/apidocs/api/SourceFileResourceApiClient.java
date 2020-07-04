package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.sourcefile.name}", url="${application.bff.resource.sourcefile.url}", configuration = ClientConfiguration.class)
public interface SourceFileResourceApiClient extends SourceFileResourceApi {
}
