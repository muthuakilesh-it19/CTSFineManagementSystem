package com.violationMicroservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.violationMicroservice.dto.ViolationDTO;
import com.violationMicroservice.service.ViolationService;

class ViolationControllerTest {

    @Mock
    private ViolationService violationService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       // mockMvc = MockMvcBuilders.standaloneSetup(new ViolationController(violationService))
         //       .build();
    }

    @Test
    void testCreateViolation() throws Exception {
        // Prepare mock data
        ViolationDTO violationDTO = new ViolationDTO();
        // Set the necessary properties of violationDTO

        ViolationDTO createdViolation = new ViolationDTO();
        // Set the necessary properties of createdViolation

        // Mock the service method
        when(violationService.createViolation(violationDTO)).thenReturn(createdViolation);

        // Perform the HTTP POST request
        mockMvc.perform(post("/violation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(violationDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdViolation.getId()))
                // Assert other properties as needed
                .andReturn();
    }

    @Test
    void testGetViolationById() throws Exception {
        // Prepare mock data
        Long id = 1L;
        ViolationDTO violationDTO = new ViolationDTO();
        // Set the necessary properties of violationDTO

        // Mock the service method
        when(violationService.getViolationById(id)).thenReturn(violationDTO);

        // Perform the HTTP GET request
        mockMvc.perform(get("/violation/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(violationDTO.toString()))
                .andReturn();
    }

    @Test
    void testGetViolationsByUserId() throws Exception {
        // Prepare mock data
        Long userId = 1L;
        List<ViolationDTO> violations = new ArrayList<>();
        // Add some violationDTO objects to the violations list

        // Mock the service method
        when(violationService.getViolationsByUserId(userId)).thenReturn(violations);

        // Perform the HTTP GET request
        mockMvc.perform(get("/violation/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.toJson(violations)))
                .andReturn();
    }
    public class JsonUtil {

        private static final ObjectMapper objectMapper = new ObjectMapper();

        public static String toJson(Object object) throws JsonProcessingException {
            return objectMapper.writeValueAsString(object);
        }
    }

}
