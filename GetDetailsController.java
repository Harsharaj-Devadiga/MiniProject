package com.MiniProject.FirstEvaluation.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.service.EmployeeService;
import com.MiniProject.FirstEvaluation.service.QuestionnaireService;

@RestController
public class GetDetailsController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private QuestionnaireService questionnaireService;

	@GetMapping("/getAdmins")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getAdmins() throws NoSuchElementException {
		return employeeService.findAdminDetails();
	}

	@GetMapping("/getSuperAdmins")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getSuperAdmins() throws NoSuchElementException {
		return employeeService.findSuperAdminDetails();
	}

	@GetMapping("/getQuestionnaire/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public Questionnaire getQuestionnaire(@PathVariable int questionnaire_id) throws NoSuchElementException {
		Questionnaire question = questionnaireService.findById(questionnaire_id).get();
		return question;
	}

	@GetMapping("/getQuestionnaireTitle")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getQuestionnaireTitle() throws NoSuchElementException {
		return questionnaireService.getAllTitles();
	}

}
