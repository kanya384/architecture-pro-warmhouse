package ru.yandex.devices.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yandex.devices.client.telemetry.TelemetryClient;

@Service
@RequiredArgsConstructor
public class TelemetryService {
    private final TelemetryClient telemetryClient;

    @Scheduled(fixedRate = 1000)
    public void sendTelemetry() {
        //TODO: implement
    }
}
