package com.hefei.user.api.email.vo;

import java.util.Date;

public class EmailInfo {
	public static final String TYPE_LOGIN="1";//登录邮箱
	public static final String IS_CHECK_DONE="1";//邮箱已经验证
	public static final String IS_CHECK_TODO="0";//邮箱未验证
	
	public static final String DEL_FLAG_YES="0";//无效
	public static final String DEL_FLAG_NO="1";//有效
	
	private Long id;
	private Long userId;
	private String email;
	private String password;
	private String pwdVersion; 
	private String type;
	/**1：已验证;0：未验证**/
	private String isCheck;
	private String delFlag;
	private Date createTime;
	private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
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
