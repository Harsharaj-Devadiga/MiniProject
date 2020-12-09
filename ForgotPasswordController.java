package com.MiniProject.FirstEvaluation.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.response.MessageResponse;
import com.MiniProject.FirstEvaluation.service.EmployeeService;
import com.MiniProject.FirstEvaluation.service.MailService;

@RestController
public class ForgotPasswordController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private MailService mailservice;

	@PutMapping("/forgotPassword/{email}")
	public MessageResponse changePassword(@PathVariable String email) {
		Optional<Employee> emp = employeeService.findByEmail(email);
		Employee employee = emp.get();
		String password = employee.generatePassword();
		String subject = "The Password has been Reset";
		String text = "The Reset password is: " + password;
		String message = "Password has been sent to the email";
		employee.setPassword(encoder.encode(password));
		employeeService.save(employee);
		try {
			mailservice.sendEmail(employee, subject, text);
			return new MessageResponse(message);
		} catch (NoSuchElementException exception) {
			return new MessageResponse("NOT_FOUND");
		}
	}
}
