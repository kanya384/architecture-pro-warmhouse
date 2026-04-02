package ru.yandex.devices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.devices.domain.Telemetry;
import ru.yandex.devices.dto.request.CreateTelemetryRequestDto;
import ru.yandex.devices.dto.response.TelemetryResponseDto;
import ru.yandex.devices.mapper.TelemetryMapper;
import ru.yandex.devices.repository.TelemetryRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TelemetryService {
    private final TelemetryRepository telemetryRepository;
    private final TelemetryMapper mapper;

    public TelemetryResponseDto save(CreateTelemetryRequestDto requestDto) {

        Telemetry telemetry = mapper.map(requestDto);

        var telemetrySaved = telemetryRepository.save(telemetry);

        return mapper.map(telemetrySaved);
    }

    public List<TelemetryResponseDto> find(Long deviceId, LocalDateTime from, LocalDateTime to) {
        return telemetryRepository.findByDeviceIdAndDateRange(deviceId, from, to)
                .stream()
                .map(mapper::map)
                .toList();
    }
}
