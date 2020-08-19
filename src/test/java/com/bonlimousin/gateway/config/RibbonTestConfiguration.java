package com.bonlimousin.gateway.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

@TestConfiguration
public class RibbonTestConfiguration {

	public static final int PORT = 9011;
	
	@Bean
	public ServerList<Server> ribbonServerList() {						
		return new StaticServerList<Server>(new Server("localhost", PORT));
	}	
}
