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
public class Drone {

	@Id
	@Size(min =  1, max =  100)
	private String serialNumber;
	
	@Enumerated(EnumType.STRING)
	private Model model;
	
	@Max(value = 500, message = "Drone weight cannot exceed 500")
	@Column (nullable = false)
	private Integer weightLimit = 0;
	
	@Min(value = 0, message = "Battery percentage cannot be lesser than 0%")
	@Max(value = 100, message = "Battery percentage cannot exceed 100%")
	@Column (nullable = false)
	private Integer battery;
	
	@Enumerated(EnumType.STRING)
	private State state = State.IDLE;

	
	public Drone() {
		super();
	}


	public Drone(@Size(min = 1, max = 100) String serialNumber, Model model,
			@Max(value = 500, message = "Drone weight cannot exceed 500") Integer weightLimit,
			@Min(value = 0, message = "Battery percentage cannot be lesser than 0%") @Max(value = 100, message = "Battery percentage cannot exceed 100%") Integer battery,
			State state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.battery = battery;
		this.state = state;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public Integer getWeightLimit() {
		return weightLimit;
	}


	public void setWeightLimit(Integer weightLimit) {
		this.weightLimit = weightLimit;
	}


	public Integer getBattery() {
		return battery;
	}


	public void setBattery(Integer battery) {
		this.battery = battery;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Drone [serialNumber=" + serialNumber + ", model=" + model + ", weightLimit=" + weightLimit
				+ ", battery=" + battery + ", state=" + state + "]";
	}
	
	
	
	
	
}
