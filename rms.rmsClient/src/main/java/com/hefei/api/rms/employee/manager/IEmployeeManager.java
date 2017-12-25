package com.hefei.api.rms.employee.manager;

import java.util.List;

import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public interface IEmployeeManager {

	public Pagination<EmployeeInfo> findEmployeePagination(String cardNo,String sex, String status, Long companyId, int pageIndex,int  pageSize) throws ClientException;
	public EmployeeInfo createEmployee(SalaryDTO salaryInfo, EmployeeInfo employeeInfo, Long companyId, Long departmentId, Long positionId) throws ClientException;
	
	public void updateEmployee(EmployeeInfo employeeInfo) throws ClientException;
	public EmployeeInfo getEmployee(Long employeeId) throws ClientException;
	public EmployeeInfo getEmployee(Long companyId,Long employeeId) throws ClientException;
	
	public EmployeeInfo getEmployeeByIdNo(String idNo) throws ClientException;
	public EmployeeInfo getEmployeeByUserId(Long userId) throws ClientException;
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId,Long employeeId) throws ClientException;
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId, Long departmentId,Long positionId)  throws ClientException;
	
	public void grantEmployeePosition(Long employeeId, String positionIds) throws ClientException;
}
