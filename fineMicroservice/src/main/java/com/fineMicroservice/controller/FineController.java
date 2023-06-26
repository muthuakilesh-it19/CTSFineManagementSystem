package com.fineMicroservice.controller;

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

import com.fineMicroservice.dto.FineCountDTO;
import com.fineMicroservice.dto.FineDTO;
import com.fineMicroservice.dto.FineNewDTO;
import com.fineMicroservice.dto.ResponseFineDTO;
import com.fineMicroservice.exception.ResourceNotFoundException;
import com.fineMicroservice.service.FineServiceImpl;

@RestController
@RequestMapping("/fines")
@CrossOrigin(origins ="http://localhost:3000")

public class FineController {
		@Autowired	
		private FineServiceImpl fineService;
		
		
		@PostMapping
		public ResponseEntity<ResponseFineDTO> createFine(@RequestBody FineDTO fineDTO) throws ResourceNotFoundException
		{
			ResponseFineDTO createdFineDTO=fineService.createFine(fineDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdFineDTO);
		}
		@GetMapping("/{fineId}")
		public ResponseEntity<ResponseFineDTO>getFineById(@PathVariable Long fineId) throws ResourceNotFoundException
		{
			ResponseFineDTO fineDTO=fineService.getFineById(fineId);
			return ResponseEntity.ok(fineDTO);
		}
		
		@GetMapping("/count/{userId}")
		public ResponseEntity<FineCountDTO>getFineCountById(@PathVariable Long userId) {
			FineCountDTO fineDTO=fineService.getFinesCountById(userId);
			return ResponseEntity.ok(fineDTO);
		}
		@PutMapping("/{fineId}")
		public ResponseEntity<ResponseFineDTO>updateFine(@PathVariable Long fineId,@RequestBody FineNewDTO updatedFineDTO) throws ResourceNotFoundException
		{
			ResponseFineDTO updatedFine=fineService.updateFine(fineId,updatedFineDTO);
			return ResponseEntity.ok(updatedFine);
		}
		@DeleteMapping("/{fineId}")
		public ResponseEntity<Void>deleteFine(@PathVariable Long fineId) throws ResourceNotFoundException
		{
			fineService.deleteFine(fineId);
			return ResponseEntity.noContent().build();
		}
		
		@GetMapping("/user/{userId}")
		public ResponseEntity<List<ResponseFineDTO>> getFinesByUserId(@PathVariable Long userId){
			List<ResponseFineDTO> fines=fineService.getFinesByUserId(userId);
			return ResponseEntity.ok(fines);
		}
		
		@GetMapping("/vehicle/{vehicleId}")
		public ResponseEntity<List<ResponseFineDTO>> getFinesByVehicleId(@PathVariable Long vehicleId){
			List<ResponseFineDTO> fines=fineService.getFinesByVehicleId(vehicleId);
			return ResponseEntity.ok(fines);
		}
		
}
