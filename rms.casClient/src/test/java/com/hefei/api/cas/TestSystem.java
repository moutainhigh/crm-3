package com.hefei.api.cas;

import com.hefei.api.cas.system.manger.ISystemManager;
import com.hefei.api.cas.system.manger.impl.SystemManager;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public class TestSystem {

	static ISystemManager systemManager = new SystemManager();
	
	public static void main(String[] args) {
//		t1();
		t2();
//		t3();
	}


	public static void t1(){
		try {
			SystemInfo s = systemManager.getById(1001L);
			System.out.print(s);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void t2(){
		try {
			SystemInfo s = new SystemInfo();
			s.setSystemName("这是测试系统A");
			s = systemManager.create(s);
			System.out.print(s);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void t3(){
		try {
			 Pagination<SystemInfo> p =  systemManager.find(null, 1, 1);
			System.out.print(p);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
