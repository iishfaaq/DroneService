package com.musala.drone.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drone.models.Drone;
import com.musala.drone.models.Medication;
import com.musala.drone.services.MedicationService;


import jakarta.validation.Valid;

@RestController
public class MedicationController {
	
	
	@Autowired
	private MedicationService medicationService;
	
	@PostMapping("/medication")
	public ResponseEntity<Medication> createMedication(@RequestBody @Valid Medication medication){
			Medication medicationResponse = medicationService.createMedication(medication);
		return new ResponseEntity<Medication>(medicationResponse,HttpStatus.CREATED);
	}
	@GetMapping("/medication")
	public ResponseEntity<List<Medication>> getDrones() {
		return new ResponseEntity<List<Medication>>(medicationService.getMedications(),HttpStatus.OK);	
	}
	
	@GetMapping("/medication/{code}")
	public ResponseEntity<Optional<Medication>> getDroneBySerialNumber(@PathVariable String code){
			return new ResponseEntity<Optional<Medication>>(medicationService.getMedicationByCode(code),HttpStatus.OK);	
	}

} 
