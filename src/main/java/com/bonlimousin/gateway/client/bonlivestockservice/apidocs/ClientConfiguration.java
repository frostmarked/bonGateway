package com.bonlimousin.gateway.client.bonlivestockservice.apidocs;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bonlimousin.gateway.client.encoder.JHipsterQueryMapEncoder;

import feign.QueryMapEncoder;

@Configuration
@EnableConfigurationProperties
public class ClientConfiguration {
	@Bean
	public QueryMapEncoder queryMapEncoder() {
		return new JHipsterQueryMapEncoder();
	}
}
