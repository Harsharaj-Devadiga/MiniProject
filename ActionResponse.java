package com.MiniProject.FirstEvaluation.response;

public class ActionResponse {

	private int QuestinnaireId;
	private String title;

	public ActionResponse(int questinnaireId, String title) {
		super();
		QuestinnaireId = questinnaireId;
		this.title = title;
	}

	public int getQuestinnaireId() {
		return QuestinnaireId;
	}

	public void setQuestinnaireId(int questinnaireId) {
		QuestinnaireId = questinnaireId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
