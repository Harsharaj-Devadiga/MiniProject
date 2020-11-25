package com.MiniProject.FirstEvaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.service.EmployeeService;

@RestController
public class GetDetailsController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAdmins")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getAdmins(){
		return employeeService.findAdminDetails();
	}
	
	@GetMapping("/getSuperAdmins")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getSuperAdmins(){
		return employeeService.findSuperAdminDetails();
	}
}
