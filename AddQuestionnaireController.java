package com.MiniProject.FirstEvaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.service.QuestionnaireService;

@RestController
public class AddQuestionnaireController {

	@Autowired
	private QuestionnaireService questionnaireService;

	@PostMapping("/addQuestionnaire")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String addQuestionnaire(@RequestBody Questionnaire questionnaire) {
		try {
			questionnaireService.save(questionnaire);
			return "Questionnaire Added";
		} catch (Exception e) {
			return "Bad Request";
		}
	}

}
