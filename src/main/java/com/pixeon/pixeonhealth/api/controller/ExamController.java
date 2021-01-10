package com.pixeon.pixeonhealth.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pixeon.pixeonhealth.domain.exception.EntidadeNaoEncontradaException;
import com.pixeon.pixeonhealth.domain.exception.SaldoInsuficienteException;
import com.pixeon.pixeonhealth.domain.model.Exam;
import com.pixeon.pixeonhealth.domain.repository.ExamRepository;
import com.pixeon.pixeonhealth.domain.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

	@Autowired
	private ExamService examService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Exam> adicionar(@RequestBody Exam exam) {
		
		try {
			exam = examService.adicionar(exam);
			return ResponseEntity.ok(exam);
		} catch (SaldoInsuficienteException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	}

	@GetMapping("/{examId}")
	public ResponseEntity<Exam> buscar(@PathVariable Long examId) {
		
		try {
			Exam exam = examService.buscar(examId);
			return ResponseEntity.ok(exam);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (SaldoInsuficienteException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	}
	
	@PutMapping("/{examId}")
	public ResponseEntity<Exam> atualizar(@PathVariable Long examId, @RequestBody Exam exam) {
		
		Exam updatedExam;
		
		try {
			updatedExam = examService.atualizar(examId, exam);
			return ResponseEntity.ok(updatedExam);			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	@DeleteMapping("/{examId}")
	public ResponseEntity<Exam> remover(@PathVariable Long examId) {
		
		try {
			examService.excluir(examId);
			return ResponseEntity.noContent().build();			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
				
	}
}