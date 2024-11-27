package com.meli.interview.back.subscription_api.common.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CommonErrorSerializer extends StdSerializer<CommonError> {

    public CommonErrorSerializer() {
        super(CommonError.class);
    }

    public CommonErrorSerializer(Class<CommonError> t) {
        super(t);
    }

    @Override
    public void serialize(CommonError commonError, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", commonError.toString());
        jsonGenerator.writeNumberField("code", commonError.getProperties().getErrorCode());
        jsonGenerator.writeStringField("summary", commonError.getProperties().getSummary());
        jsonGenerator.writeObjectFieldStart("httpError");
        jsonGenerator.writeStringField("error", commonError.getProperties().getCommonHttpError().name());
        jsonGenerator.writeNumberField("code", commonError.getProperties().getCommonHttpError().getHttpErrorCode());
        jsonGenerator.writeEndObject();
    }
}
