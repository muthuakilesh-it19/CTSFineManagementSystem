package com.violationMicroservice.service;
import com.violationMicroservice.dto.ViolationDTO;
import com.violationMicroservice.exception.ResourceAlreadyExistException;
import com.violationMicroservice.exception.ResourceNotFoundException;
import com.violationMicroservice.model.Violation;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface ViolationService {
	public ViolationDTO createViolation(ViolationDTO violationDTO) throws ResourceNotFoundException;
	public ViolationDTO getViolationById(Long id)throws ResourceNotFoundException;
	public List<ViolationDTO> getAllViolations();
    public List<ViolationDTO> getViolationsByVehicleId(Long vehicleId);
    public ViolationDTO updateViolation(Long id, ViolationDTO violationDTO) throws ResourceNotFoundException;
    public void deleteViolation(Long id);
    public List<ViolationDTO> getViolationsByUserId(Long userId) throws ResourceNotFoundException;
}
