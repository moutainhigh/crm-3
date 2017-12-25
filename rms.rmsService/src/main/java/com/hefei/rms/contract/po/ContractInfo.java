package com.hefei.rms.contract.po;

import java.io.Serializable;
import java.util.Date;

public class ContractInfo implements Serializable{

	private static final long serialVersionUID = -8329051387058135005L;
	
	private Long id;
	private Long companyId;
	private Long employeeId;//员工id
	private Date contractStartTime;//合同起始时间
	private Date contractEndTime;//合同结束时间
	private String contractType;//合同类型
	private String contractPics;//合同照片
	private Long auditUserId;//审核人id
	private String auditStatus;//审核状态
	private String delFlag;//删除标志
	private Date createTime;//创建时间
	private Date updateTime;//更改时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Date getContractStartTime() {
		return contractStartTime;
	}
	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}
	public Date getContractEndTime() {
		return contractEndTime;
	}
	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractPics() {
		return contractPics;
	}
	public void setContractPics(String contractPics) {
		this.contractPics = contractPics;
	}
	public Long getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
