package com.hefei.api.user.test;

import com.hefei.api.user.auth.manager.IAuthManager;
import com.hefei.api.user.auth.manager.impl.AuthManager;
import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.common.exception.ClientException;

public class AuthManagerTest {
	
	public static void testLoginUser(){
		IAuthManager authManager = new AuthManager();
		String loginName = "111@qq.com";
		String password = "111111";
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername(loginName);
		authRequest.setPlainPassword(password);
		authRequest.setIp("127.0.0.1");
		try {
			Long userId = authManager.login(authRequest);
			System.out.println(userId);
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public static void main(String[] args) {
		testLoginUser();
		//testRegUser();
	}
}
