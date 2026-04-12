package ru.yandex.devices.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ModuleResponseDto(
        Long id,
        String unit,
        String value,
        LocalDateTime lastUpdatedAt,
        LocalDateTime createdAt
) {
}
