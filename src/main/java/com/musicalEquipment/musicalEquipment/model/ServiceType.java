package com.musicalEquipment.musicalEquipment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "service_type")
public class ServiceType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();

    public void addService(Service service) {
        services.add(service);
        service.setServiceType(this);
    }

    public void removeService(Service service) {
        services.remove(service);
        service.setServiceType(null);
    }
}
