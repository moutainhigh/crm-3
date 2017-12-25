package com.hefei.api.rms.position.vo;

import java.io.Serializable;
import java.util.Date;

public class PositionInfo implements Serializable {
	private static final long serialVersionUID = -1694810036816610919L;

	public static final String DELFLAG_NO = "1";
	public static final String DELFLAG_YES = "0";
	
	private Long id;//岗位id
	private Long departmentId;//部门id
	private String positionName;//公司名称
	private String delFlag;//删除标识
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
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
