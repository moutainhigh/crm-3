package com.hefei.rms.socialsecurity.mapper;

import java.util.Map;

import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SocialsecurityMapper extends Mapper{

	public EmployeeSS getEmployeeSS(Map map);
	
	public void saveEmployeeSS(EmployeeSS employeeSS);
	
	public void updateEmployeeSS(EmployeeSS employeeSS);
}
