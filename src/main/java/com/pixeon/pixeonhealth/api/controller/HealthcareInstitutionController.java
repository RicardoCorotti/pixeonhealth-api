package com.pixeon.pixeonhealth.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixeon.pixeonhealth.domain.model.HealthcareInstitution;
import com.pixeon.pixeonhealth.domain.repository.HealthcareInstitutionRepository;

@RestController
@RequestMapping("/healthcareInstitutions")
public class HealthcareInstitutionController {

	@Autowired
	private HealthcareInstitutionRepository healthcareInstitutionRepository;
	
	@GetMapping
	public List<HealthcareInstitution> listar() {
		return healthcareInstitutionRepository.listar();
	}
	
	@GetMapping("/{cnpj}")
	public ResponseEntity<HealthcareInstitution> buscar(@PathVariable String cnpj) {
		HealthcareInstitution healthcareInstitution = healthcareInstitutionRepository.buscar(cnpj);
		
		if (healthcareInstitution != null) {
			return ResponseEntity.ok(healthcareInstitution);
		} else {
			return ResponseEntity.notFound().build();
		}
				
	}
	
}