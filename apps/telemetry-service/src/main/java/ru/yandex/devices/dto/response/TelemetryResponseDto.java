package ru.yandex.devices.dto.response;

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
