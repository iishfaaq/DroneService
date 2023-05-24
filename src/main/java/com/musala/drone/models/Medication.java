package com.musala.drone.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Medication {

	@Id
	@Pattern(regexp 	= "[A-Z0-9_]+",
			 message 	= "Code can only contains numbers, uppercase letters & underscore")
	private String code;
	
	@Pattern(regexp 	= "[a-zA-Z_0-9-]+",
			 message 	= "Name can only contains numbers, letters underscore & hyphen")
	@Column(nullable = false)
	private String name;
	
	@Column (nullable = false)
	@Positive
	private Integer weight;
	
	private String picture;
	
	@ManyToOne  (fetch = FetchType.LAZY)
	@JoinColumn(name = "drone_id")
	private Drone drone;

	public Medication() {
		super();
	}

	public Medication(
			@Pattern(regexp = "[A-Z0-9_]+", message = "Code can only contains numbers, uppercase letters & underscore") String code,
			@Pattern(regexp = "[a-zA-Z_0-9-]+", message = "Name can only contains numbers, letters underscore & hyphen") String name,
			Integer weight, String picture, Drone drone) {
		super();
		this.code = code;
		this.name = name;
		this.weight = weight;
		this.picture = picture;
		this.drone = drone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	@Override
	public String toString() {
		return "Medication [code=" + code + ", name=" + name + ", weight=" + weight + ", picture=" + picture
				+ ", drone=" + drone + "]";
	}
	
	
	
	
}
