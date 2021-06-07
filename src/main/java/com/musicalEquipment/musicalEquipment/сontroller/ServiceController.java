package com.musicalEquipment.musicalEquipment.сontroller;

import com.musicalEquipment.musicalEquipment.model.Service;
import com.musicalEquipment.musicalEquipment.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Service getService(@PathVariable Long id) {
        return serviceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
