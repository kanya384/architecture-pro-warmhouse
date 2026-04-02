package ru.yandex.devices.client.monolith.dto;

import java.time.LocalDateTime;

public record SensorResponse(
        Long id,
        String name,
        String type,
        String location,
        String value,
        String unit,
        String status,
        LocalDateTime createdAt,
        LocalDateTime lastUpdated
) {
}