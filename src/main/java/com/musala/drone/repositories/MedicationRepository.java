package com.musala.drone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drone.models.Drone;
import com.musala.drone.models.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

	List<Medication> findByDrone(Drone drone);
}
