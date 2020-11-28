package com.MiniProject.FirstEvaluation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.service.EmployeeService;

@RestController
public class ChangePasswordController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	PasswordEncoder encoder;

	@PutMapping("/changePassword/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String changePassword(@RequestParam String password, @PathVariable String id) {
		try {
			Optional<Employee> emp = employeeService.findById(id);
			Employee employee = emp.get();
			employee.setPassword(encoder.encode(password));
			employeeService.save(employee);
			return "Password Updated";
		} catch (Exception e) {
			return "Bad Request";
		}
	}
}
