package com.musicalEquipment.musicalEquipment.repository;

import com.musicalEquipment.musicalEquipment.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByName(String name);
}
