package com.bonlimousin.gateway.web.rest.errors;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.zalando.problem.AbstractThrowableProblem;

import com.bonlimousin.gateway.web.problem.AlertProblem;

public class WhileFetchingDataException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	private final AlertProblem alertProblem;

	public WhileFetchingDataException(AlertProblem alertProblem) {
		super(ErrorConstants.DEFAULT_TYPE, alertProblem.getMessage(), alertProblem.getStatus(), null, null, null,
				createParameters(alertProblem));
		this.alertProblem = alertProblem;
	}

	public AlertProblem getAlertProblem() {
		return alertProblem;
	}
	
	private static Map<String, Object> createParameters(AlertProblem alertProblem) {		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("problems", ArrayUtils.toArray(alertProblem));
		return parameters;
	}
}
