package net.khaledez.simple_api_demo.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.Instant;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Info(
        Instant time,
        String clientAddress,
        String hostName,
        Map<String, String> headers
        ) {
}
