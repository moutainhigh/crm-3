package com.hefei.api.cas;

import java.util.List;

import com.hefei.api.cas.admin.auth.manager.IAdminAuthManager;
import com.hefei.api.cas.admin.auth.manager.impl.AdminAuthManager;
import com.hefei.api.cas.admin.cas.manager.IAdminCasManager;
import com.hefei.api.cas.admin.cas.manager.impl.AdminCasManager;
import com.hefei.api.cas.admin.manager.IAdminManager;
import com.hefei.api.cas.admin.manager.impl.AdminManager;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.module.util.ModuleTree;
import com.hefei.api.cas.module.util.ModuleTreeFactory;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public class TestAdmin {

	static IAdminAuthManager adminAuthManager = new AdminAuthManager();
	static IAdminCasManager adminCasManager = new AdminCasManager();
	static IAdminManager adminManager = new AdminManager();
	
	public static void main(String[] args) {
//		testauth();
//		testgetadminmenu();
//		testMenu();
//		testcreate();
		
//		testgetById();
//		
		testfind();
	}
	
	
	public static void testfind(){
		String username=null;//"admin";
		String mobile= null;//"admin";
		String email="s@s";//"admin";
		int pageSize = 1;
		int pageIndex=1;
		Pagination<AdminInfo> p;
		try {
			p = adminManager.find(username, mobile, email, pageSize, pageIndex);
			System.out.println(p);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void testcreate(){
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setCreator(1L);
		adminInfo.setEmail("s@s");
		adminInfo.setMobileNo("135242424");
		adminInfo.setPassword("123455");
		adminInfo.setRealName(null);
		adminInfo.setUpdater(1L);
		adminInfo.setUsername("admin1");
		
		try {
			adminInfo = adminManager.create(adminInfo);
			System.out.print(adminInfo);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testgetById(){
		try {
			AdminInfo a = adminManager.getById(79440331837709172L);
			System.out.print(a);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void testMenu(){
		try {
			List<ModuleInfo>  list = adminCasManager.getModuleMenus(1001L, 100L);
			
			List<ModuleTree> moduleTree = ModuleTreeFactory.generate(list);
			System.out.println(moduleTree);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
	}
	public static void testgetadminmenu(){
		try {
			List<ModuleInfo>  list = adminCasManager.getModuleMenus(1001L, 100L);
			System.out.println(list.size());
			System.out.println(list.get(0));
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
	public static void testauth(){
		try {
			AdminInfo adminInfo = adminAuthManager.auth("admin", "admin");
			System.out.println(adminInfo);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
