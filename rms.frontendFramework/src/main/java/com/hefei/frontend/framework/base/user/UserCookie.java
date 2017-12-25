package com.hefei.frontend.framework.base.user;

public class UserCookie {

	private Long userId;
	
	private String nickname;

	private Long employeeId;

	
	@Override
	public String toString() {
		return "UserCookie [userId=" + userId + ", nickname=" + nickname
				+ ", employeeId=" + employeeId + "]";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
