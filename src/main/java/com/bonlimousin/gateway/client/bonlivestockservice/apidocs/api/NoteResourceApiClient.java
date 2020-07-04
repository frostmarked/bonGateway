package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${noteResource.name:noteResource}", url="${noteResource.url:http://localhost:9001}", configuration = ClientConfiguration.class)
public interface NoteResourceApiClient extends NoteResourceApi {
}
