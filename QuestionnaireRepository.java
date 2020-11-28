package com.MiniProject.FirstEvaluation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MiniProject.FirstEvaluation.models.Questionnaire;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {

	Optional<Questionnaire> findById(int Id);
	
	@Query(value="SELECT title FROM questionnaire", nativeQuery = true)
	List<String> getAllTitles();
}
