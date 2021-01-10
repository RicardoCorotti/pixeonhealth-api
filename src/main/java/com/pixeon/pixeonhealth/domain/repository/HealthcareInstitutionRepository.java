package com.pixeon.pixeonhealth.domain.repository;

import java.util.List;

import com.pixeon.pixeonhealth.domain.model.HealthcareInstitution;

public interface HealthcareInstitutionRepository {

	List<HealthcareInstitution> listar();
	
	HealthcareInstitution buscar(String cnpj);
	
	HealthcareInstitution salvar(HealthcareInstitution healthcareInstitution);
	
	void remover(HealthcareInstitution healthcareInstitution);
	
}
