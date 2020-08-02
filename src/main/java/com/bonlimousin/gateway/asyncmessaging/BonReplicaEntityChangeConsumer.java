package com.bonlimousin.gateway.asyncmessaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.bonlimousin.gateway.config.CacheConfiguration;

@Service
@KafkaListener(	
	topics = {
		"ENTITY_CHANGE_SOURCEFILEENTITY"
	}, 
	containerFactory = "entityChangeKafkaListenerContainerFactory"
)
public class BonReplicaEntityChangeConsumer {

	private final Logger log = LoggerFactory.getLogger(BonReplicaEntityChangeConsumer.class);
	
	@Autowired
	private CacheManager cacheManager;
	
	@KafkaHandler
	public void listen(@Payload EntityChangeVO vo, 
			@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
			@Header(KafkaHeaders.RECEIVED_TOPIC) String topic, 
			@Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
		log.debug("Consumed topic {} with key {} and message {}", topic, key, vo);
		if("UPDATE".equalsIgnoreCase(key)) {
			cacheManager.getCache(CacheConfiguration.CACHE_COWS).clear();
		}
	}
}
