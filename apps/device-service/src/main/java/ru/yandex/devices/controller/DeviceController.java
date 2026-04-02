package ru.yandex.devices.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.devices.dto.request.CreateDeviceRequestDto;
import ru.yandex.devices.dto.request.UpdateDeviceRequestDto;
import ru.yandex.devices.dto.response.DeviceResponseDto;
import ru.yandex.devices.service.DeviceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public DeviceResponseDto save(@RequestBody @Valid CreateDeviceRequestDto requestDto) {
        return deviceService.save(requestDto);
    }

    @PatchMapping("/{id}")
    public DeviceResponseDto update(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateDeviceRequestDto requestDto) {
        return deviceService.update(id, requestDto);
    }

    @GetMapping("/{id}")
    public DeviceResponseDto findById(@PathParam(value = "id") Long id) {
        return deviceService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceResponseDto> deleteById(@PathParam(value = "id") Long id) {
        deviceService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/house/{house_id}")
    public List<?> findByHouseId(@PathParam(value = "house_id") Long houseId, @RequestParam(value = "isExtended", required = false) Boolean isExtended) {
        if (isExtended) {
            return deviceService.findDevicesOfHouseExtended(houseId);
        }

        return deviceService.findDevicesOfHouse(houseId);
    }
}
