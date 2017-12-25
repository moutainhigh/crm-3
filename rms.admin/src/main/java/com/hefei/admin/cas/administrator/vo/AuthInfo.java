package com.hefei.admin.cas.administrator.vo;

public class AuthInfo {

	public static final String CHECK_YES = "Y";
	public static final String CHECK_NO = "N";
	private Long roleId;
	
	private String roleName;
	
	private String check;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
}
