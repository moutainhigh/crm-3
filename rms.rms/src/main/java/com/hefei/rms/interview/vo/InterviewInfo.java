package com.hefei.rms.interview.vo;

import java.util.Date;

public class InterviewInfo {
	private static final long serialVersionUID = -1694810036816610919L;
	
	private Long companyId;
	private Long employeeId;
	private Long positionId;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Date interviewYuyueTime;
	private Date interviewActualTime;
	private Long userId;
	private String interviewStatus;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getInterviewYuyueTime() {
		return interviewYuyueTime;
	}
	public void setInterviewYuyueTime(Date interviewYuyueTime) {
		this.interviewYuyueTime = interviewYuyueTime;
	}
	public Date getInterviewActualTime() {
		return interviewActualTime;
	}
	public void setInterviewActualTime(Date interviewActualTime) {
		this.interviewActualTime = interviewActualTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getInterviewStatus() {
		return interviewStatus;
	}
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	
}
