package com.hefei.api.rms.salary.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 工资发放流水
 *
 */
public class SalaryTransactionDTO implements Serializable{

	private static final long serialVersionUID = -7579982509991198316L;

	/**
	 * 1：未发 2：已发
	 */
	public static final String STATUS_TO_PAY = "1";
	public static final String STATUS_PAYED = "2";
	
	/** 工资编号 */
	private Long id;
	private Long companyId;
	/** 员工编号 */
	private Long employeeId;
	private String employeeName;
	/** 月基本工资 */
	private Double monthlyBaseSalary;
	/** 月奖金 */
	private Double monthlyBonus;
	/** 季度奖金 */
	private Double quarterlyBonus;
	/** 年奖金 */
	private Double yearlyBonus;
	/** 当月惩罚 */
	private Double deductAmount;
	/** 社保编号 */
	private Long ssId;
	/** 社保编号 */
	private Double ssAmount;
	/** 社保编号 */
	private Double taxRate;
	/** 个人所得税 */
	private Double taxAmount;
	/** 实发工资*/
	private Double payedAmount;
	/**
	 * 实发时间
	 */
	private Date payedTime;
	/** 发放标志 */
	private String status;
	
	private String delFlag;
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
	public Double getMonthlyBaseSalary() {
		return monthlyBaseSalary;
	}
	public void setMonthlyBaseSalary(Double monthlyBaseSalary) {
		this.monthlyBaseSalary = monthlyBaseSalary;
	}
	public Double getMonthlyBonus() {
		return monthlyBonus;
	}
	public void setMonthlyBonus(Double monthlyBonus) {
		this.monthlyBonus = monthlyBonus;
	}
	public Double getQuarterlyBonus() {
		return quarterlyBonus;
	}
	public void setQuarterlyBonus(Double quarterlyBonus) {
		this.quarterlyBonus = quarterlyBonus;
	}
	public Double getYearlyBonus() {
		return yearlyBonus;
	}
	public void setYearlyBonus(Double yearlyBonus) {
		this.yearlyBonus = yearlyBonus;
	}
	public Double getDeductAmount() {
		return deductAmount;
	}
	public void setDeductAmount(Double deductAmount) {
		this.deductAmount = deductAmount;
	}
	public Long getSsId() {
		return ssId;
	}
	public void setSsId(Long ssId) {
		this.ssId = ssId;
	}
	public Double getSsAmount() {
		return ssAmount;
	}
	public void setSsAmount(Double ssAmount) {
		this.ssAmount = ssAmount;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getPayedAmount() {
		return payedAmount;
	}
	public void setPayedAmount(Double payedAmount) {
		this.payedAmount = payedAmount;
	}
	public Date getPayedTime() {
		return payedTime;
	}
	public void setPayedTime(Date payedTime) {
		this.payedTime = payedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
