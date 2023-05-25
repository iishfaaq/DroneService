package com.musala.drone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drone.enumerators.State;
import com.musala.drone.models.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
	
	List<Drone> findByState(State state);

}
