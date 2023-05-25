package com.musala.drone.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drone.enumerators.State;
import com.musala.drone.models.Drone;
import com.musala.drone.models.Medication;
import com.musala.drone.repositories.DroneRepository;
import com.musala.drone.services.DroneService;

import jakarta.validation.Valid;

@RestController
public class DroneController {
	
	@Autowired
	private DroneService droneService;
	
	
	@PostMapping("/drone")
	public ResponseEntity<Drone> createDrone( @RequestBody @Valid Drone drone) {
		return new ResponseEntity<Drone>(droneService.saveDrone(drone),HttpStatus.CREATED);		
	}
	
	@GetMapping("/drone")
	public ResponseEntity<List<Drone>> getDrones() {
		return new ResponseEntity<List<Drone>>(droneService.getDrones(),HttpStatus.OK);	
	}
	
	@GetMapping("/drone/{serial_number}")
	public ResponseEntity<Optional<Drone>> getDroneBySerialNumber(@PathVariable String serial_number){
			return new ResponseEntity<Optional<Drone>>(droneService.getDroneBySerialNumber(serial_number),HttpStatus.OK);	
	}
	
	@GetMapping("/drone/batteryLevel/{serial_number}")
	public ResponseEntity<Integer> getBatteryLevelForDrone(@PathVariable String serial_number){
			return new ResponseEntity<Integer>(droneService.getBatteryLevelForDrone(serial_number),HttpStatus.OK);
	}
	
	@GetMapping("/drone/state/{state}")
	public ResponseEntity<List<Drone>> getrDronesByState(@PathVariable State state){
			return new ResponseEntity<List<Drone>>(droneService.getDronesByState(state),HttpStatus.OK);
	}
	
	@GetMapping("/drone/availableDrones")
	public ResponseEntity<List<Drone>> getAvailableDrone(){
			return new ResponseEntity<List<Drone>>(droneService.getAvailableDrone(),HttpStatus.OK);
	}
	
	@PatchMapping("/drone/battery/{serial_number}")
	public ResponseEntity<Drone> updateDroneBattery(@PathVariable String serial_number, @RequestBody Integer battery){
		return new ResponseEntity<Drone>(droneService.updateDroneBattery(serial_number, battery),HttpStatus.OK);	
	}
	
	@GetMapping("/drone/medication/{serial_number}")
	public ResponseEntity<List<Medication>> getMedicationByDrone (@PathVariable String serial_number){
		return new ResponseEntity<List<Medication>>(droneService.getMedicationByDrone(serial_number),HttpStatus.OK);
	}
	
	@GetMapping("/drone/remainingWeight/{serial_number}")
	public ResponseEntity<Long> getremainingWeight (@PathVariable String serial_number){
		return new ResponseEntity<Long>(droneService.getremainingWeight(serial_number),HttpStatus.OK);
	}
	
	@PutMapping("/drone/medication/loadMedicationToDrone")
	public ResponseEntity<String> loadMedicationToDrone(@RequestParam  String serial_number, @RequestParam List<String> medication_code){
		return new ResponseEntity<String>(droneService.loadMedicationToDrone(serial_number, medication_code),HttpStatus.OK);
	
		 
	}
	
	

}
