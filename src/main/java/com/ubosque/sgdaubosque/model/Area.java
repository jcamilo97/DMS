package com.ubosque.sgdaubosque.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "area")
public class Area implements Serializable{

    private static final long serialVersionUID = 5859375921628981852L;

    @Id
    @Column(name="area_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name="area_name",columnDefinition = "varchar")
    private String name;

    public Area() {

    }

    public Area(Long id) {
        this.id = id;
    }

    public Area(String name) {
        this.name = name;
    }

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