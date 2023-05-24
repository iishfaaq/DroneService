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
import com.musala.drone.repositories.DroneRepository;
import com.musala.drone.services.DroneService;

import jakarta.validation.Valid;

@RestController
public class DroneController {
	
	@Autowired
	private DroneService droneService;
	
	
	@PostMapping("/drone")
	public ResponseEntity<Drone> createDrone( @RequestBody @Valid Drone drone) {
		return new ResponseEntity<>(droneService.saveDrone(drone),HttpStatus.CREATED);		
	}
	
	@GetMapping("/drone")
	public ResponseEntity<List<Drone>> getDrones() {
		return new ResponseEntity<List<Drone>>(droneService.getDrones(),HttpStatus.OK);
		
	}
	
	@GetMapping("/drone/{serial_numer}")
	public ResponseEntity<Optional<Drone>> getDroneySerialNumber(@PathVariable String serial_numer){
			return new ResponseEntity<Optional<Drone>>(droneService.getDroneBySerialNumber(serial_numer),HttpStatus.OK);
		
	}
	@GetMapping("/drone/batteryLevel/{serial_numer}")
	public ResponseEntity<Integer> getBatteryLevelForDrone(@PathVariable String serial_numer){
			return new ResponseEntity<Integer>(droneService.getBatteryLevelForDrone(serial_numer),HttpStatus.OK);
		
	}

}
