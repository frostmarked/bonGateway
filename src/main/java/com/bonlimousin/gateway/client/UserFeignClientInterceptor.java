package com.bonlimousin.gateway.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bonlimousin.gateway.config.ApplicationProperties;
import com.bonlimousin.gateway.security.jwt.TokenProvider;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class UserFeignClientInterceptor implements RequestInterceptor {
	private static final Logger log = LoggerFactory.getLogger(UserFeignClientInterceptor.class);

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER = "Bearer";

	private final ApplicationProperties applicationProperties;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private static String jwtToken = null;

	public UserFeignClientInterceptor(ApplicationProperties applicationProperties, TokenProvider tokenProvider,
			AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.applicationProperties = applicationProperties;
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER, getClientJWT()));
	}

	private String getClientJWT() {
		if (jwtToken == null || tokenProvider.validateToken(jwtToken) == false) {			
			final String username = this.applicationProperties.getBff().getClient().getUsername();
			final String password = this.applicationProperties.getBff().getClient().getPassword();
			log.debug("Creating jwt-token for user {}", username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
			jwtToken = tokenProvider.createToken(authentication, true);
		}
		return jwtToken;
	}
}
