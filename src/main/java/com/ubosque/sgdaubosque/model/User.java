package com.ubosque.sgdaubosque.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ubosque.sgdaubosque.model.audit.DateAudit;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "usr_email"
        })
})
public class User extends DateAudit {

    private static final long serialVersionUID = -4023299907867915572L;

    @Id
    @Column(name="usr_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @NotBlank
    @Column(name="usr_name")
    private String name;

    @Column(name="usr_last_name")
    private String lastname;

    // change at model
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_area_id", referencedColumnName = "area_id")
   
    private Area areaid;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name="usr_email")
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name="usr_password")
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_profiles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<Profile> profiles = new HashSet<>();

    public User() {

    }

    public User(String name, String lastname, String email, String password) {
        //this.id = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Area area, Set<Profile> profile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.areaid = area;
        this.profiles = profile;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Area getArea(){
        return areaid;
    }
    
    public void setArea(Area a){
        this.areaid = a;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> roles) {
        this.profiles = roles;
    }
}