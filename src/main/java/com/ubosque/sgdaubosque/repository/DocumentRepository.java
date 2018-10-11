package com.ubosque.sgdaubosque.repository;

import com.ubosque.sgdaubosque.model.Document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DocumentRepository
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    
}