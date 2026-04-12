package ru.yandex.devices.client.telemetry;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.yandex.devices.client.telemetry.dto.CreateTelemetryRequestDto;
import ru.yandex.devices.client.telemetry.dto.TelemetryResponseDto;

@RequiredArgsConstructor
@Component
public class TelemetryClient {
    @Value("${app.telemetry-url}")
    private String url;
    private final RestClient restClient;

    public TelemetryResponseDto create(CreateTelemetryRequestDto request) {
        return restClient
                .post()
                .uri(url)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(request)
                .retrieve()
                .body(TelemetryResponseDto.class);
    }
}
