package com.ubosque.sgdaubosque.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name="rol_actions",columnDefinition = "jsonb")
    private String actions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_permissions",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permissions> profiles = new HashSet<>();

    public Set<Permissions> getProfiles() {
        return this.profiles;
    }

    public void setProfiles(Set<Permissions> profiles) {
        this.profiles = profiles;
    }

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