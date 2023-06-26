package com.violationMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.violationMicroservice.dto.ViolationDTO;
import com.violationMicroservice.exception.ResourceNotFoundException;
import com.violationMicroservice.service.ViolationService;

@RestController
@RequestMapping("/violation")
@CrossOrigin(origins ="http://localhost:3000")

public class ViolationController {

	@Autowired
    private ViolationService violationService;

    

    @PostMapping
    public ResponseEntity<ViolationDTO> createViolation(@RequestBody ViolationDTO violationDTO) throws ResourceNotFoundException {
        ViolationDTO createdViolation = violationService.createViolation(violationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdViolation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getViolationById(@PathVariable Long id) throws ResourceNotFoundException {
        ViolationDTO violationDTO = violationService.getViolationById(id);
        return ResponseEntity.ok(violationDTO.toString());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ViolationDTO>> getViolationsByUserId(@PathVariable Long userId) throws ResourceNotFoundException {
        List<ViolationDTO> violations = violationService.getViolationsByUserId(userId);
        return ResponseEntity.ok(violations);
    }
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ViolationDTO>> getViolationsByVehicleId(@PathVariable Long vehicleId) {
        List<ViolationDTO> violations = violationService.getViolationsByVehicleId(vehicleId);
        return ResponseEntity.ok(violations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViolationDTO> updateViolation(@PathVariable Long id, @RequestBody ViolationDTO violationDTO) throws ResourceNotFoundException {
        ViolationDTO updatedViolation = violationService.updateViolation(id, violationDTO);
        return ResponseEntity.ok(updatedViolation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViolation(@PathVariable Long id) {
        violationService.deleteViolation(id);
        return ResponseEntity.noContent().build();
    }
}



