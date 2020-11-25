package com.MiniProject.FirstEvaluation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MiniProject.FirstEvaluation.models.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, String> {
	@Query(value="SELECT id, name, email FROM project.employee where id in"
			+"(select employee_id from project.employee_role where role_id =2)",nativeQuery = true)
	List<String> findAdminDetails();
	
	@Query(value="SELECT id, name, email FROM project.employee where id in"
			+"(select employee_id from project.employee_role where role_id =3)",nativeQuery = true)
	List<String> findSuperAdminDetails();
	
	Optional<Employee> findByName(String name);
	
	Optional<Employee> findById(String Id);
	
	void deleteById(String id);

	Boolean existsByName(String name);

	Boolean existsByEmail(String email);

	Optional<Employee> findByEmail(String mail);
}

