package com.MiniProject.FirstEvaluation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;
import com.MiniProject.FirstEvaluation.response.ActionResponse;
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
	public List<ActionResponse> completedQuestionnaire(@PathVariable String emp_id) throws NoSuchElementException {
		List<QuestionnaireMapping> questionMap = mapService.getStatus(emp_id, 1);
		List<ActionResponse> completedList = new ArrayList<ActionResponse>();
		for (QuestionnaireMapping map : questionMap) {
			int qnsId = map.getQnsId();
			Optional<Questionnaire> questionnaire = questionnaireService.findById(qnsId);
			Questionnaire qns = questionnaire.get();
			String title = qns.getTitle();
			ActionResponse completed = new ActionResponse(qnsId, title);
			completedList.add(completed);
		}
		return completedList;
	}

	@GetMapping("/pendingQuestionnaire/{emp_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')or hasRole('USER')")
	public List<ActionResponse> pendingQuestionnaire(@PathVariable String emp_id) throws NoSuchElementException {
		List<QuestionnaireMapping> questionMap = mapService.getStatus(emp_id, 0);
		List<ActionResponse> completedList = new ArrayList<ActionResponse>();
		for (QuestionnaireMapping map : questionMap) {
			int qnsId = map.getQnsId();
			Optional<Questionnaire> questionnaire = questionnaireService.findById(qnsId);
			Questionnaire qns = questionnaire.get();
			String title = qns.getTitle();
			ActionResponse completed = new ActionResponse(qnsId, title);
			completedList.add(completed);
		}
		return completedList;
	}

}
