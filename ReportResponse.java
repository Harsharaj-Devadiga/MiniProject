package com.MiniProject.FirstEvaluation.response;

public class ReportResponse {

	private String EmployeeId;
	private String username;
	private int status;

	public ReportResponse(String employeeId, String username, int status) {
		super();
		EmployeeId = employeeId;
		this.username = username;
		this.status = status;
	}

	public String getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
