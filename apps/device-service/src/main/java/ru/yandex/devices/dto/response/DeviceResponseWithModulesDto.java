package ru.yandex.devices.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ru.yandex.devices.domain.DeviceType;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DeviceResponseWithModulesDto(
        Long id,
        Long houseId,
        String name,
        String serialNumber,
        String manufacturer,
        String model,
        DeviceType deviceType,
        List<ModuleResponseDto> modules
) {
}
