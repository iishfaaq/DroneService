package com.musala.drone.serviceImplementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.exceptions.ExceptionMessegeCreator;
import com.musala.drone.exceptions.UserNotFoundException;
import com.musala.drone.models.Drone;
import com.musala.drone.repositories.DroneRepository;
import com.musala.drone.services.DroneService;

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
	private ExceptionMessegeCreator exceptionMessegeCreator;
	
	@Override
	public List<Drone> getDrones() {
		return droneRepository.findAll();
	}

	@Override
	public Optional<Drone> getDroneBySerialNumber(String serial_number) {
		return droneRepository.findById(serial_number);
	}
	
	@Override
	public Integer getBatteryLevelForDrone(String serial_numer) {
		Optional<Drone> drone = this.getDroneBySerialNumber(serial_numer);
		
		if(drone.isPresent()) {
			return drone.get().getBattery();
		}
		else{
			throw new UserNotFoundException(exceptionMessegeCreator.createMessage(DRONE_NOT_FOUND));
		}
	}

	@Override
	public Drone saveDrone(Drone drone) {
		if(droneRepository.count() >= MAXIMUM_FLEET_LIMIT) {
			throw new IllegalArgumentException(exceptionMessegeCreator.createMessage(FLEET_LIMIT_EXCEEDED));
		}
		else {
			droneRepository.saveAndFlush(drone);
		}
	
		// TODO Auto-generated method stub
		return null;
	}


	

}
