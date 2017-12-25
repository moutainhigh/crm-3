package com.hefei.frontend.framework.http.request;

import com.hefei.frontend.framework.base.company.CompanyCookie;
import com.hefei.frontend.framework.base.user.UserCookie;

public class CommonParameter {

	private UserCookie userCookie;
	
	private CompanyCookie companyCookie;

	public UserCookie getUserCookie() {
		return userCookie;
	}

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public CompanyCookie getCompanyCookie() {
		return companyCookie;
	}

	public void setCompanyCookie(CompanyCookie companyCookie) {
		this.companyCookie = companyCookie;
	}

}
