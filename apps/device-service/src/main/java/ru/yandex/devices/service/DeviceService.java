package ru.yandex.devices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.devices.client.monolith.MonolithClient;
import ru.yandex.devices.client.monolith.dto.SensorCreate;
import ru.yandex.devices.client.monolith.dto.SensorResponse;
import ru.yandex.devices.client.monolith.dto.SensorUpdate;
import ru.yandex.devices.domain.Device;
import ru.yandex.devices.dto.request.CreateDeviceRequestDto;
import ru.yandex.devices.dto.request.UpdateDeviceRequestDto;
import ru.yandex.devices.dto.response.DeviceResponseDto;
import ru.yandex.devices.dto.response.DeviceResponseWithModulesDto;
import ru.yandex.devices.exception.NotFoundException;
import ru.yandex.devices.mapper.DeviceMapper;
import ru.yandex.devices.repository.DeviceRepository;

import java.util.List;

import static ru.yandex.devices.domain.DeviceType.TEMPERATURE;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final MonolithClient monolithClient;

    public DeviceResponseDto save(CreateDeviceRequestDto requestDto) {
        if (requestDto.deviceType().equals(TEMPERATURE.name())) {
            SensorResponse sensorResponse = monolithClient.create(SensorCreate.builder()
                    .name(requestDto.name())
                    .type(TEMPERATURE.name().toLowerCase())
                    .location(requestDto.houseId().toString())
                    .unit("°C")
                    .build());

            return DeviceResponseDto.builder()
                    .id(sensorResponse.id())
                    .name(sensorResponse.name())
                    .houseId(Long.parseLong(sensorResponse.location()))
                    .deviceType(TEMPERATURE)
                    .build();
        }

        var device = deviceMapper.map(requestDto);

        var savedDevice = deviceRepository.save(device);

        return deviceMapper.map(savedDevice);
    }

    public DeviceResponseDto update(Long id, UpdateDeviceRequestDto requestDto) {
        if (requestDto.deviceType().equals(TEMPERATURE.name())) {
            SensorResponse sensorResponse = monolithClient.update(id, SensorUpdate.builder()
                    .name(requestDto.name())
                    .type(TEMPERATURE.name())
                    .location(requestDto.location())
                    .unit("°C")
                    .build());

            return DeviceResponseDto.builder()
                    .id(sensorResponse.id())
                    .name(sensorResponse.name())
                    .houseId(Long.parseLong(sensorResponse.location()))
                    .deviceType(TEMPERATURE)
                    .build();
        }

        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("не найдено устройство с id = %d", id)));

        Device updatedDevice = deviceMapper.map(requestDto, device);

        updatedDevice = deviceRepository.save(updatedDevice);

        return deviceMapper.map(updatedDevice);
    }

    public DeviceResponseDto findById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("не найдено устройство с id = %d", id)));

        return deviceMapper.map(device);
    }

    public void delete(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    public List<DeviceResponseDto> findDevicesOfHouse(Long houseId) {
        List<Device> devices = deviceRepository.findByHouseId(houseId);

        return devices.stream()
                .map(deviceMapper::map)
                .toList();
    }

    public List<DeviceResponseWithModulesDto> findDevicesOfHouseExtended(Long houseId) {
        List<Device> devices = deviceRepository.findByHouseId(houseId);

        return devices.stream()
                .map(deviceMapper::mapExtended)
                .toList();
    }
}
