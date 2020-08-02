package com.bonlimousin.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.bonlimousin.gateway.asyncmessaging.EntityChangeVO;

@Configuration
public class KafkaConfiguration {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Primary
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.getConsumerProps());
	}

	@Primary
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, EntityChangeVO> entityChangeConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.getConsumerProps(), null,
				new JsonDeserializer<>(EntityChangeVO.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EntityChangeVO> entityChangeKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EntityChangeVO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(entityChangeConsumerFactory());
		return factory;
	}
}
