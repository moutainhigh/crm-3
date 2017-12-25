package com.hefei.frontsample.sample.client;

import java.util.Date;


public class SampleVO {

	public static final String DELFLAG_NO = "1";
	public static final String DELFLAG_YES = "0";
	
	private Long id;
	private String type;
	private Date createTime;
	private Date updateTime;
	private String delFlag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
