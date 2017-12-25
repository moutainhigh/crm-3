package com.rmsClient.test;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.contract.manager.IContractManager;
import com.hefei.api.rms.contract.manager.impl.ContractManager;
import com.hefei.api.rms.contract.vo.ContractInfo;
import com.hefei.api.rms.department.manager.IDepartmentManager;
import com.hefei.api.rms.department.manager.impl.DepartmentManager;
import com.hefei.api.rms.department.vo.DepartmentInfo;

public class DepartmentManagerTest {

	private static void saveDepartment(){
		DepartmentInfo departmentInfo = new DepartmentInfo();
		//IdManager im = new IdManagerImpl();
		IDepartmentManager DepartmentM = new DepartmentManager();
		try {
			//Long id = im.getNextId();
			departmentInfo.setId(111l);
			departmentInfo.setCompanyId(12124313l);
			departmentInfo.setParentId(11l);
			departmentInfo.setDepartmentName("jishubu");
			departmentInfo.setDepartmentInfo("bumenjianjie");
			departmentInfo.setDelFlag("1");
			departmentInfo.setCreateTime(new Date());
			departmentInfo.setUpdateTime(new Date());
			DepartmentM.saveDepartment(departmentInfo);
			//System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//更改状态
	private static void updateDepartment(){
		IDepartmentManager DepartmentM = new DepartmentManager();
		try {
			DepartmentM.updateDepartment(112l,"0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			//查看合同信息
			private static void findDepartment(){
				IDepartmentManager DepartmentM = new DepartmentManager();
				Long id = 111l;
				try {
					DepartmentInfo departmentInfo = DepartmentM.getDepartmentById(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public static void main(String[] args) {
				//saveDepartment();
				//updateDepartment();
				findDepartment();
			}
}