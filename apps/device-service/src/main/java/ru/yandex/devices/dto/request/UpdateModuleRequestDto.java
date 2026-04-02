package ru.yandex.devices.dto.request;

import lombok.Builder;

@Builder
public record UpdateModuleRequestDto(
        String value
) {
}
