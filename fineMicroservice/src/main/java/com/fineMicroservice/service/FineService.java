package com.fineMicroservice.service;

import java.util.List;

import com.fineMicroservice.dto.FineDTO;
import com.fineMicroservice.dto.FineNewDTO;
import com.fineMicroservice.dto.ResponseFineDTO;
import com.fineMicroservice.exception.ResourceNotFoundException;

public interface FineService {
	public ResponseFineDTO createFine(FineDTO fineDTO) throws ResourceNotFoundException;
	public ResponseFineDTO getFineById(Long fineId) throws ResourceNotFoundException;
	public ResponseFineDTO updateFine(Long fineId,FineNewDTO updatedFineDTO) throws ResourceNotFoundException;
	public void deleteFine(Long fineId) throws ResourceNotFoundException;
	public List<ResponseFineDTO>getFinesByUserId(Long userId);
	public List<ResponseFineDTO>getFinesByVehicleId(Long vehicleId);


}
