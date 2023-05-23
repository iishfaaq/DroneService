package com.musala.drone.models;

import com.musala.drone.enumerators.Model;
import com.musala.drone.enumerators.State;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

	@Id
	@Size(min =  1, max =  100)
	private String serialNumber;
	
	@Enumerated(EnumType.STRING)
	private Model model;
	
	@Max(value = 500, message = "Drone weight cannot exceed 500")
	@Column (nullable = false)
	private Integer weightLimit;
	
	@Min(value = 0, message = "Battery percentage cannot be lesser than 0%")
	@Max(value = 100, message = "Battery percentage cannot exceed 100%")
	@Column (nullable = false)
	private Integer battery;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
}
