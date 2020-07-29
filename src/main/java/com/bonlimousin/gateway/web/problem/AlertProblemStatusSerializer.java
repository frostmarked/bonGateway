package com.bonlimousin.gateway.web.problem;

import java.io.IOException;

import org.zalando.problem.Status;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AlertProblemStatusSerializer extends StdSerializer<Status> {

	private static final long serialVersionUID = 1L;

	public AlertProblemStatusSerializer() { 
        this(null); 
    }

	public AlertProblemStatusSerializer(Class<Status> t) {
        super(t); 
    }

	@Override
	public void serialize(Status value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		gen.writeString(String.valueOf(value.getStatusCode()));
	}
}