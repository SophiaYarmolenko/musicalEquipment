package com.musicalEquipment.musicalEquipment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @JsonIgnore
    @ManyToOne
    private Service service;
}
