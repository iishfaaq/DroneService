package com.musala.drone.serviceImplementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.enumerators.State;
import com.musala.drone.exceptions.ExceptionMessegeCreator;
import com.musala.drone.exceptions.UserNotFoundException;
import com.musala.drone.models.Drone;
import com.musala.drone.models.Medication;
import com.musala.drone.repositories.DroneRepository;
import com.musala.drone.repositories.MedicationRepository;
import com.musala.drone.services.DroneService;
import com.musala.drone.services.MedicationService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import static com.musala.drone.constants.Constants.*;

@Service
@Slf4j
@Transactional
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	@Autowired
	private MedicationService medicationService;
	
	@Autowired
	private ExceptionMessegeCreator messegeCreator;
	
	@Override
	public List<Drone> getDrones() {
		return droneRepository.findAll();
	}

	@Override
	public Optional<Drone> getDroneBySerialNumber(String serial_number) {
		Optional<Drone> drone = droneRepository.findById(serial_number);
		if(!drone.isPresent()) {
			 throw new UserNotFoundException(messegeCreator.createMessage(DRONE_NOT_FOUND));
		} 
		else return drone;
	}
	
	@Override
	public Integer getBatteryLevelForDrone(String serial_number) {
		Optional<Drone> drone = this.getDroneBySerialNumber(serial_number);
		
		if(drone.isPresent()) {
			return drone.get().getBattery();
		}
		else{
			throw new UserNotFoundException(messegeCreator.createMessage(DRONE_NOT_FOUND));
		}
	}

	@Override
	public Drone saveDrone(Drone drone) {
		if(droneRepository.count() >= MAXIMUM_FLEET_LIMIT) {
			throw new IllegalArgumentException(messegeCreator.createMessage(FLEET_LIMIT_EXCEEDED));
		}
		else if(drone.getBattery() < 25 && drone.getState() == State.LOADING) {
			throw new IllegalArgumentException(messegeCreator.createMessage(LOW_BATTERY_FOR_LOADING));
		}
		else {
			Drone droneResponse = droneRepository.saveAndFlush(drone);
			return droneResponse;
		}
	}

	@Override
	public List<Drone> getDronesByState(State state) {
		List<Drone> drones = droneRepository.findByState(state);
		return drones;
	}

	@Override
	public List<Drone> getAvailableDrone() {
		List<Drone> availableDrones = new ArrayList<>();
		
		List<Drone> idleDrones = this.getDronesByState(State.IDLE);
		List<Drone> LoadingDrones = this.getDronesByState(State.LOADING);
		
		availableDrones.addAll(idleDrones);
		availableDrones.addAll(LoadingDrones);
		
		availableDrones = availableDrones.stream().filter(drone -> drone.getBattery() >= 25).collect(Collectors.toList());
		
		return availableDrones;
	}

	@Override
	public Drone updateDroneBattery(String serial_number, Integer battery) {
		Optional<Drone> drone = this.getDroneBySerialNumber(serial_number);
		
		if(!drone.isPresent()) {
			throw new UserNotFoundException(messegeCreator.createMessage(DRONE_NOT_FOUND));
		}
		else if(battery < 25 && drone.get().getState() == State.LOADING) {
			throw new IllegalArgumentException(messegeCreator.createMessage(LOW_BATTERY_FOR_LOADING));
		}
		else {
			drone.get().setBattery(battery);
			Drone droneResponse = droneRepository.saveAndFlush(drone.get());
			return droneResponse;
		}
	}

	@Override
	public List<Medication> getMedicationByDrone(String serial_number) {
		Optional<Drone> drone = this.getDroneBySerialNumber(serial_number);
		
		List<Medication> medications = medicationRepository.findByDrone(drone.get());
		
		return medications;
		
	}
	
	@Override
	public Long getremainingWeight(String serial_number) {
		Optional<Drone> drone = this.getDroneBySerialNumber(serial_number);
		
		List<Medication> exsitingMedication = this.getMedicationByDrone(serial_number);
		
		Long existingWeight = exsitingMedication.stream().mapToLong(Medication :: getWeight).sum();
		
		Long remainingWeight = drone.get().getWeightLimit() - existingWeight;
		
		return remainingWeight;
	}

	@Override
	public String loadMedicationToDrone(String serial_number, List<String> medication_code) {
		
		Long remainingWeight = this.getremainingWeight(serial_number);
		
		Optional<Drone> drone = this.getDroneBySerialNumber(serial_number);
		
		List<Medication> addingMedications = new ArrayList<>();
		
		for (String code : medication_code) {
			Optional<Medication> medication = medicationService.getMedicationByCode(code);
			addingMedications.add(medication.get());
		}
		
		Long newWeight = addingMedications.stream().mapToLong(Medication :: getWeight).sum();
		
		if(newWeight > remainingWeight) {
			throw new IllegalArgumentException(messegeCreator.createMessage(WEIGHT_EXCEEDED));
		}
		else {
			if(newWeight.longValue() < remainingWeight.longValue()) {
				drone.get().setState(State.LOADING);
			}
			else if(newWeight.equals(remainingWeight)) {
				drone.get().setState(State.LOADED);
			}
			droneRepository.saveAndFlush(drone.get());
			
			for (Medication medication : addingMedications) {
				medication.setDrone(drone.get());
			}
			
			return messegeCreator.createMessage(SUCESSFULL_LOAD);
		}
		
	}

}
