package com.hefei.rms.behavior.po;

import java.io.Serializable;
import java.util.Date;

public class BehaviorInfo implements Serializable{

	private static final long serialVersionUID = 6264610932189169044L;
	public static final String STATUS_TRUE = "1";
	public static final String STATUS_FALSE = "0";
	
	/** 奖惩编号 */
	private Long id;
	/** 公司编号 */
	private Long companyId;
	/** 员工编号 */
	private Long employeeId;
	/** 奖惩月份 */
	private String month;
	/** 奖惩类型 */
	private String type;
	/** 金额标志 0 金额为正 1 金额为负 */
	private String sign;
	/** 奖惩金额 */
	private Double amount;
	/** 状态 0 撤销 1 正常 */
	private String status;
	/** 奖惩说明 */
	private String remark;
	/** 操作人 */
	private Long creator;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "BehaviorInfo [id=" + id + ", companyId=" + companyId
				+ ", employeeId=" + employeeId + ", type=" + type + ", sign="
				+ sign + ", amount=" + amount + ", status=" + status
				+ ", remark=" + remark + ", creator=" + creator
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	
	
	
}
