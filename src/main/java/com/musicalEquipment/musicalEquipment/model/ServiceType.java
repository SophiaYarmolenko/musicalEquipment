package com.musicalEquipment.musicalEquipment.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "service_type")
public class ServiceType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceSubtype> serviceSubtypes = new ArrayList<>();

    public void addServiceSubtype(ServiceSubtype serviceSubtype) {
        serviceSubtypes.add(serviceSubtype);
        serviceSubtype.setServiceType(this);
    }

    public void removeServiceSubType(ServiceSubtype serviceSubtype) {
        serviceSubtypes.remove(serviceSubtype);
        serviceSubtype.setServiceType(null);
    }
}
