package com.meli.interview.back.subscription_api.common.exception;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonThrowableDeserializer extends JsonDeserializer<CommonThrowable> {

    @Override
    public CommonThrowable deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode traceIdNode = node.get("traceId");
        JsonNode timestampNode = node.get("timestamp");
        JsonNode errorTypeNode = node.get("errorType");
        JsonNode argsNode = node.get("args");
        JsonNode errorNode = node.get("error");

        UUID traceId = UUID.fromString(traceIdNode.asText());
        OffsetDateTime timestamp = OffsetDateTime.parse(timestampNode.asText());
        String errorType = errorTypeNode.asText();
        Map<String, Object> args;

        TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {
        };
        try (JsonParser argsParser = argsNode.traverse(jp.getCodec())) {
            args = new HashMap<>(argsParser.readValueAs(typeRef));
        }

        CommonError error = deserializeCommonError(errorNode);

        // A criação do objeto CommonAbstractThrowable pode precisar de ajustes
        // dependendo do construtor e da lógica interna da classe.
        return createCommonThrowable(error, traceId, timestamp, args, errorType);
    }

    public CommonError deserializeCommonError(JsonNode errorNode) {
        try {
            return CommonErrorCode.valueOf(errorNode.get("name").asText());
        } catch (IllegalArgumentException ex) {
            return CommonErrorCode.ERROR_GENERAL_2_000;
        }
    }

    public CommonThrowable createCommonThrowable(
            CommonError error,
            UUID traceId,
            OffsetDateTime timestamp,
            Map<String, Object> args,
            String errorType
    ) {
        return new CommonThrowable(error, traceId, timestamp, args, errorType);
    }
}