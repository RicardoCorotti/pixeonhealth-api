package com.pixeon.pixeonhealth.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.pixeon.pixeonhealth.domain.model.HealthcareInstitution;
import com.pixeon.pixeonhealth.domain.repository.HealthcareInstitutionRepository;

@Component
public class HealthcareInstitutionRepositoryImpl implements HealthcareInstitutionRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<HealthcareInstitution> listar() {
		return manager.createQuery("from HealthcareInstitution", HealthcareInstitution.class).getResultList();
	}
	
	@Override
	public HealthcareInstitution buscar(String cnpj) {
		return manager.find(HealthcareInstitution.class, cnpj);
	}
	
	@Override
	@Transactional
	public HealthcareInstitution salvar(HealthcareInstitution healthcareInstitution) {
		return manager.merge(healthcareInstitution);
	}
	
	@Override
	@Transactional
	public void remover(HealthcareInstitution healthcareInstitution) {
		healthcareInstitution = buscar(healthcareInstitution.getCnpj());
		manager.remove(healthcareInstitution);
	}
	
}
