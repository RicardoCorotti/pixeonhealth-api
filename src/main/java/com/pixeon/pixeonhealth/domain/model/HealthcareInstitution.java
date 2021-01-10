package com.pixeon.pixeonhealth.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class HealthcareInstitution {

	@Id
	@EqualsAndHashCode.Include
	private String cnpj;
	
	private String name;
	
	private Long coinsBalance;
		
}