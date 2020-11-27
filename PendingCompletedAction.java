package com.MiniProject.FirstEvaluation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;
import com.MiniProject.FirstEvaluation.service.MapService;
import com.MiniProject.FirstEvaluation.service.QuestionnaireService;

@RestController
public class PendingCompletedAction {

	@Autowired
	private MapService mapService;

	@Autowired
	private QuestionnaireService questionnaireService;

	@GetMapping("/completedQuestionnaire/{emp_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')or hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Object> completedQuestionnaire(@PathVariable String emp_id) {
		List<QuestionnaireMapping> questionMap = mapService.getStatus(emp_id, 1);
		List<Object> completedList = new ArrayList<Object>();
		for (QuestionnaireMapping map : questionMap) {
			int qnsId = map.getQnsId();
			Optional<Questionnaire> questionnaire = questionnaireService.findById(qnsId);
			Questionnaire qns = questionnaire.get();
			String title = qns.getTitle();
			completedList.add("Questionnaire Id :" + qnsId);
			completedList.add("Title : " + title);
		}
		return completedList;
	}

	@GetMapping("/pendingQuestionnaire/{emp_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')or hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Object> pendingQuestionnaire(@PathVariable String emp_id) {
		List<QuestionnaireMapping> questionMap = mapService.getStatus(emp_id, 0);
		List<Object> completedList = new ArrayList<Object>();
		for (QuestionnaireMapping map : questionMap) {
			int qnsId = map.getQnsId();
			Optional<Questionnaire> questionnaire = questionnaireService.findById(qnsId);
			Questionnaire qns = questionnaire.get();
			String title = qns.getTitle();
			completedList.add("Questionnaire Id :" + qnsId);
			completedList.add("Title : " + title);
		}
		return completedList;
	}

}
