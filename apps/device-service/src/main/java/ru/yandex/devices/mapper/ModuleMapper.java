package ru.yandex.devices.mapper;


import org.mapstruct.*;
import ru.yandex.devices.domain.Device;
import ru.yandex.devices.domain.Module;
import ru.yandex.devices.dto.request.CreateModuleRequestDto;
import ru.yandex.devices.dto.request.UpdateModuleRequestDto;
import ru.yandex.devices.dto.response.ModuleResponseDto;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ModuleMapper {
    @Mapping(source = "deviceId", target = "device")
    Module map(CreateModuleRequestDto requestDto);

    Module map(UpdateModuleRequestDto requestDto, @MappingTarget Module module);

    ModuleResponseDto map(Module module);

    default Device map(Long deviceId) {
        if (deviceId == null) {
            return null;
        }

        return Device.builder()
                .id(deviceId)
                .build();
    }
}
