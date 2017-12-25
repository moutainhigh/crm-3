package com.hefei.api.cas;

import java.util.HashMap;
import java.util.Map;

import com.hefei.api.cas.role.manager.IRoleManager;
import com.hefei.api.cas.role.manager.impl.RoleManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public class TestRole {
	
	static IRoleManager roleManager = new RoleManager();
	public static void main(String[] args) throws Exception {
//		t1();
//				t2();
//		t3();
		t4();
	}
	
	public static void t1(){
		try {
			RoleInfo s = roleManager.getById(10001L);
			System.out.print(s);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void t2(){
		try {
			RoleInfo s = new RoleInfo();
			s.setSystemId(1001L);
			s.setCompanyId(null);
			s.setRoleName("测试角色");
			s.setRemark("X");
			s= roleManager.create(s);
			System.out.print(s);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void t3(){
		try {
			 Pagination<RoleInfo> p =  roleManager.find(null,null,null, 1, 1);
			System.out.print(p);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void t4(){
		try {
			Map<Long, String> moduleIdAndCheck = new HashMap<Long, String>();
			moduleIdAndCheck.put(1L, "n");
			moduleIdAndCheck.put(2L, "n");
			moduleIdAndCheck.put(3L, "n");
			roleManager.roleAuthModule(100L, moduleIdAndCheck);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
