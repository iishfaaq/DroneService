package com.musala.drone.services;

import java.util.List;
import java.util.Optional;

import com.musala.drone.enumerators.State;
import com.musala.drone.models.Drone;
import com.musala.drone.models.Medication;

public interface DroneService {
	
	List<Drone> getDrones();
	
	Optional<Drone> getDroneBySerialNumber(String serial_number);

	Integer getBatteryLevelForDrone(String serial_numer);
	
	Drone saveDrone(Drone drone);
	
	List<Drone> getDronesByState(State state);

	List<Drone> getAvailableDrone();
	
	Drone updateDroneBattery(String serial_number, Integer battery);

	List<Medication> getMedicationByDrone(String serial_number);

}
