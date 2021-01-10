package com.pixeon.pixeonhealth.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.pixeon.pixeonhealth.domain.model.Exam;
import com.pixeon.pixeonhealth.domain.model.HealthcareInstitution;
import com.pixeon.pixeonhealth.domain.repository.ExamRepository;
import com.pixeon.pixeonhealth.domain.repository.HealthcareInstitutionRepository;

@Component
public class ExamRepositoryImpl implements ExamRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Exam> listar() {
		return manager.createQuery("from Exam", Exam.class).getResultList();
	}
	
	@Override
	public Exam buscar(Long id) {

		Exam exam = manager.find(Exam.class, id);

		if (exam == null) {
			throw new EmptyResultDataAccessException(1);
		}
				
		return exam;
	}
	
	@Override
	@Transactional
	public Exam salvar(Exam exam) {
		return manager.merge(exam);
	}
	
	@Override
	@Transactional
	public void remover(Long id) {

		Exam exam = manager.find(Exam.class, id);
		
		if (exam == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(exam);
	}
	
}
