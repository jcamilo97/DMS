package com.ubosque.sgdaubosque.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubosque.sgdaubosque.model.Profile;
import com.ubosque.sgdaubosque.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Set<Profile> profiles;

    public UserPrincipal(UUID id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities, Set<Profile> profiles) {
        this.id = id;
        this.name = name;
        this.username = username; // will be use
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.profiles = profiles;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getProfiles().stream().map(profile ->
                new SimpleGrantedAuthority(profile.getName())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getProfiles()
        );
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
