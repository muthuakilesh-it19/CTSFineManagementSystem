package com.fineMicroservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineMicroservice.dto.FineDTO;
import com.fineMicroservice.dto.ResponseFineDTO;
import com.fineMicroservice.service.FineServiceImpl;

class FineControllerTest {

    @Mock
    private FineServiceImpl fineService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       // FineController fineController = new FineController(fineService);
       // mockMvc = MockMvcBuilders.standaloneSetup(fineController).build();
    }

    @Test
    void testCreateFine() throws Exception {
        // Prepare mock data
        FineDTO fineDTO = new FineDTO();
        // Set the necessary properties of fineDTO

        ResponseFineDTO createdFineDTO = new ResponseFineDTO();
        // Set the necessary properties of createdFineDTO

        // Mock the service method
        when(fineService.createFine(fineDTO)).thenReturn(createdFineDTO);

        // Perform the POST request
        mockMvc.perform(post("/fines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fineDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdFineDTO.getId()))
                // Assert other properties as needed
                .andReturn();
    }

    @Test
    void testGetFineById() throws Exception {
        // Prepare mock data
        Long fineId = 1L;
        ResponseFineDTO fineDTO = new ResponseFineDTO();
        // Set the necessary properties of fineDTO

        // Mock the service method
        when(fineService.getFineById(fineId)).thenReturn(fineDTO);

        // Perform the GET request
        mockMvc.perform(get("/fines/{fineId}", fineId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(fineDTO.getId()))
                // Assert other properties as needed
                .andReturn();
    }

    // Write similar tests for other methods (getFineCountById, updateFine, deleteFine, getFinesByUserId, getFinesByVehicleId)

    // Helper method to convert objects to JSON string
    private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}