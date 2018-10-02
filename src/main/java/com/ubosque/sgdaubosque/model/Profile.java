package com.ubosque.sgdaubosque.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "profile")
public class Profile implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name="pro_name",columnDefinition = "varchar")
    private String name;

    public Profile() {

    }

    public Profile(Long id) {
        this.id = id;
    }

    public Profile(String name) {
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