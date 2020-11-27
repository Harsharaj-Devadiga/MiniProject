package com.MiniProject.FirstEvaluation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;

@Repository
public interface MapRepository extends JpaRepository<QuestionnaireMapping, Integer> {
	@Query(value = "SELECT * FROM questionnaire_mapping WHERE questionnaire_mapping.emp_id=?1 and questionnaire_mapping.qns_id=?2", nativeQuery = true)
	Optional<QuestionnaireMapping> findByTwoValues(String emp_id, int qns_id);

	@Query(value = "SELECT * FROM questionnaire_mapping map WHERE map.qns_id =?", nativeQuery = true)
	List<QuestionnaireMapping> findByQuestionId(int qns_id);

	@Query(value = "SELECT * FROM questionnaire_mapping map WHERE map.emp_id=?1 and map.status=?2", nativeQuery = true)
	List<QuestionnaireMapping> getStatus(String emp_id, int status);
}
