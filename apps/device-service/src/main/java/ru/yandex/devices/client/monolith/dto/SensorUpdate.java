package ru.yandex.devices.client.monolith.dto;

import lombok.Builder;

@Builder
public record SensorUpdate(
        String name,
        String type, //temperature
        String location,
        String value,
        String unit,
        String status
) {
}