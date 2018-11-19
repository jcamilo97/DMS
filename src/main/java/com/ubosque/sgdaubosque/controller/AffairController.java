package com.ubosque.sgdaubosque.controller;

import java.util.List;

import javax.validation.Valid;

import com.ubosque.sgdaubosque.exception.ResourceNotFoundException;
import com.ubosque.sgdaubosque.model.Affair;
import com.ubosque.sgdaubosque.repository.AffairRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class AffairController {

    @Autowired
    AffairRepository affairRepository;

    @GetMapping("/affair")
    @PreAuthorize("hasRole('_SUPER_ADMIN')")
    public List<Affair> getAllAffair() {
        return affairRepository.findAll();
    }

    @PostMapping("/affair")
    public Affair createAffair(@Valid @RequestBody Affair affair) {
        return affairRepository.save(affair);
    }

    @GetMapping("/affair/{id}")
    public Affair getAffairById(@PathVariable(value = "id") Long affairId) {
        return affairRepository.findById(affairId)
                .orElseThrow(() -> new ResourceNotFoundException("Affair", "id", affairId));
    }

    @PutMapping("/affair/{id}")
    public Affair updateAffair(@PathVariable(value = "id") Long affairID, @Valid @RequestBody Affair affairDetail) {

        Affair affair = affairRepository.findById(affairID)
                .orElseThrow(() -> new ResourceNotFoundException("Affair", "id", affairID));

        affair.setName(affairDetail.getName());
        Affair updatedNote = affairRepository.save(affair);
        return updatedNote;
    }

    @DeleteMapping("/affair/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long affairID) {
        Affair affair = affairRepository.findById(affairID)
                .orElseThrow(() -> new ResourceNotFoundException("Affair", "id", affairID));

        affairRepository.delete(affair);

        return ResponseEntity.ok().build();
    }
}