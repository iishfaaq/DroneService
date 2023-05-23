package com.musala.drone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drone.models.Drone;
import com.musala.drone.repositories.DroneRepository;

import jakarta.validation.Valid;

@RestController
public class DroneController {
	
	@Autowired
	private DroneRepository droneRepository;
	
	@PostMapping("/drone")
	public ResponseEntity<Drone> createDrone( @RequestBody @Valid Drone drone) {
		return new ResponseEntity<>(droneRepository.save(drone),HttpStatus.CREATED);
		
	}

}
