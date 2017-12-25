package com.hefei.rms.salary.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 工资调整历史表
 * @author yinzg
 *
 */
public class SalaryHistory implements Serializable{

	private static final long serialVersionUID = 5059561155360052506L;

	/** 工资编号 */
	private Long id;
	/** 公司编号 */
	private Long companyId;
	/** 员工编号 */
	private Long employeeId;
	private String employeeName;
	
	/** 月基本工资 */
	private Double monthlyBaseSalaryBefore;
	/** 月奖金 */
	private Double monthlyBonusBefore;
	/** 季度奖金 */
	private Double quarterlyBonusBefore;
	/** 年奖金 */
	private Double yearlyBonusBefore;
	/** 月基本工资 */
	private Double monthlyBaseSalaryAfter;
	/** 月奖金 */
	private Double monthlyBonusAfter;
	/** 季度奖金 */
	private Double quarterlyBonusAfter;
	/** 年奖金 */
	private Double yearlyBonusAfter;
	/** 备注 */
	private String remark;
	/** 操作人 */
	private Long creator;
	private String delFlag;
	/** 生效时间：年月 */
	private Date effectTime;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Double getMonthlyBaseSalaryBefore() {
		return monthlyBaseSalaryBefore;
	}
	public void setMonthlyBaseSalaryBefore(Double monthlyBaseSalaryBefore) {
		this.monthlyBaseSalaryBefore = monthlyBaseSalaryBefore;
	}
	public Double getMonthlyBonusBefore() {
		return monthlyBonusBefore;
	}
	public void setMonthlyBonusBefore(Double monthlyBonusBefore) {
		this.monthlyBonusBefore = monthlyBonusBefore;
	}
	public Double getQuarterlyBonusBefore() {
		return quarterlyBonusBefore;
	}
	public void setQuarterlyBonusBefore(Double quarterlyBonusBefore) {
		this.quarterlyBonusBefore = quarterlyBonusBefore;
	}
	public Double getYearlyBonusBefore() {
		return yearlyBonusBefore;
	}
	public void setYearlyBonusBefore(Double yearlyBonusBefore) {
		this.yearlyBonusBefore = yearlyBonusBefore;
	}
	public Double getMonthlyBaseSalaryAfter() {
		return monthlyBaseSalaryAfter;
	}
	public void setMonthlyBaseSalaryAfter(Double monthlyBaseSalaryAfter) {
		this.monthlyBaseSalaryAfter = monthlyBaseSalaryAfter;
	}
	public Double getMonthlyBonusAfter() {
		return monthlyBonusAfter;
	}
	public void setMonthlyBonusAfter(Double monthlyBonusAfter) {
		this.monthlyBonusAfter = monthlyBonusAfter;
	}
	public Double getQuarterlyBonusAfter() {
		return quarterlyBonusAfter;
	}
	public void setQuarterlyBonusAfter(Double quarterlyBonusAfter) {
		this.quarterlyBonusAfter = quarterlyBonusAfter;
	}
	public Double getYearlyBonusAfter() {
		return yearlyBonusAfter;
	}
	public void setYearlyBonusAfter(Double yearlyBonusAfter) {
		this.yearlyBonusAfter = yearlyBonusAfter;
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
	public Date getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}
