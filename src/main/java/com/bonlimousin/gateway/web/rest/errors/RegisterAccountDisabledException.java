package com.bonlimousin.gateway.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class RegisterAccountDisabledException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public RegisterAccountDisabledException() {
        super(ErrorConstants.DEFAULT_TYPE, "Register account is disabled", Status.FORBIDDEN);
    }
}
