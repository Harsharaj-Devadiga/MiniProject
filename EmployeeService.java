package com.MiniProject.FirstEvaluation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiniProject.FirstEvaluation.models.Employee;
import com.MiniProject.FirstEvaluation.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	private UserRepository repo;

	public Employee save(Employee employee) {
		return repo.save(employee);
	}

	public void delete(String id) {
		repo.deleteById(id);
	}

	public Optional<Employee> findById(String id) {
		return repo.findById(id);
	}

	public Optional<Employee> findByEmail(String mail) {
		return repo.findByEmail(mail);
	}

	public List<String> findAdminDetails() {
		return repo.findAdminDetails();
	}

	public List<String> findSuperAdminDetails() {
		return repo.findSuperAdminDetails();
	}
}
