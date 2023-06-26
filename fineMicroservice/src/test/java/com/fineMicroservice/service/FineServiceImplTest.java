package com.fineMicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fineMicroservice.dto.FineDTO;
import com.fineMicroservice.dto.ResponseFineDTO;
import com.fineMicroservice.exception.ResourceNotFoundException;
import com.fineMicroservice.model.Fine;
import com.fineMicroservice.repository.FineRepository;
import com.fineMicroservice.repository.UserRepository;
import com.fineMicroservice.repository.VehicleRepository;

class FineServiceImplTest {

    @Mock
    private FineRepository fineRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VehicleRepository vehicleRepository;

    private FineServiceImpl fineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //fineService = new FineServiceImpl(fineRepository, userRepository, vehicleRepository);
    }

    @Test
    void testCreateFine() throws ResourceNotFoundException {
        // Prepare mock data
        FineDTO fineDTO = new FineDTO();
        // Set the necessary properties of fineDTO

        Fine fine = new Fine();
        // Set the necessary properties of fine

        // Mock the repository method
        when(fineRepository.save(fine)).thenReturn(fine);

        // Call the service method
        ResponseFineDTO result = fineService.createFine(fineDTO);

        // Verify the result
        assertEquals(fine.getId(), result.getId());
        // Assert other properties as needed
    }

    @Test
    void testGetFineById() throws ResourceNotFoundException {
        // Prepare mock data
        Long fineId = 1L;
        Fine fine = new Fine();
        // Set the necessary properties of fine
        fine.setId(fineId);

        // Mock the repository method
        when(fineRepository.findById(fineId)).thenReturn(Optional.of(fine));

        // Call the service method
        ResponseFineDTO result = fineService.getFineById(fineId);

        // Verify the result
        assertEquals(fine.getId(), result.getId());
        // Assert other properties as needed
    }

    // Write similar tests for other methods (updateFine, deleteFine, getFinesByUserId, getFinesCountById, getFinesByVehicleId)
}
