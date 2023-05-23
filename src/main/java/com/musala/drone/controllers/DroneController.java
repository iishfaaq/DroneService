package com.musala.drone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/drone")
	public ResponseEntity<List<Drone>> getDrones() {
		return new ResponseEntity<List<Drone>>(droneRepository.findAll(),HttpStatus.CREATED);
		
	}

}
