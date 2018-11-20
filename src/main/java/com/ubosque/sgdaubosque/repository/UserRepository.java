package com.ubosque.sgdaubosque.repository;

import com.ubosque.sgdaubosque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNameOrEmail(String name, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findById(UUID id);

    //Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT u.id, CONCAT(u.name, ' ', u.lastname) as name FROM User u")
    List<User> findAllUsers();
}