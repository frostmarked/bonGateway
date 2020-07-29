package com.bonlimousin.gateway.web.problem;

import org.zalando.problem.Status;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AlertProblem {

	private String message;
	private Status status;
	private AlertProblemSeverity severity;
	private String key;
	private String params;
	
	public AlertProblem() {
		super();
	}
	
	public AlertProblem(String message, Status status, AlertProblemSeverity severity, String key, String params) {
		super();
		this.message = message;
		this.status = status;
		this.severity = severity;
		this.key = key;
		this.params = params;
	}

	public String getMessage() {
		return message;
	}
	
	public AlertProblem message(String message) {
		this.message = message;
		return this;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonSerialize(using = AlertProblemStatusSerializer.class)
	public Status getStatus() {
		return status;
	}
	
	public AlertProblem status(Status status) {
		this.status = status;
		return this;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AlertProblemSeverity getSeverity() {
		return severity;
	}
	
	public AlertProblem severity(AlertProblemSeverity severity) {
		this.severity = severity;
		return this;
	}

	public void setSeverity(AlertProblemSeverity severity) {
		this.severity = severity;
	}

	public String getKey() {
		return key;
	}
	
	public AlertProblem key(String key) {
		this.key = key;
		return this;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParams() {
		return params;
	}
	
	public AlertProblem params(String params) {
		this.params = params;
		return this;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
}
