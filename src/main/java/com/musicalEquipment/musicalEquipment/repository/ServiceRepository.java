package com.musicalEquipment.musicalEquipment.repository;

import com.musicalEquipment.musicalEquipment.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    Service findByName(String name);
}
