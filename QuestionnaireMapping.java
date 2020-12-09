package com.MiniProject.FirstEvaluation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire_mapping")
public class QuestionnaireMapping {
	@Id
	@Column(name = "map_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mapId;

	@Column(name = "emp_id")
	private String empId;

	@Column(name = "qns_id")
	private int qnsId;

	@Column(name = "status")
	private int status;

	public QuestionnaireMapping() {

	}

	public QuestionnaireMapping(String empId, int qnsId, int status) {
		super();
		this.empId = empId;
		this.qnsId = qnsId;
		this.status = status;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getQnsId() {
		return qnsId;
	}

	public void setQnsId(int qnsId) {
		this.qnsId = qnsId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
