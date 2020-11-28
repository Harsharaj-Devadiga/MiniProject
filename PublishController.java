package com.MiniProject.FirstEvaluation.controller;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.models.Questionnaire;
import com.MiniProject.FirstEvaluation.models.Role;
import com.MiniProject.FirstEvaluation.repository.RoleRepository;
import com.MiniProject.FirstEvaluation.service.EmployeeService;
import com.MiniProject.FirstEvaluation.service.MailService;
import com.MiniProject.FirstEvaluation.service.MapService;
import com.MiniProject.FirstEvaluation.service.QuestionnaireService;

@RestController
public class PublishController {

	@Autowired
	private EmployeeService service;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private QuestionnaireService questionnaireService;

	@Autowired
	private MailService mailservice;

	@Autowired
	private MapService mapService;

	@PostMapping("/publish/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public MailException addUser(@RequestBody List<Employee> employeeList, @PathVariable int questionnaire_id)
			throws NoSuchElementException {
		try {
			Optional<Questionnaire> questionnaire = questionnaireService.findById(questionnaire_id);
			Questionnaire question = questionnaire.get();
			for (Employee employee : employeeList) {
				Set<Role> role = new HashSet<>();
				Role userRole = roleRepository.findByName("ROLE_USER")
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				role.add(userRole);
				employee.setRoles(role);

				String password = employee.generatePassword();
				employee.setPassword(encoder.encode(password));
				service.save(employee);

				mapService.setData(employee.getId(), question.getId(), 0);

				String subject = "You are added as an user";
				String text = "Hello " + employee.getUsername() + "\n " + question.getMailBody() + "\n Username : "
						+ employee.getUsername() + "\n Password : " + password + "\n Thank you ";
				mailservice.sendEmail(employee, subject, text);
			}
		} catch (MailException mailException) {
			return mailException;
		}
		return null;
	}
}
