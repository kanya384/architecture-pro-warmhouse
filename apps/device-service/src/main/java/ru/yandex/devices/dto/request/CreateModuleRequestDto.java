package ru.yandex.devices.dto.request;

import lombok.Builder;

@Builder
public record CreateModuleRequestDto(
        String unit,
        String value,
        Long deviceId
) {
}
