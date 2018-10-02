package com.ubosque.sgdaubosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubosque.sgdaubosque.model.Affair;

@Repository
public interface AffairRepository extends JpaRepository<Affair, Long> {
}
