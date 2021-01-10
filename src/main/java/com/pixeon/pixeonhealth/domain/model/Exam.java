package com.pixeon.pixeonhealth.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	private HealthcareInstitution healthcareInstitution;
	
	private String patientName;
	
	private Long patientAge;
	
	private String patientGender;
	
	private String physicianName;
	
	private String physicianCRM;
	
	private String procedureName;
	
	private String queryAlreadyCharged;
	
}