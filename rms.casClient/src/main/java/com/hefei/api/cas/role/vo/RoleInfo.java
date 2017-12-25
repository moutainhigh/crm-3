package com.hefei.api.cas.role.vo;

import java.util.Date;


/**
 * 慈善外部商户信息
 */
public class RoleInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 2027775270232679636L;

	public static final String DEL_FLAG_NO = "1";//有效
	public static final String DEL_FLAG_YES = "0";//无效
	
	public static final String RMS_SUPER_MANAGER = "RMS-SUPER-MANAGER";//RMS系统默认超级管理员
	
	private Long id;
	private Long systemId;
	//
	private String roleName;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private String delFlag;
	
	private String remark;
	
	private Long companyId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
