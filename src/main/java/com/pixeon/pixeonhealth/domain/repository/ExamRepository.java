package com.pixeon.pixeonhealth.domain.repository;

import java.util.List;

import com.pixeon.pixeonhealth.domain.model.Exam;

public interface ExamRepository {

	List<Exam> listar();
	
	Exam buscar(Long id);
	
	Exam salvar(Exam exam);
	
	void remover(Long id);
	
}