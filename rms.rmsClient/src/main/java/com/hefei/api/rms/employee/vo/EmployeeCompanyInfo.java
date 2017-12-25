package com.hefei.api.rms.employee.vo;

import java.util.HashMap;
import java.util.Map;

public class EmployeeCompanyInfo {

	/**
	 * 0：实习
1：试用
2：在职
3：离职
4：退休
	 */
	public static final String STATUS_PRACTICE ="0";
	public static final String STATUS_PROBATION ="1";
	public static final String STATUS_ONBOARD ="2";
	public static final String STATUS_LEAVE ="3";
	public static final String STATUS_RETIRE ="4";
	
	public static final Map<String, String> employeeStatusDict = new HashMap<String, String>();
	static{
		employeeStatusDict.put(STATUS_PRACTICE, "实习");
		employeeStatusDict.put(STATUS_PROBATION, "试用 ");
		employeeStatusDict.put(STATUS_ONBOARD, "在职");
		employeeStatusDict.put(STATUS_LEAVE, "离职");
		employeeStatusDict.put(STATUS_RETIRE, "退休");
	}
}
