package com.MiniProject.FirstEvaluation.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;
import com.MiniProject.FirstEvaluation.response.MessageResponse;
import com.MiniProject.FirstEvaluation.service.MapService;

@RestController
public class StatusController {

	@Autowired
	private MapService mapService;

	@PutMapping("/agree/{employee_id}/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')or hasRole('USER')")
	public MessageResponse agreeCommand(@PathVariable String employee_id, @PathVariable int questionnaire_id)
			throws NoSuchElementException {
		Optional<QuestionnaireMapping> statusMapping = mapService.findByTwoValues(employee_id, questionnaire_id);
		QuestionnaireMapping statusMap = statusMapping.get();
		statusMap.setStatus(1);
		mapService.save(statusMap);
		String statusResponse = "Policy agreed by the user";
		return new MessageResponse(statusResponse);
	}
}
