package com.meli.interview.back.subscription_api.common.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CommonThrowableSerializer extends StdSerializer<CommonThrowable> {

    public CommonThrowableSerializer() {
        this(null);
    }

    protected CommonThrowableSerializer(Class<CommonThrowable> t) {
        super(t);
    }

    @Override
    public void serialize(CommonThrowable commonThrowable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", commonThrowable.getError().getProperties().getErrorCode());
        jsonGenerator.writeStringField(
                "summary",
                CommonTranslateService.translate(commonThrowable.getError().getProperties().getSummary())
        );
        jsonGenerator.writeObjectField("args", commonThrowable.getArgs());
        jsonGenerator.writeStringField("type", commonThrowable.getErrorType());
        jsonGenerator.writeStringField("timestamp", commonThrowable.getTimestamp().toString());
        jsonGenerator.writeStringField("traceId", commonThrowable.getTraceId().toString());
        jsonGenerator.writeObjectField("cause", commonThrowable.getCause());
        jsonGenerator.writeEndObject();
    }
}