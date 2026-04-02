package ru.yandex.devices.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DeviceTypeResponseDto(
        Long id,
        String name,
        LocalDateTime createdAt
) {
}
