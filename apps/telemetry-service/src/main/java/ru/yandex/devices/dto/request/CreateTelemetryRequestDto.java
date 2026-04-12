package ru.yandex.devices.dto.request;

import lombok.Builder;

@Builder
public record CreateTelemetryRequestDto(
        Long deviceId,
        String unit,
        String value
) {
}
