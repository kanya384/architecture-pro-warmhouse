package ru.yandex.devices.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.devices.domain.Telemetry;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TelemetryRepository extends CrudRepository<Telemetry, Long> {
    List<Telemetry> findByDeviceId(Long deviceId);

    @Query("SELECT t FROM Telemetry t WHERE t.deviceId = :deviceId AND t.timestamp BETWEEN :start AND :end")
    List<Telemetry> findByDeviceIdAndDateRange(Long deviceId,
                                               LocalDateTime start,
                                               LocalDateTime end);
}
