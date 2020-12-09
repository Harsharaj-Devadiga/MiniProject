package com.MiniProject.FirstEvaluation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.response.AdminSuperAdminResponse;
import com.MiniProject.FirstEvaluation.response.QuestionnaireTitleResponse;
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
	public List<AdminSuperAdminResponse> getAdmins() throws NoSuchElementException {
		List<Employee> employee = employeeService.findAdminDetails();
		List<AdminSuperAdminResponse> adminList = new ArrayList<AdminSuperAdminResponse>();
		for (Employee emp : employee) {
			AdminSuperAdminResponse admins = new AdminSuperAdminResponse(emp.getId(), emp.getUsername(),
					emp.getEmail());
			adminList.add(admins);
		}
		return adminList;
	}

	@GetMapping("/getSuperAdmins")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public List<AdminSuperAdminResponse> getSuperAdmins() throws NoSuchElementException {
		List<Employee> employee = employeeService.findSuperAdminDetails();
		List<AdminSuperAdminResponse> superAdminList = new ArrayList<AdminSuperAdminResponse>();
		for (Employee emp : employee) {
			AdminSuperAdminResponse superAdmins = new AdminSuperAdminResponse(emp.getId(), emp.getUsername(),
					emp.getEmail());
			superAdminList.add(superAdmins);
		}
		return superAdminList;
	}

	@GetMapping("/getQuestionnaire/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	public Questionnaire getQuestionnaire(@PathVariable int questionnaire_id) throws NoSuchElementException {
		Questionnaire question = questionnaireService.findById(questionnaire_id).get();
		return question;
	}

	@GetMapping("/getQuestionnaireTitle")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	public List<QuestionnaireTitleResponse> getQuestionnaireTitle() throws NoSuchElementException {
		List<Questionnaire> questionnaire = questionnaireService.getAllTitles();
		List<QuestionnaireTitleResponse> questionnaireTitle = new ArrayList<QuestionnaireTitleResponse>();
		for (Questionnaire quest : questionnaire) {
			QuestionnaireTitleResponse questTitle = new QuestionnaireTitleResponse(quest.getTitle());
			questionnaireTitle.add(questTitle);
		}
		return questionnaireTitle;
	}

}
