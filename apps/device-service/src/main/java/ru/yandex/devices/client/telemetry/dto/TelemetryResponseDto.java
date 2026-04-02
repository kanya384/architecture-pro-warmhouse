package ru.yandex.devices.client.telemetry.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TelemetryResponseDto(
        Long deviceId,
        String unit,
        String value,
        LocalDateTime timestamp
) {
}
