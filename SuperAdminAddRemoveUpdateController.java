package com.MiniProject.FirstEvaluation.controller;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.models.Role;
import com.MiniProject.FirstEvaluation.repository.RoleRepository;
import com.MiniProject.FirstEvaluation.response.MessageResponse;
import com.MiniProject.FirstEvaluation.service.EmployeeService;
import com.MiniProject.FirstEvaluation.service.MailService;

@RestController
public class SuperAdminAddRemoveUpdateController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private MailService mailservice;

	@PostMapping("/addAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public MessageResponse addAdmin(@RequestBody Employee employee) {
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
			String subject = "You are added as an admin";
			String text = "Hello " + employee.getUsername() + "\n "
					+ "You are added as an admin and given below are the login credentials " + "\n Username : "
					+ employee.getUsername() + "\n Password : " + password + "\n Thank you ";
			mailservice.sendEmail(employee, subject, text);
			return new MessageResponse("Admin added successfully");
		} catch (NoSuchElementException e) {
			return new MessageResponse("NOT_FOUND");
		}
	}

	@PostMapping("/addSuperAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public MessageResponse addSuperAdmin(@RequestBody Employee employee) {
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
			String subject = "You are added as an Super Admin";
			String text = "Hello " + employee.getUsername() + "\n "
					+ "You are added as an Super Admin and given below are the login credentials " + "\n Username : "
					+ employee.getUsername() + "\n Password : " + password + "\n Thank you ";
			mailservice.sendEmail(employee, subject, text);
			return new MessageResponse("SuperAdmin added successfully");
		} catch (NoSuchElementException e) {
			return new MessageResponse("NOT_FOUND");
		}
	}

	@DeleteMapping("/deleteAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@Transactional
	public MessageResponse deleteAdmin(@PathVariable String id) {
		try {
			employeeService.delete(id);
			return new MessageResponse("Admin with Id " + id + " Deleted");

		} catch (NoSuchElementException e) {
			return new MessageResponse("NOT_FOUND");
		}
	}

	@DeleteMapping("/deleteSuperAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@Transactional
	public MessageResponse deleteSuperAdmin(@PathVariable String id) {
		try {
			employeeService.delete(id);
			return new MessageResponse("SuperAdmin with Id " + id + " Deleted");
		} catch (NoSuchElementException e) {
			return new MessageResponse("NOT_FOUND");
		}
	}

	@PutMapping("/updateAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public MessageResponse updateAdmin(@RequestBody Employee employee, @PathVariable String id) {
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
			String subject = "Your admin details are updated";
			String text = "Hello " + employee.getUsername() + "\n "
					+ "Your an admin details are updated and given below are the login credentials " + "\n Username : "
					+ employee.getUsername() + "\n Password : " + password + "\n Thank you ";
			mailservice.sendEmail(employee, subject, text);
			return new MessageResponse("Admin updated successfully");
		} catch (NoSuchElementException e) {
			return new MessageResponse("NOT_FOUND");
		}
	}

	@PutMapping("/updateSuperAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public MessageResponse updateSuperAdmin(@RequestBody Employee employee, @PathVariable String id) {
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
			String subject = "Your Super admin details are updated";
			String text = "Hello " + employee.getUsername() + "\n "
					+ "Your an Super admin details are updated and given below are the login credentials "
					+ "\n Username : " + employee.getUsername() + "\n Password : " + password + "\n Thank you ";
			mailservice.sendEmail(employee, subject, text);
			return new MessageResponse("SuperAdmin update successfully");
		} catch (NoSuchElementException e) {
			return new MessageResponse("NOT_FOUND");
		}
	}
}
