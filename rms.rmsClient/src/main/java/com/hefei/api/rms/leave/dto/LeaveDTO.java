package com.hefei.api.rms.leave.dto;

import java.io.Serializable;
import java.util.Date;

public class LeaveDTO implements Serializable{

	private static final long serialVersionUID = 1385643703118739069L;

	//1：事假 	2：病假	3：年假	4：调休 5：丧假 6：产假 7：婚假 8:陪产假 9：其他
	public static final String TYPE_PRIVATE = "1";
	public static final String TYPE_SICK = "2"; 
	public static final String TYPE_ANNUAL = "3";
	public static final String TYPE_OFF = "4";
	public static final String TYPE_FUNERAL = "5";
	public static final String TYPE_MATERNITY = "6";
	public static final String TYPE_MARRIAGE = "7";
	public static final String TYPE_PATERNITY = "8";
	public static final String TYPE_OTHER = "9";
	
	//0.未审核;1.审核通过;2.未批准
	public static final String AUDIT_STATUS_TODO = "0";
	public static final String AUDIT_STATUS_PASS = "1";
	public static final String AUDIT_STATUS_REJECT = "2";
	
	//1：未删除；0：删除
	public static final String DEL_FLAG_NO = "1";
	public static final String DEL_FLAG_YES = "0";
	
	
	private Long id;
	private Long companyId;
	private Long employeeId;
	private String employeeName;
	private String type;
	private String content;
	private Date startTime;
	private Date endTime;
	private Integer totalTimeDay;
	private Integer totalTimeHour;
	private Long auditEmployeeId;
	private String auditEmployeeName;
	private String auditStatus;
	private Date auditTime;
	private String delFlag;
	private Date createTime;
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getTotalTimeDay() {
		return totalTimeDay;
	}
	public void setTotalTimeDay(Integer totalTimeDay) {
		this.totalTimeDay = totalTimeDay;
	}
	public Integer getTotalTimeHour() {
		return totalTimeHour;
	}
	public void setTotalTimeHour(Integer totalTimeHour) {
		this.totalTimeHour = totalTimeHour;
	}
	public Long getAuditEmployeeId() {
		return auditEmployeeId;
	}
	public void setAuditEmployeeId(Long auditEmployeeId) {
		this.auditEmployeeId = auditEmployeeId;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditEmployeeName() {
		return auditEmployeeName;
	}
	public void setAuditEmployeeName(String auditEmployeeName) {
		this.auditEmployeeName = auditEmployeeName;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
