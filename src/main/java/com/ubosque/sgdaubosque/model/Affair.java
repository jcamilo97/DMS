package com.ubosque.sgdaubosque.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "affair")
public class Affair implements Serializable{

    private static final long serialVersionUID = 8696708209266562708L;

    @Id
    @Column(name="aff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="aff_name",columnDefinition = "varchar")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}