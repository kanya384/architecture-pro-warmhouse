package ru.yandex.devices.client.monolith.dto;

import lombok.Builder;

@Builder
public record SensorCreate(
        String name,
        String type, //temperature
        String location,
        String unit
) {
}