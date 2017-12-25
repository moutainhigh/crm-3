package com.hefei.rms.employee.service;

import java.util.List;

import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;

public interface IEmployeeService {

	public EmployeeInfo getEmployee(Long employeeId) throws BusinessException;
	public EmployeeInfo getEmployee(Long companyId,Long employeeId) throws BusinessException;
	public EmployeeInfo getEmployeeByIdNo(String idNo) throws BusinessException;
	
	public EmployeeInfo createEmployee(SalaryDTO salaryInfo, EmployeeInfo employeeInfo, Long companyId, Long departmentId, Long positionId) throws BusinessException;
	
	public void updateEmployee(EmployeeInfo employeeInfo) throws BusinessException;
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId, Long employeeId) throws BusinessException;
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId, Long departmentId,Long positionId)  throws BusinessException;
	public Pagination<EmployeeInfo> findEmployeePagination(String cardNo, String sex, String status,Long companyId, int pageIndex, int pageSize) throws BusinessException;
	public void grantEmployeePosition(Long employeeId, String positionIds) throws BusinessException;
}
