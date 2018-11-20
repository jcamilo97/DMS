package com.ubosque.sgdaubosque.controller;

import com.ubosque.sgdaubosque.exception.ResourceNotFoundException;
import com.ubosque.sgdaubosque.model.Profile;
import com.ubosque.sgdaubosque.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public  Map<String,List<Profile>> getAllProfile(){
        List<Profile> profiles = profileRepository.findAll();
        Map<String,List<Profile>> response = new HashMap<>();
        response.put("roles", profiles);
        return  response;
    }

    @PostMapping("/profile")
    public Profile createProfile(@Valid @RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

    @GetMapping("/profile/{id}")
    public Profile getProfileById(@PathVariable(value = "id") Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("profile", "id", profileId));
    }

    @PutMapping("/profile/{id}")
    public Profile updateProfile(@PathVariable(value = "id") Long profileID, @Valid @RequestBody Profile profileDetail) {

        Profile profile = profileRepository.findById(profileID)
                .orElseThrow(() -> new ResourceNotFoundException("profile", "id", profileID));

        profile.setName(profileDetail.getName());
        Profile updatedNote = profileRepository.save(profile);
        return updatedNote;
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long profileID) {
        Profile profile = profileRepository.findById(profileID)
                .orElseThrow(() -> new ResourceNotFoundException("profile", "id", profileID));

        profileRepository.delete(profile);

        return ResponseEntity.ok().build();
    }
}