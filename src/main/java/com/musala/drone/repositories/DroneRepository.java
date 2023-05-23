package com.musala.drone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drone.models.Drone;

public interface DroneRepository extends JpaRepository<Drone, String> {

}
