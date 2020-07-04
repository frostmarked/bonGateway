package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${application.bff.resource.bonreplicaservicekafka.name}", url="${application.bff.resource.bonreplicaservicekafka.url}", configuration = ClientConfiguration.class)
public interface BonReplicaServiceKafkaResourceApiClient extends BonReplicaServiceKafkaResourceApi {
}
