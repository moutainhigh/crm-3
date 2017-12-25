package com.hefei.api.user.test;

import com.hefei.api.user.reg.manager.IRegisterManager;
import com.hefei.api.user.reg.manager.impl.RegisterManager;
import com.hefei.api.user.reg.vo.RegisterRequestByEmail;
import com.hefei.common.exception.ClientException;

public class TestRegister {

	static IRegisterManager registerManager =  new RegisterManager();
	
	public static void main(String[] args) {
		t1();
	}
	public static void t1(){
		RegisterRequestByEmail registerRequestByEmail = new RegisterRequestByEmail();
		registerRequestByEmail.setBrowser("");
		registerRequestByEmail.setEmail("3@1.com");
		registerRequestByEmail.setIp("127.0.0.1");
		registerRequestByEmail.setMac("mac");
		registerRequestByEmail.setPlainPassword("123456");
		registerRequestByEmail.setRegisterPosition("1");
		registerRequestByEmail.setRegisterType("1");
		registerRequestByEmail.setRegisterSubType("1");
		
		try {
			Long userid = registerManager.regByEmail(registerRequestByEmail);
			System.out.print(userid);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
