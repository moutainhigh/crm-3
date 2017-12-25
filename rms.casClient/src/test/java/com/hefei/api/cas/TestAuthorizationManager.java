package com.hefei.api.cas;

import com.hefei.api.cas.admin.auth.manager.IAuthorizationManager;
import com.hefei.api.cas.admin.auth.manager.impl.AuthorizationManager;
import com.hefei.common.exception.ClientException;

public class TestAuthorizationManager {

	static IAuthorizationManager authorizationManager = new AuthorizationManager();
	public static void main(String[] args) {
		t1();
	}

	static void t1(){
		try {
			boolean check = authorizationManager.adminHasAuthorization(1001L, "/cas/system/toCreate.do");
			System.out.println(check);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
