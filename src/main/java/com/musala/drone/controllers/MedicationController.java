package com.musala.drone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

} 
