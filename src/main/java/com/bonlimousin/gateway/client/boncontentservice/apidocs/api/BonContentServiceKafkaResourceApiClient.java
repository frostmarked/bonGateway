package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.boncontentservicekafka.name}", url="${application.bff.resource.boncontentservicekafka.url}", configuration = ClientConfiguration.class)
public interface BonContentServiceKafkaResourceApiClient extends BonContentServiceKafkaResourceApi {
}
