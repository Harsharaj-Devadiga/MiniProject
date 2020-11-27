package com.MiniProject.FirstEvaluation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {
	@Id
	@Column(name = "questionnaire_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "button_text")
	private String buttonText;

	@Column(name = "button_title")
	private String buttonTitle;

	@Column(name = "checkbox_text")
	private String checkboxText;

	@Column(name = "ppt_url")
	private String pptUrl;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "mail_body")
	private String mailBody;

	public Questionnaire(String title, String description, String buttonText, String buttonTitle, String checkboxText,
			String startDate, String endDate, String mailBody) {
		this.title = title;
		this.description = description;
		this.buttonText = buttonText;
		this.buttonTitle = buttonTitle;
		this.checkboxText = checkboxText;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mailBody = mailBody;
	}

	public Questionnaire() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getButtonTitle() {
		return buttonTitle;
	}

	public void setButtonTitle(String buttonTitle) {
		this.buttonTitle = buttonTitle;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getCheckboxText() {
		return checkboxText;
	}

	public void setCheckboxText(String checkboxText) {
		this.checkboxText = checkboxText;
	}

	public String getPptUrl() {
		return pptUrl;
	}

	public void setPptUrl(String pptUrl) {
		this.pptUrl = pptUrl;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
