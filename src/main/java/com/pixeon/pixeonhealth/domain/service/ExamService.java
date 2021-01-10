package com.pixeon.pixeonhealth.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pixeon.pixeonhealth.domain.exception.EntidadeNaoEncontradaException;
import com.pixeon.pixeonhealth.domain.exception.SaldoInsuficienteException;
import com.pixeon.pixeonhealth.domain.model.Exam;
import com.pixeon.pixeonhealth.domain.model.HealthcareInstitution;
import com.pixeon.pixeonhealth.domain.repository.ExamRepository;
import com.pixeon.pixeonhealth.domain.repository.HealthcareInstitutionRepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private HealthcareInstitutionRepository healthcareInstitutionRepository;

	public Exam adicionar(Exam exam) {
		
		HealthcareInstitution healthcareInstitution = healthcareInstitutionRepository.buscar(exam.getHealthcareInstitution().getCnpj());
		
		if (healthcareInstitution.getCoinsBalance() != null && healthcareInstitution.getCoinsBalance() > 0) {
			
			exam = examRepository.salvar(exam);
			healthcareInstitution.setCoinsBalance(healthcareInstitution.getCoinsBalance() - 1);
			healthcareInstitutionRepository.salvar(healthcareInstitution);
		} else {
			throw new SaldoInsuficienteException("Saldo Insuficiente para upload de exames! Favor verificar");
			
		}
		
		return exam;
				
	}
	
	public Exam buscar(Long examId) {

		Exam exam;
		
		try {
			exam = examRepository.buscar(examId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Exame %d não foi encontrado!", examId));
		}
		
		HealthcareInstitution healthcareInstitution = healthcareInstitutionRepository.buscar(exam.getHealthcareInstitution().getCnpj());
		
		if ("S".equals(exam.getQueryAlreadyCharged())) {
			//Consultas a exames que já foram feitas/cobradas não são cobradas novamente.
			return exam;
			
	    } else if (healthcareInstitution.getCoinsBalance() != null && healthcareInstitution.getCoinsBalance() > 0) {
			
			healthcareInstitution.setCoinsBalance(healthcareInstitution.getCoinsBalance() - 1);
			healthcareInstitutionRepository.salvar(healthcareInstitution);
			
			exam.setQueryAlreadyCharged("S");
			exam = examRepository.salvar(exam);
			return exam;
			
		} else {
			throw new SaldoInsuficienteException("Saldo Insuficiente para consulta de exame! Favor verificar");
			
		}
				
	}
	
	public Exam atualizar(Long examId, Exam examNewValues) {
		
		Exam examInDataBase;
		
		try {
			examInDataBase = examRepository.buscar(examId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Exame %d não foi encontrado!", examId));
		}
		
		BeanUtils.copyProperties(examNewValues, examInDataBase, "id");	
		return examRepository.salvar(examInDataBase);
		
	}
	
	public void excluir(Long examId) {
		try {
			examRepository.remover(examId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Exame %d não foi encontrado!", examId));
		}
	}
	
}
