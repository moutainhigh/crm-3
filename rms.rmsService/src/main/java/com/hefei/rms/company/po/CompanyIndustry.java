package com.hefei.rms.company.po;

import java.io.Serializable;
import java.util.Date;


/**
 */
public class CompanyIndustry implements Serializable{

	private static final long serialVersionUID = 363796312115647438L;
	
	private Long id;
	private Long companyId;//公司ID
	private Long industryId;//公司行业ID
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
	public Long getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
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
