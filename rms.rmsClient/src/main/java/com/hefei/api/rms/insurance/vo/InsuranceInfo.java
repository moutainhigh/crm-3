package com.hefei.api.rms.insurance.vo;

import java.io.Serializable;
import java.util.Date;

public class InsuranceInfo implements Serializable{

	private static final long serialVersionUID = 212741068441406366L;

	/** 奖惩编号 */
	private Long id;
	/** 公司编号 */
	private Long companyId;
	/** 员工编号 */
	private Long employeeId;
	/** 保单编号 */
	private Long insuranceId;
	/** 保险类型 */
	private String insuranceType;
	/** 保额 */
	private Double insuranceAmt;
	/** 保险期限 */
	private String insuranceDate;
	/** 缴费年限 */
	private Integer payYear;
	/** 受益人标志  0 本人 1 非本人 */
	private String incomeSign;
	/** 受益人姓名 */
	private String incomeName;
	/** 受益人性别 */
	private String incomeSex;
	/** 受益人证件类型 */
	private String incomeDcmentsType;
	/** 受益人证件号码 */
	private String incomeDocuments;
	/** 备注 */
	private String remark;
	/** 操作人 */
	private Long creator;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
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
	public Long getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Long insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public Double getInsuranceAmt() {
		return insuranceAmt;
	}
	public void setInsuranceAmt(Double insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}
	public String getInsuranceDate() {
		return insuranceDate;
	}
	public void setInsuranceDate(String insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	public Integer getPayYear() {
		return payYear;
	}
	public void setPayYear(Integer payYear) {
		this.payYear = payYear;
	}
	public String getIncomeSign() {
		return incomeSign;
	}
	public void setIncomeSign(String incomeSign) {
		this.incomeSign = incomeSign;
	}
	public String getIncomeName() {
		return incomeName;
	}
	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}
	public String getIncomeSex() {
		return incomeSex;
	}
	public void setIncomeSex(String incomeSex) {
		this.incomeSex = incomeSex;
	}
	public String getIncomeDcmentsType() {
		return incomeDcmentsType;
	}
	public void setIncomeDcmentsType(String incomeDcmentsType) {
		this.incomeDcmentsType = incomeDcmentsType;
	}
	public String getIncomeDocuments() {
		return incomeDocuments;
	}
	public void setIncomeDocuments(String incomeDocuments) {
		this.incomeDocuments = incomeDocuments;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "InsuranceInfo [id=" + id + ", companyId=" + companyId
				+ ", employeeId=" + employeeId + ", insuranceId=" + insuranceId
				+ ", insuranceType=" + insuranceType + ", insuranceAmt="
				+ insuranceAmt + ", insuranceDate=" + insuranceDate
				+ ", payYear=" + payYear + ", incomeSign=" + incomeSign
				+ ", incomeName=" + incomeName + ", incomeSex=" + incomeSex
				+ ", incomeDcmentsType=" + incomeDcmentsType
				+ ", incomeDocuments=" + incomeDocuments + ", remark=" + remark
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}
