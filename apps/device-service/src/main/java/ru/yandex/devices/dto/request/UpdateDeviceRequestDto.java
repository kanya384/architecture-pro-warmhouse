package ru.yandex.devices.dto.request;

import lombok.Builder;
import ru.yandex.devices.domain.Status;

@Builder
public record UpdateDeviceRequestDto(
        String name,
        String deviceType,
        Status status,
        String location
) {
}
