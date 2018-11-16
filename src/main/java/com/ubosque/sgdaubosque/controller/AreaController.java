package com.ubosque.sgdaubosque.controller;

import java.util.List;

import javax.validation.Valid;

import com.ubosque.sgdaubosque.exception.ResourceNotFoundException;
import com.ubosque.sgdaubosque.model.Area;
import com.ubosque.sgdaubosque.repository.AreaRepository;

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
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/area")
    public List<Area> getAllArea() {
        return areaRepository.findAll();
    }

    @PostMapping("/area")
    @PreAuthorize("hasRole('ADMIN')")
    public Area createArea(@Valid @RequestBody Area area) {
        return areaRepository.save(area);
    }

    @GetMapping("/area/{id}")
    public Area getAreaById(@PathVariable(value = "id") Long AreaId) {
        return areaRepository.findById(AreaId)
                .orElseThrow(() -> new ResourceNotFoundException("Area", "id", AreaId));
    }

    @PutMapping("/area/{id}")
    public Area updateArea(@PathVariable(value = "id") Long areaID, @Valid @RequestBody Area areaDetail) {

        Area area = areaRepository.findById(areaID)
                .orElseThrow(() -> new ResourceNotFoundException("area", "id", areaID));

        area.setName(areaDetail.getName());
        Area updatedArea = areaRepository.save(area);
        return updatedArea;
    }

    @DeleteMapping("/area/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long areaID) {
        Area area = areaRepository.findById(areaID)
                .orElseThrow(() -> new ResourceNotFoundException("area", "id", areaID));
        // Debe actualizarse no borrar
        areaRepository.delete(area);

        return ResponseEntity.ok().build();
    }
}