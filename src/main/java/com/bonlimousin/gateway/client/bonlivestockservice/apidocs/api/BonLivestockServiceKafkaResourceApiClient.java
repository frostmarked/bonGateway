package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.ClientConfiguration;

@FeignClient(name="${bonLivestockServiceKafkaResource.name:bonLivestockServiceKafkaResource}", url="${bonLivestockServiceKafkaResource.url:http://localhost:9001}", configuration = ClientConfiguration.class)
public interface BonLivestockServiceKafkaResourceApiClient extends BonLivestockServiceKafkaResourceApi {
}
