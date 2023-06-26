package com.fineMicroservice.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fineMicroservice.dto.FineCountDTO;
import com.fineMicroservice.dto.FineDTO;
import com.fineMicroservice.dto.FineNewDTO;
import com.fineMicroservice.dto.ResponseFineDTO;
import com.fineMicroservice.exception.ResourceNotFoundException;
import com.fineMicroservice.model.Fine;
import com.fineMicroservice.model.User;
import com.fineMicroservice.model.Vehicle;
import com.fineMicroservice.repository.FineRepository;
import com.fineMicroservice.repository.UserRepository;
import com.fineMicroservice.repository.VehicleRepository;

@Service
public class FineServiceImpl implements FineService {
	@Autowired
	private FineRepository fineRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	
//	public FineService(FineRepository fineRepository,UserRepository userRepository,VehicleRepository vehicleRepository)
//	{
//		this.fineRepository=fineRepository;
//		this.userRepository=userRepository;
//		this.vehicleRepository=vehicleRepository;
//	}
	
	public ResponseFineDTO createFine(FineDTO fineDTO) throws ResourceNotFoundException {
		Fine fine=mapToFineEntity(fineDTO);
		fine.setPaid(false);
		Fine createdFine=fineRepository.save(fine);
		return mapToFineDTO(createdFine);
	}
	
	public ResponseFineDTO getFineById(Long fineId) throws ResourceNotFoundException {
		Fine fine=fineRepository.findById(fineId)
				.orElseThrow(()->new ResourceNotFoundException("Fine not found"));
		return mapToFineDTO(fine);
	}
	
	public ResponseFineDTO updateFine(Long fineId,FineNewDTO updatedFineDTO) throws ResourceNotFoundException {
		Fine existingFine=fineRepository.findById(fineId)
				.orElseThrow(()->new ResourceNotFoundException("Fine not found"));
		
		existingFine.setAmount(updatedFineDTO.getAmount());
		existingFine.setDescription(updatedFineDTO.getDescription());
		existingFine.setPaid(updatedFineDTO.isPaid());
		
		Fine updatedFine=fineRepository.save(existingFine);
		return mapToFineDTO(updatedFine);
	}
	
	public void deleteFine(Long fineId) throws ResourceNotFoundException {
		Fine existingFine=fineRepository.findById(fineId)
				.orElseThrow(()->new ResourceNotFoundException("Fine not found"));
		fineRepository.delete(existingFine);
	}
	
	public List<ResponseFineDTO>getFinesByUserId(Long userId){
		List<Fine> fines=fineRepository.findByUserId(userId);
		
		
		
		return fines.stream()
				.map(this::mapToFineDTO)
				.collect(Collectors.toList());
	}
	public FineCountDTO getFinesCountById(Long userId){
		List<Fine> fines=fineRepository.findByUserId(userId);
		FineCountDTO countFines=new FineCountDTO();
		int pendingCount=0;
		int paidCount=0;
		for(Fine f:fines)
		{
			if(f.isPaid()==true)
			{
				paidCount++;
			}else {
				pendingCount++;
			}
		}
		countFines.setFinesPaid(paidCount);
		countFines.setPendingFines(pendingCount);
		return countFines;
	}
	
	public List<ResponseFineDTO>getFinesByVehicleId(Long vehicleId){
		List<Fine> fines=fineRepository.findByvehicleId(vehicleId);
		return fines.stream()
				.map(this::mapToFineDTO)
				.collect(Collectors.toList());
	}
	
	private ResponseFineDTO mapToFineDTO(Fine fine) {
		ResponseFineDTO fineDTO=new ResponseFineDTO();
		fineDTO.setId(fine.getId());
		fineDTO.setAmount(fine.getAmount());
		fineDTO.setDescription(fine.getDescription());
		fineDTO.setPaid(fine.isPaid());
		LocalDate currentDate=LocalDate.now();
		
		LocalDate fineDate=fine.getDate();
		long daysBetween=ChronoUnit.DAYS.between(fineDate, currentDate);
		
		double penaltyRate=0;
		
		if(daysBetween>30 && daysBetween<50) {
			penaltyRate=30;
		}
		else if(daysBetween>50 && daysBetween<100)
			penaltyRate=50;
		else
			penaltyRate=100;
		
		fineDTO.setPenalty(penaltyRate);
		
		//fineDTO.setUserId(fine.getUser().getId());
		//fineDTO.setVehicleId(fine.getVehicle().getId());
		return fineDTO;
		
	}
	
	private Fine mapToFineEntity(FineDTO fineDTO) throws ResourceNotFoundException
	{
		Fine fine=new Fine();
		fine.setAmount(fineDTO.getAmount());
		fine.setDescription(fineDTO.getDescription());
		fine.setPaid(fineDTO.isPaid());
		fine.setDate(fineDTO.getDateFine());
		
		User user=userRepository.findByUserName(fineDTO.getUserName())
				.orElseThrow(()-> new ResourceNotFoundException("User not found"));
		fine.setUser(user);
		Vehicle vehicle=vehicleRepository.findByPlateNumber(fineDTO.getPlateNumber());
				
		fine.setVehicle(vehicle);
		return fine;
	}
}
