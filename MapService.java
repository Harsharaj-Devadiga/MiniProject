package com.MiniProject.FirstEvaluation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiniProject.FirstEvaluation.models.QuestionnaireMapping;
import com.MiniProject.FirstEvaluation.repository.MapRepository;

@Service
public class MapService {

	@Autowired
	private MapRepository mapRepository;

	public void setData(String empId, int qnsId, int status) {
		QuestionnaireMapping map = new QuestionnaireMapping(empId, qnsId, status);
		mapRepository.save(map);
	}

	public void save(QuestionnaireMapping questionnaireMapping) {
		mapRepository.save(questionnaireMapping);
	}

	public Optional<QuestionnaireMapping> findByTwoValues(String emp_id, int qns_id) {
		return mapRepository.findByTwoValues(emp_id, qns_id);
	}

	public List<QuestionnaireMapping> findByQuestionId(int qns_id) {
		return mapRepository.findByQuestionId(qns_id);
	}

	public List<QuestionnaireMapping> getStatus(String emp_id, int status) {
		return mapRepository.getStatus(emp_id, status);
	}
}
