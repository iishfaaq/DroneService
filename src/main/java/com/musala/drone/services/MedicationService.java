package com.musala.drone.services;

import java.util.List;
import java.util.Optional;

import com.musala.drone.models.Medication;

public interface MedicationService {
	
	Medication createMedication(Medication medication);

	List<Medication> getMedications();

	Optional<Medication> getMedicationByCode(String code);

}
