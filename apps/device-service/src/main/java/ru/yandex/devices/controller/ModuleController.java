package ru.yandex.devices.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.devices.dto.request.CreateModuleRequestDto;
import ru.yandex.devices.dto.request.UpdateModuleRequestDto;
import ru.yandex.devices.dto.response.ModuleResponseDto;
import ru.yandex.devices.service.ModuleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping
    public ModuleResponseDto save(@RequestBody @Valid CreateModuleRequestDto requestDto) {
        return moduleService.save(requestDto);
    }

    @PatchMapping("/{id}")
    public ModuleResponseDto update(@PathParam(value = "id") Long id, @RequestBody @Valid UpdateModuleRequestDto requestDto) {
        return moduleService.update(id, requestDto);
    }

    @GetMapping("/{id}")
    public ModuleResponseDto findById(@PathParam(value = "id") Long id) {
        return moduleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModuleResponseDto> deleteById(@PathParam(value = "id") Long id) {
        moduleService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
