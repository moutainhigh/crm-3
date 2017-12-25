package com.hefei.agg.location.po;

import java.util.Date;

public class Location implements java.io.Serializable{
	
	private static final long serialVersionUID = 2027775270232679636L;
	
	private String code;
	private String name;
	private String enName;
	private Long rank;
	private String levelCode;
	private String parentCode;
	private Long status;
	private String postCode;
	private String dialingCode;
	private String remark;
	private Date createTime;
	private Date updateTime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getDialingCode() {
		return dialingCode;
	}
	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		return "Location [ code=" + code + ", name=" + name
				+ ", enName=" + enName + ", rank=" + rank + ", levelCode="
				+ levelCode + ", parentCode=" + parentCode + ", status="
				+ status + ", postCode=" + postCode + ", dialingCode="
				+ dialingCode + ", remark=" + remark + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}	
}
