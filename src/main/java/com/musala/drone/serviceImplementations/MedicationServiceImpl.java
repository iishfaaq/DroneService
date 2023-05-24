package com.musala.drone.serviceImplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.models.Medication;
import com.musala.drone.repositories.MedicationRepository;
import com.musala.drone.services.MedicationService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	private MedicationRepository medicationRepository;
	
	
	@Override
	public Medication createMedication(Medication medication) {
		Medication medicationResponse = medicationRepository.saveAndFlush(medication);
		return medicationResponse;
	}

}
