package com.hefei.api.rms.attendance.vo;

import java.io.Serializable;
import java.util.Date;

public class AttendanceInfo implements Serializable{

	private static final long serialVersionUID = 8659692053342756861L;

	private Long id;
	private Long companyId;
	private Long employeeId;//员工id
	private Date goworkTime;//上班打卡记录
	private Date offworkTime;//下班打卡记录
	private String note;//备注
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
	public Date getGoworkTime() {
		return goworkTime;
	}
	public void setGoworkTime(Date goworkTime) {
		this.goworkTime = goworkTime;
	}
	public Date getOffworkTime() {
		return offworkTime;
	}
	public void setOffworkTime(Date offworkTime) {
		this.offworkTime = offworkTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
