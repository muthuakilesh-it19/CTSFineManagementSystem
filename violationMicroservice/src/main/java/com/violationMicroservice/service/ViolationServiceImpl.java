package com.violationMicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.violationMicroservice.dto.ViolationDTO;
import com.violationMicroservice.exception.ResourceNotFoundException;
import com.violationMicroservice.model.User;
import com.violationMicroservice.model.Vehicle;
import com.violationMicroservice.model.Violation;
import com.violationMicroservice.repository.UserRepository;
import com.violationMicroservice.repository.VehicleRepository;
import com.violationMicroservice.repository.ViolationRepository;

@Service
public class ViolationServiceImpl implements ViolationService {

	@Autowired
	private ViolationRepository violationRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private UserRepository userRepository;
    
	public ViolationDTO createViolation(ViolationDTO violationDTO) throws ResourceNotFoundException {
        Violation violation = convertToEntity(violationDTO);
        Violation createdViolation = violationRepository.save(violation);
        return convertToViolationDTO(createdViolation);
    }

    public ViolationDTO getViolationById(Long id) throws ResourceNotFoundException {
        Violation violation = violationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Violation not found"));
        return convertToViolationDTO(violation);
    }
    
    
    public List<ViolationDTO> getAllViolations() {
        List<Violation> violations = violationRepository.findAll();
        return violations.stream()
                .map(this::convertToViolationDTO)
                .collect(Collectors.toList());
    }
    
    
    public List<ViolationDTO> getViolationsByVehicleId(Long vehicleId) {
        List<Violation> violations = violationRepository.findByVehicleId(vehicleId);
        return violations.stream()
                .map(this::convertToViolationDTO)
                .collect(Collectors.toList());
    }
    public List<ViolationDTO> getViolationsByUserId(Long userId) throws ResourceNotFoundException {
    	Optional<User> optionalUser = userRepository.findById(userId);
    	User user=optionalUser.orElseThrow(()->new ResourceNotFoundException("User not found"));
    	List<Vehicle> vehicles=user.getVehicles();
        List<Violation> violations =new ArrayList<>();
        for(Vehicle vehicle:vehicles)
        {
        	List<Violation> vehicleViolations=violationRepository.findByVehicle(vehicle);
        	violations.addAll(vehicleViolations);
        }
        return violations.stream()
                .map(this::convertToViolationDTO)
                .collect(Collectors.toList());
    }
    
    public ViolationDTO updateViolation(Long id, ViolationDTO violationDTO) throws ResourceNotFoundException {
        Violation violation = violationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Violation not found"));
        // Update violation properties
        violation.setViolationType(violationDTO.getViolationType());
        violation.setDate(violationDTO.getDate());
        violation.setLocation(violationDTO.getLocation());

        Violation updatedViolation = violationRepository.save(violation);
        return convertToViolationDTO(updatedViolation);
    }
    
    public void deleteViolation(Long id) {
        violationRepository.deleteById(id);
    }
    
    private ViolationDTO convertToViolationDTO(Violation violation) {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setId(violation.getId());
        violationDTO.setViolationType(violation.getViolationType());
        violationDTO.setDate(violation.getDate());
        violationDTO.setLocation(violation.getLocation());
        violationDTO.setPlateNumber(violation.getVehicle().getPlateNumber());
        return violationDTO;
}

//    private List<ViolationDTO> mapViolationListToDTOList(List<Violation> violations) {
//        return violations.stream()
//                .map(violation ->convertToViolationDTO(violation))
//                .collect(Collectors.toList());
//    }
    private Violation convertToEntity(ViolationDTO violationDTO) throws ResourceNotFoundException {
        Violation violation = new Violation();
        violation.setId(violationDTO.getId());
        violation.setViolationType(violationDTO.getViolationType());
        violation.setDate(violationDTO.getDate());
        violation.setLocation(violationDTO.getLocation());
        Vehicle vehicle= vehicleRepository.findByPlateNumber(violationDTO.getPlateNumber())
				.orElseThrow(()-> new ResourceNotFoundException("Vehicle not found"));
		violation.setVehicle(vehicle);
        return violation;
    }

}
