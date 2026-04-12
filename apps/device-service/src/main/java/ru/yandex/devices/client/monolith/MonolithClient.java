package ru.yandex.devices.client.monolith;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.yandex.devices.client.monolith.dto.SensorCreate;
import ru.yandex.devices.client.monolith.dto.SensorResponse;
import ru.yandex.devices.client.monolith.dto.SensorUpdate;

@RequiredArgsConstructor
@Component
public class MonolithClient {
    @Value("${app.monolith-url}")
    private String url;
    private final RestClient restClient;

    public SensorResponse create(SensorCreate request) {
        return restClient
                .post()
                .uri(url)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(request)
                .retrieve()
                .body(SensorResponse.class);
    }

    public SensorResponse update(Long id, SensorUpdate request) {
        return restClient
                .put()
                .uri(String.format("%s/%d", url, id))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(request)
                .retrieve()
                .body(SensorResponse.class);
    }
}
