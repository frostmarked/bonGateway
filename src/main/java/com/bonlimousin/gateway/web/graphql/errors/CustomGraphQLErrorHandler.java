package com.bonlimousin.gateway.web.graphql.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> list) {
		return list.stream().map(ProblemGraphQLError::new).collect(Collectors.toList());
	}
}
