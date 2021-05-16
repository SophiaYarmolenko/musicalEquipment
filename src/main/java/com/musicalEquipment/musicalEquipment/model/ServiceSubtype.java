package com.musicalEquipment.musicalEquipment.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "service_sub_type")
public class ServiceSubtype {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private ServiceType serviceType;

    @OneToMany(mappedBy = "serviceSubtype", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();

    public void addService(Service service) {
        services.add(service);
        service.setServiceSubtype(this);
    }

    public void removeService(Service service) {
        services.remove(service);
        service.setServiceSubtype(null);
    }
}
