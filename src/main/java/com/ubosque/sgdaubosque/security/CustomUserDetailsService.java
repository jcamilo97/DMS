package com.ubosque.sgdaubosque.security;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ubosque.sgdaubosque.exception.ResourceNotFoundException;
import com.ubosque.sgdaubosque.model.Profile;
import com.ubosque.sgdaubosque.model.User;
import com.ubosque.sgdaubosque.payload.RolOrProfile;
import com.ubosque.sgdaubosque.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userRepository.findByNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByToken(Claims claimsUser, UUID id) {
        Gson gson = new Gson();
        User user = gson.fromJson(claimsUser.get("user").toString(),User.class);
        String array = gson.toJson(claimsUser.get("roles"));
        List<RolOrProfile> setRoles = gson.fromJson(array, new TypeToken<List<RolOrProfile>>(){}.getType());

        Set<Profile> profiles = new HashSet<>();
        profiles.add( new Profile( setRoles.get(0).getName()));
        user.setId(id);
        user.setProfiles(profiles);

        return UserPrincipal.create(user);
    }
}