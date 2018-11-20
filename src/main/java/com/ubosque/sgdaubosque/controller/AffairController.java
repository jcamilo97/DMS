package com.ubosque.sgdaubosque.controller;

import com.ubosque.sgdaubosque.exception.ResourceNotFoundException;
import com.ubosque.sgdaubosque.model.Affair;
import com.ubosque.sgdaubosque.repository.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class AffairController {

    @Autowired
    AffairRepository affairRepository;

    @GetMapping("/affair")
    public Map<String,List<Affair>> getAllAffair() {
        List<Affair> affairs = affairRepository.findAll();
        Map<String,List<Affair>> response = new HashMap<>();
        response.put("affairs", affairs);
        return response;
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