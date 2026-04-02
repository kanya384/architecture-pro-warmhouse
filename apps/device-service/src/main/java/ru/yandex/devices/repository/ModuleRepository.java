package ru.yandex.devices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.devices.domain.Module;

public interface ModuleRepository extends CrudRepository<Module, Long> {
}
