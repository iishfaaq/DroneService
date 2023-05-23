package com.musala.drone.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
	private Integer weight;
	
	private String picture;
	
	@ManyToOne  (fetch = FetchType.LAZY)
	@JoinColumn(name = "drone_id")
	private Drone drone;
}
