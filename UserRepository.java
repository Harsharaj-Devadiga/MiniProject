package com.MiniProject.FirstEvaluation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MiniProject.FirstEvaluation.models.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, String> {
	@Query(value = "SELECT * FROM project.employee where id in"
			+ "(select employee_id from project.employee_role where role_id =2)", nativeQuery = true)
	List<Employee> findAdminDetails();

	@Query(value = "SELECT * FROM project.employee where id in"
			+ "(select employee_id from project.employee_role where role_id =3)", nativeQuery = true)
	List<Employee> findSuperAdminDetails();

	Optional<Employee> findByName(String name);

	Optional<Employee> findById(String Id);

	void deleteById(String id);

	Boolean existsByName(String name);

	Boolean existsByEmail(String email);

	Optional<Employee> findByEmail(String mail);
	
//	@Modifying
//	@Query(value="DELETE FROM project.employee_role where employee_id=?1 and role_id=2", nativeQuery = true)
//	void deleteAdmins(String emp_id);
}
