package com.hefei.api.cas;

import java.util.ArrayList;
import java.util.List;

import com.hefei.api.cas.module.manager.IModuleManager;
import com.hefei.api.cas.module.manager.impl.ModuleManager;
import com.hefei.api.cas.module.vo.ModuleInfo;

public class TestModule {

	static IModuleManager moduleManager = new ModuleManager();
	public static void main(String[] args) {
//		testgetModuleBySystemId();
//		
//		testgetModuleById();
//		
//		testgetModuleByRole();
//		
//		testgetModuleByParentId();
//		
//		testcreateModule();
//		
		testdeleteModule();
	}

	public static void testgetModuleBySystemId(){
		List<Long> systemIds = new ArrayList<Long>();
//		systemIds.add(1001L);
		systemIds.add(1002L);
		
		boolean withButton = false;
		try {
			List<ModuleInfo> l = moduleManager.getModuleBySystemId(systemIds, withButton);
			System.out.println(l.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testgetModuleById(){
		try {
			ModuleInfo mi = moduleManager.getModuleById(100101L);
			System.out.println(mi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testgetModuleByRole(){
		List<Long> roleIds = new ArrayList<Long>();
		roleIds.add(10001L);
		roleIds.add(1002L);
		
		try {
			List<ModuleInfo> l = moduleManager.getModuleByRole(roleIds);
			System.out.println(l.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  void testgetModuleByParentId(){
		try {
			List<ModuleInfo> l = moduleManager.getModuleByParentId(1001L);
			System.out.println(l.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testcreateModule(){
		ModuleInfo mi = new ModuleInfo();
		mi.setIconPath("http:");
		mi.setName("测试");
		mi.setOrders(1);
		mi.setParentId(1001L);
		mi.setSystemId(1L);
		mi.setType("1");
		mi.setUrl("x");
		try {
			mi = moduleManager.createModule(mi);
			System.out.println(mi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testdeleteModule(){
		List<Long> moduleIds = new ArrayList<Long>();
		moduleIds.add(794001641232797696L);
		moduleIds.add(794001819469746176L);
		moduleIds.add(794002776450535424l);
		
		
		
		try {
			moduleManager.deleteModule(moduleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
