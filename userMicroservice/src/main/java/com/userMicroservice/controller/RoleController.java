package com.userMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.userMicroservice.dto.RoleDTO;
import com.userMicroservice.exception.ResourceNotFoundException;
import com.userMicroservice.service.RoleServiceImpl;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins ="http://localhost:3000")

public class RoleController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDTO>> getAllRoles(){
		List<RoleDTO> roles=roleService.getAllRoles();
		return ResponseEntity.ok(roles);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Long id ) throws ResourceNotFoundException{
		RoleDTO role=roleService.getRoleById(id);
		return ResponseEntity.ok(role);
	}
	
	@PostMapping("/")
	public ResponseEntity<String> createRole(@RequestBody RoleDTO roleDTO)
	{
		roleService.createRole(roleDTO);
		return ResponseEntity.ok("role created successfully");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateRole(@PathVariable("id") Long id,@RequestBody RoleDTO roleDTO ) throws ResourceNotFoundException{
		roleService.updateRole(id,roleDTO);
		return ResponseEntity.ok("role updated successfully");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable("id") Long id ) throws ResourceNotFoundException
	{
		roleService.deleteRole(id);
		return ResponseEntity.ok("deleted");
	}
	
	@PostMapping("/{userId}/{roleId}")
	public ResponseEntity<String> assignRoleToUser(@PathVariable("userId") Long userId,@PathVariable("roleId") Long roleId) throws ResourceNotFoundException{
		roleService.assignRoleToUser(userId,roleId);
		return ResponseEntity.ok("Role assigned to user");
	}
	
	@DeleteMapping("/{userId}/{roleId}")
	public ResponseEntity<String> removeRoleFromUser(@PathVariable("userId") Long userId,@PathVariable("roleId") Long roleId) throws ResourceNotFoundException{
		roleService.removeRoleFromUser(userId,roleId);
		return ResponseEntity.ok("Role removed");
	}
}
