package ru.yandex.temperature.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.temperature.dto.TemperatureResponse;

import java.util.Random;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @GetMapping
    public TemperatureResponse get(@RequestParam("location") String location) {
        Random random = new Random();
        return TemperatureResponse.builder()
                .value(random.nextDouble(-20, 20))
                .build();
    }

    @GetMapping("/{id}")
    public TemperatureResponse getById(@PathParam("id") String id) {
        Random random = new Random();
        return TemperatureResponse.builder()
                .value(random.nextDouble(-20, 20))
                .build();
    }
}
