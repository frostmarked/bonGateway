package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.bonlivestockservicekafka.name}", url="${application.bff.resource.bonlivestockservicekafka.url}", configuration = ClientConfiguration.class)
public interface BonLivestockServiceKafkaResourceApiClient extends BonLivestockServiceKafkaResourceApi {
}
