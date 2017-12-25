package com.hefei.api.rms.employee.vo;

import java.util.Date;

public class EmployeeDepartPositionDTO {

	public static final String DEPARTMENT_DEL_FLAG_NO="1";//未删除
	public static final String DEPARTMENT_DEL_FLAG_YES="0";//已经删除
	
	public static final String POSITION_DEL_FLAG_NO="1";//未删除
	public static final String POSITION_DEL_FLAG_YES="0";//已经删除
	
	private Long companyId;//公司id
	private Long employeeId;
	private Long departmentId;
	private Long parentDepartmentId;//上级部门id
	private String departmentName;//部门名称
	private String departmentInfo;//部门简介
	private Long positionId;//岗位id
	private String positionName;//公司名称
	private String departmentdDelFlag;
	private String positionDelFlag;
	private Date createTime;
	private Date updateTime;
	
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
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}
	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentInfo() {
		return departmentInfo;
	}
	public void setDepartmentInfo(String departmentInfo) {
		this.departmentInfo = departmentInfo;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDepartmentdDelFlag() {
		return departmentdDelFlag;
	}
	public void setDepartmentdDelFlag(String departmentdDelFlag) {
		this.departmentdDelFlag = departmentdDelFlag;
	}
	public String getPositionDelFlag() {
		return positionDelFlag;
	}
	public void setPositionDelFlag(String positionDelFlag) {
		this.positionDelFlag = positionDelFlag;
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
