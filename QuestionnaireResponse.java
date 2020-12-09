package com.MiniProject.FirstEvaluation.response;

public class QuestionnaireResponse {
	private int questionnaireId;

	public QuestionnaireResponse(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public int getId() {
		return questionnaireId;
	}

	public void setId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
}
