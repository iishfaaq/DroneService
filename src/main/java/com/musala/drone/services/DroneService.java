package com.musala.drone.services;

import java.util.List;
import java.util.Optional;

import com.musala.drone.models.Drone;

public interface DroneService {
	
	List<Drone> getDrones();
	
	Optional<Drone> getDroneBySerialNumber(String serial_number);

	Integer getBatteryLevelForDrone(String serial_numer);

}
