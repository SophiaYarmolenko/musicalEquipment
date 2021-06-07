package com.musicalEquipment.musicalEquipment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "service")
@AllArgsConstructor
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

    @JsonIgnore
    @ManyToOne
    private ServiceType serviceType;

    public Service() {

    }

    public List<Document> getDocuments() {
        return documents;
    }

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