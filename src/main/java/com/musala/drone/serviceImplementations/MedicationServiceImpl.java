package com.musala.drone.serviceImplementations;

import static com.musala.drone.constants.Constants.DRONE_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.exceptions.ExceptionMessegeCreator;
import com.musala.drone.exceptions.UserNotFoundException;
import com.musala.drone.models.Drone;
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
	
	@Autowired
	private ExceptionMessegeCreator messegeCreator;
	
	
	@Override
	public Medication createMedication(Medication medication) {
		Medication medicationResponse = medicationRepository.saveAndFlush(medication);
		return medicationResponse;
	}


	@Override
	public List<Medication> getMedications() {
		List<Medication> Medications = medicationRepository.findAll();
		return Medications;
	}


	@Override
	public Optional<Medication> getMedicationByCode(String code) {
		Optional<Medication> Medication = medicationRepository.findById(code);
		if(!Medication.isPresent()) {
			 throw new UserNotFoundException(messegeCreator.createMessage(DRONE_NOT_FOUND));
		} 
		else return Medication;
	}

}
