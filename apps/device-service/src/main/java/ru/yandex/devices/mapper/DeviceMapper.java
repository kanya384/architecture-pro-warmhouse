package ru.yandex.devices.mapper;

import org.mapstruct.*;
import ru.yandex.devices.domain.Device;
import ru.yandex.devices.dto.request.CreateDeviceRequestDto;
import ru.yandex.devices.dto.request.UpdateDeviceRequestDto;
import ru.yandex.devices.dto.response.DeviceResponseDto;
import ru.yandex.devices.dto.response.DeviceResponseWithModulesDto;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DeviceMapper {
    Device map(CreateDeviceRequestDto requestDto);

    Device map(UpdateDeviceRequestDto requestDto, @MappingTarget Device device);

    DeviceResponseDto map(Device device);

    DeviceResponseWithModulesDto mapExtended(Device device);
}
