package com.ubosque.sgdaubosque.repository;

import java.util.Optional;

import com.ubosque.sgdaubosque.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByName(String profileName);
}
