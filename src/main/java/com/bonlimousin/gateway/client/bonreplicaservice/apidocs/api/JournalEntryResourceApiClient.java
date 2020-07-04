package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.journalentry.name}", url="${application.bff.resource.journalentry.url}", configuration = ClientConfiguration.class)
public interface JournalEntryResourceApiClient extends JournalEntryResourceApi {
}
