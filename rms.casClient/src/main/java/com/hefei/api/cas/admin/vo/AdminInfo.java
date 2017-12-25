package com.hefei.api.cas.admin.vo;

import java.util.Date;

public class AdminInfo {

	public static final String DEL_FLAG_NORMAL ="1";
	public static final String DEL_FLAG_CLOSE ="2";
	public static final String DEL_FLAG_FREEZE ="3";
	
	private Long id;
	private String username;
	private String password;
	private String pwdVersion;
	private String realName;
	private String mobileNo;
	private String email;
	private Long creator;
	private Long updater;
	private String delFlag;
	private Date createTime;
	private Date updateTime;
	
	@Override
	public String toString() {
		return "AdminInfo [id=" + id + ", username=" + username + ", password="
				+ password + ", pwdVersion=" + pwdVersion + ", realName="
				+ realName + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", creator=" + creator + ", updater=" + updater
				+ ", delFlag=" + delFlag + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPwdVersion() {
		return pwdVersion;
	}
	public void setPwdVersion(String pwdVersion) {
		this.pwdVersion = pwdVersion;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
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
