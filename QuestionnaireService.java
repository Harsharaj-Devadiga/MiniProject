package com.MiniProject.FirstEvaluation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.repository.QuestionnaireRepository;

@Service
public class QuestionnaireService {

	@Autowired
	private QuestionnaireRepository repo;
	
	public Questionnaire save(Questionnaire questionnaire) {
		return repo.save(questionnaire);
	}
	
	public Optional<Questionnaire> findById(int id){
	        return repo.findById(id);
	}
}
