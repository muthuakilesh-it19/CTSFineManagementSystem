package com.violationMicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.violationMicroservice.dto.ViolationDTO;
import com.violationMicroservice.exception.ResourceNotFoundException;
import com.violationMicroservice.model.Violation;
import com.violationMicroservice.repository.UserRepository;
import com.violationMicroservice.repository.VehicleRepository;
import com.violationMicroservice.repository.ViolationRepository;

class ViolationServiceImplTest {

    @Mock
    private ViolationRepository violationRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserRepository userRepository;

    private ViolationServiceImpl violationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       // violationService = new ViolationServiceImpl(violationRepository, vehicleRepository, userRepository);
    }

    @Test
    void testCreateViolation() throws ResourceNotFoundException {
        // Prepare mock data
        ViolationDTO violationDTO = new ViolationDTO();
        // Set the necessary properties of violationDTO

        Violation violation = new Violation();
        // Set the necessary properties of violation

        // Mock the repository method
        when(violationRepository.save(violation)).thenReturn(violation);

        // Call the service method
        ViolationDTO createdViolationDTO = violationService.createViolation(violationDTO);

        // Verify the repository method was called with the correct parameter
        verify(violationRepository, times(1)).save(violation);

        // Assert the returned DTO matches the expected result
        assertEquals(violation.getId(), createdViolationDTO.getId());
        // Assert other properties as needed
    }

    @Test
    void testGetViolationById() throws ResourceNotFoundException {
        // Prepare mock data
        Long id = 1L;
        Violation violation = new Violation();
        // Set the necessary properties of violation

        // Mock the repository method
        when(violationRepository.findById(id)).thenReturn(Optional.of(violation));

        // Call the service method
        ViolationDTO violationDTO = violationService.getViolationById(id);

        // Verify the repository method was called with the correct parameter
        verify(violationRepository, times(1)).findById(id);

        // Assert the returned DTO matches the expected result
        assertEquals(violation.getId(), violationDTO.getId());
        // Assert other properties as needed
    }


}