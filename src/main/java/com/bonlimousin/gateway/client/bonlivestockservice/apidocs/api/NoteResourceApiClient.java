package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.note.name}", url="${application.bff.resource.note.url}", configuration = ClientConfiguration.class)
public interface NoteResourceApiClient extends NoteResourceApi {
}
