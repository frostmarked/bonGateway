package com.bonlimousin.gateway.web.graphql.errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.zalando.problem.AbstractThrowableProblem;

import graphql.ErrorClassification;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;
import graphql.language.SourceLocation;

@SuppressWarnings("serial")
public class ProblemGraphQLError implements GraphQLError {

	private final GraphQLError error;	
	private final Map<String, Object> map;

	public ProblemGraphQLError(GraphQLError originalError) {				
		this.map = new HashMap<String, Object>();		
		if (originalError instanceof ExceptionWhileDataFetching) {
			ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) originalError;
			if (exceptionError.getException() instanceof GraphQLError) {
				this.error = (GraphQLError) exceptionError.getException();			
			} else if (exceptionError.getException() instanceof AbstractThrowableProblem) {
				this.error = originalError;
				Map<String, Object> params = ((AbstractThrowableProblem)exceptionError.getException()).getParameters();
				Optional.ofNullable(params).ifPresent(p -> map.putAll(p));				
			} else {
				this.error = originalError;				
			}
		} else {
			this.error = originalError;
		}
		Optional.ofNullable(this.error.getExtensions()).ifPresent(p -> map.putAll(p));		
	}

	@Override
	public String getMessage() {
		return this.error.getMessage();
	}

	@Override
	public List<SourceLocation> getLocations() {
		return this.error.getLocations();
	}

	@Override
	public ErrorClassification getErrorType() {
		return this.error.getErrorType();
	}
	
	@Override
	public List<Object> getPath() {
        return this.error.getPath();
    }

	@Override
	public Map<String, Object> toSpecification() {
        return GraphqlErrorHelper.toSpecification(this);
    }

	@Override
	public Map<String, Object> getExtensions() {		
		return this.map;
	}

}
