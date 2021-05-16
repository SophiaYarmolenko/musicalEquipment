package com.musicalEquipment.musicalEquipment.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private ServiceSubtype serviceSubtype;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document) {
        documents.add(document);
        document.setService(this);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
        document.setService(null);
    }
}