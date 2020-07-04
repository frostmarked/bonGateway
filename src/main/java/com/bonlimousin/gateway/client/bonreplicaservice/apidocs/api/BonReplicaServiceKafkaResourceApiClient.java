package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.ClientConfiguration;

@FeignClient(name="${bonReplicaServiceKafkaResource.name:bonReplicaServiceKafkaResource}", url="${bonReplicaServiceKafkaResource.url:http://localhost:9002}", configuration = ClientConfiguration.class)
public interface BonReplicaServiceKafkaResourceApiClient extends BonReplicaServiceKafkaResourceApi {
}
