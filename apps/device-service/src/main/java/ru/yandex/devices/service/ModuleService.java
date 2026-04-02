package ru.yandex.devices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.devices.domain.Module;
import ru.yandex.devices.dto.request.CreateModuleRequestDto;
import ru.yandex.devices.dto.request.UpdateModuleRequestDto;
import ru.yandex.devices.dto.response.ModuleResponseDto;
import ru.yandex.devices.exception.NotFoundException;
import ru.yandex.devices.mapper.ModuleMapper;
import ru.yandex.devices.repository.ModuleRepository;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleMapper mapper;

    public ModuleResponseDto save(CreateModuleRequestDto requestDto) {
        Module module = mapper.map(requestDto);
        Module savedModule = moduleRepository.save(module);

        return mapper.map(savedModule);
    }

    public ModuleResponseDto update(Long id, UpdateModuleRequestDto requestDto) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("устройство с id = %d не найдено", id)));

        Module updatedModule = mapper.map(requestDto, module);

        return mapper.map(updatedModule);
    }

    public ModuleResponseDto findById(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("устройство с id = %d не найдено", id)));

        return mapper.map(module);
    }

    public void delete(Long moduleId) {
        moduleRepository.deleteById(moduleId);
    }
}
