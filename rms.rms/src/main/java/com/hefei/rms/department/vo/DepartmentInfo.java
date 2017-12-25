package com.hefei.rms.department.vo;

import java.util.Date;

public class DepartmentInfo {
	private static final long serialVersionUID = -1694810036816610919L;
	
	private Long companyId;
	private Long parentId;
	private String departmentName;
	private String departmentInfo;
	private Date createTime;
	private Date updateTime;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
