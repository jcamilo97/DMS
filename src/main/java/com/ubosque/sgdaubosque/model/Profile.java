package com.ubosque.sgdaubosque.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Permissions> permissions = new HashSet<>();

    public Profile() {

    }

    public Profile(Long id) {
        this.id = id;
    }

    public Profile(String name) {
        this.name = name;
    }

    public Profile(String name, String actions) {
        this.name = name;
        this.actions = actions;
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


    public String getActions() {
        return this.actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Set<Permissions> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

}