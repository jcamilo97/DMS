package com.ubosque.sgdaubosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubosque.sgdaubosque.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
}
