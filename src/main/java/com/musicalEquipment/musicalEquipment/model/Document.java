package com.musicalEquipment.musicalEquipment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    private Service service;
}
