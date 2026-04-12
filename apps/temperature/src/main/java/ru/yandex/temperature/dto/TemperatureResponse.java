package ru.yandex.temperature.dto;

import lombok.Builder;

@Builder
public record TemperatureResponse(
        Double value
) {
}
