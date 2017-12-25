package com.hefei.api.rms.interview.vo;

import java.io.Serializable;
import java.util.Date;

public class InterviewInfo implements Serializable{
	private static final long serialVersionUID = -1694810036816610919L;
	
	private Long id;
	private Long companyId;
	private Long employeeId;
	private Long positionId;//面试岗位
	private String name;//姓名
	private String phone;//面试人手机
	private String email;//面试人邮箱
	private String address;//面试人地址
	private Date interviewYuyueTime;//预约面试时间
	private Date interviewActualTime;//实际面试时间
	private Long userId;//面试官Id
	private String interviewStatus;//面试结果
	private String delFlag;//删除标识
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
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
