package com.MiniProject.FirstEvaluation.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;
import com.MiniProject.FirstEvaluation.service.EmployeeService;
import com.MiniProject.FirstEvaluation.service.MailService;
import com.MiniProject.FirstEvaluation.service.MapService;

@RestController
public class RemindController {

	@Autowired
	private MapService mapService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MailService mailservice;

	@Autowired
	private EmployeeService service;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/remind/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public void remindQuestionnaire(@PathVariable int questionnaire_id) throws NoSuchElementException{
		List<QuestionnaireMapping> questionnaireMap = mapService.findByQuestionId(questionnaire_id);
		for (QuestionnaireMapping quest : questionnaireMap) {
			if ((quest.getStatus()) == 0) {
				String empId = quest.getEmpId();
				Optional<Employee> employee = employeeService.findById(empId);
				Employee emp = employee.get();

				String password = emp.generatePassword();
				emp.setPassword(encoder.encode(password));
				service.save(emp);

				String subject = "Reminder for policy agreement";
				String text = "Hello "+emp.getUsername()+"\n Reminder For Questionnaire " + questionnaire_id + "\n Please click the link given below \n Credentials : \n username :"+emp.getUsername()+"\n Password :"+password;
				mailservice.sendEmail(emp, subject, text);

			}
		}
	}
}
