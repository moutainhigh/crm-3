package com.hefei.rms.employee.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.rms.employee.po.Employee;
import com.hefei.rms.employee.po.EmployeeCompany;
import com.hefei.rms.employee.po.EmployeePosition;
import com.hefei.rms.employee.po.UserEmployee;
import com.hefei.service.framework.base.mapper.Mapper;

@SuppressWarnings("rawtypes")
public interface EmployeeMapper extends Mapper {

	public void saveEmployee(Employee employee);
	
	public EmployeeCompany getEmployeeCompanyById(Long id);
	public EmployeeCompany getEmployeeCompany( Map map);
	public void saveEmployeeCompany(EmployeeCompany employeeCompany);
	public void updateEmployeeCompany(EmployeeCompany employeeCompany);
	
	public UserEmployee getUserEmployeeByUserId(Long userid);
	public UserEmployee getUserEmployeeByEmployeeId(Long employeeId);
	public void saveUserEmployee(UserEmployee userEmployee);
	
	public void saveEmployeePosition(EmployeePosition employeePosition);
	
	public void updateEmployee(Employee employeeInfo);
	
	public List<EmployeeInfo> getEmployeePaginationItems(Map map);
	public Integer getEmployeePaginationCount(Map map);
	
	public EmployeeInfo getEmployee(Long id);
	public EmployeeInfo getEmployeeByIdNo(String idNo);
	public EmployeeInfo getEmployeeByUserId(Long userId);
	
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Map map);
	public void updateEmployeeDepartPosition(Map map);
}
