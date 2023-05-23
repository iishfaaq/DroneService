package com.musala.drone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drone.models.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

}
