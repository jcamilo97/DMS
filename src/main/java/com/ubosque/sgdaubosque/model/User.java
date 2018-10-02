package com.ubosque.sgdaubosque.model;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ubosque.sgdaubosque.model.audit.DateAudit;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "usr_email"
        })
})
public class User extends DateAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="usr_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name="usr_name")
    private String name;

    @Column(name="usr_last_name")
    private String lastName;

    // change at model
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_area_id", referencedColumnName = "area_id")
    @NotBlank
    private Area areaID;

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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Area area, Set<Profile> profile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.areaID = area;
        this.profiles = profile;
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

    public Set<Profile> getRoles() {
        return profiles;
    }

    public void setRoles(Set<Profile> roles) {
        this.profiles = roles;
    }
}