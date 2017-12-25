package com.hefei.rms.employee.po;

import java.util.Date;

/**
 * 雇佣关系
 * @author Administrator
 *
 */
public class EmployeeCompany {
	
	private Long id;//公司id
	private Long companyId;//公司所属行业
	private Long employeeId;//公司名称
	private String cardNo;//公司地址
	private String email;//公司电话
	private String mobile;//公司简介
	private Long superior;//上级 T_EMPLOYEE.ID
	private String superiorEmployeeName;
	private String status;//公司法人
	private Date onBoardDate;//入职时间
	private Date leaveDate;//离职时间
	private Long creator;
	private Long updater;
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
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getSuperior() {
		return superior;
	}
	public void setSuperior(Long superior) {
		this.superior = superior;
	}
	public String getSuperiorEmployeeName() {
		return superiorEmployeeName;
	}
	public void setSuperiorEmployeeName(String superiorEmployeeName) {
		this.superiorEmployeeName = superiorEmployeeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOnBoardDate() {
		return onBoardDate;
	}
	public void setOnBoardDate(Date onBoardDate) {
		this.onBoardDate = onBoardDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
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
