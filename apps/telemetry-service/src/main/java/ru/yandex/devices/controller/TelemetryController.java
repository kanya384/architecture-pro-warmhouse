package ru.yandex.devices.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.devices.dto.request.CreateTelemetryRequestDto;
import ru.yandex.devices.dto.response.TelemetryResponseDto;
import ru.yandex.devices.service.TelemetryService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telemetry")
public class TelemetryController {
    private final TelemetryService telemetryService;

    @PostMapping
    public TelemetryResponseDto save(@RequestBody @Valid CreateTelemetryRequestDto requestDto) {
        return telemetryService.save(requestDto);
    }

    @GetMapping("/{deviceId}")
    public List<TelemetryResponseDto> find(@PathParam("id") Long deviceId, @RequestParam(required = false) LocalDateTime startDate,
                                           @RequestParam(required = false) LocalDateTime endDate) {
        throw new UnsupportedOperationException("implement");
    }
}
