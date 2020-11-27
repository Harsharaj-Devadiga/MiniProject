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

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;
import com.MiniProject.FirstEvaluation.service.EmployeeService;
import com.MiniProject.FirstEvaluation.service.MapService;

@RestController
public class GenerateReport {

	@Autowired
	private MapService mapService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/generateReport/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Object> generateReport(@PathVariable int questionnaire_id) {
		List<QuestionnaireMapping> questionMap = mapService.findByQuestionId(questionnaire_id);
		List<Object> reportList = new ArrayList<Object>();
		for (QuestionnaireMapping map : questionMap) {
			String empId = map.getEmpId();
			Optional<Employee> employee = employeeService.findById(empId);
			Employee emp = employee.get();
			String username = emp.getUsername();
			int status = map.getStatus();
			reportList.add("Employee Id: " + empId);
			reportList.add("Username: " + username);
			reportList.add("Status: " + status);
		}
		return reportList;
	}
}
