package ru.yandex.devices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.yandex.devices.domain.Telemetry;
import ru.yandex.devices.dto.request.CreateTelemetryRequestDto;
import ru.yandex.devices.dto.response.TelemetryResponseDto;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TelemetryMapper {
    Telemetry map(CreateTelemetryRequestDto requestDto);

    TelemetryResponseDto map(Telemetry telemetry);
}
