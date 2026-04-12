package ru.yandex.devices.client.telemetry.dto;

import lombok.Builder;

@Builder
public record CreateTelemetryRequestDto(
        Long deviceId,
        String unit,
        String value
) {
}
