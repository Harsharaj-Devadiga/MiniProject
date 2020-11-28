package com.MiniProject.FirstEvaluation.controller;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.models.Role;
import com.MiniProject.FirstEvaluation.repository.RoleRepository;
import com.MiniProject.FirstEvaluation.service.EmployeeService;

@RestController
public class SuperAdminAddRemoveUpdateController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/addAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String addAdmin(@RequestBody Employee employee) {
		try {
			Set<Role> role = new HashSet<>();
			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(adminRole);
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(userRole);
			employee.setRoles(role);
			String password = employee.generatePassword();
			employee.setPassword(encoder.encode(password));
			employeeService.save(employee);
			return "Admin Saved Successfully With Password " + password;
		} catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}

	@PostMapping("/addSuperAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String addSuperAdmin(@RequestBody Employee employee) {
		try {
			Set<Role> role = new HashSet<>();
			Role superAdminRole = roleRepository.findByName("ROLE_SUPERADMIN")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(superAdminRole);
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(userRole);
			employee.setRoles(role);
			String password = employee.generatePassword();
			employee.setPassword(encoder.encode(password));
			employeeService.save(employee);
			return "SuperAdmin Saved Successfully With Password " + password;
		} catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}

	@DeleteMapping("/deleteAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public String deleteAdmin(@PathVariable String id) {
		try {
			employeeService.delete(id);
			return "Admin with Employee Id " + id + " Deleted!";
		} catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}

	@DeleteMapping("/deleteSuperAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public String deleteSuperAdmin(@PathVariable String id) {
		try {
			employeeService.delete(id);
			return "SuperAdmin with Employee Id " + id + " Deleted!";
		} catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}

	@PutMapping("/updateAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String updateAdmin(@RequestBody Employee employee, @PathVariable String id) {
		try {
			Set<Role> role = new HashSet<>();
			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(adminRole);
			employee.setRoles(role);
			employee.setId(id);
			String password = employee.generatePassword();
			employee.setPassword(encoder.encode(password));
			employeeService.save(employee);
			return "Admin Updated Successfully with password " + password;
		} catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}

	@PutMapping("/updateSuperAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String updateSuperAdmin(@RequestBody Employee employee, @PathVariable String id) {
		try {
			Set<Role> role = new HashSet<>();
			Role adminRole = roleRepository.findByName("ROLE_SUPERADMIN")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(adminRole);
			employee.setRoles(role);
			employee.setId(id);
			String password = employee.generatePassword();
			employee.setPassword(encoder.encode(password));
			employeeService.save(employee);
			return "SuperAdmin Updates Successfully with password " + password;
		} catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}
}
