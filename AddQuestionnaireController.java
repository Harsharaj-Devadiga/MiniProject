package com.MiniProject.FirstEvaluation.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.response.QuestionnaireResponse;
import com.MiniProject.FirstEvaluation.service.QuestionnaireService;

@RestController
public class AddQuestionnaireController {

	@Autowired
	private QuestionnaireService questionnaireService;

	@PostMapping("/addQuestionnaire")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	public QuestionnaireResponse addQuestionnaire(@RequestBody Questionnaire questionnaire)
			throws NoSuchElementException {
		Questionnaire question = questionnaireService.save(questionnaire);
		return new QuestionnaireResponse(question.getId());
	}
}
