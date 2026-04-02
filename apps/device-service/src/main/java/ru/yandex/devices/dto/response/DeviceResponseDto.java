package ru.yandex.devices.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ru.yandex.devices.domain.DeviceType;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DeviceResponseDto(
        Long id,
        Long houseId,
        String name,
        String serialNumber,
        String manufacturer,
        String model,
        DeviceType deviceType
) {
}
