package com.hefei.rms.employee.service;

import java.util.List;

import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.page.Pagination;
import com.hefei.rms.employee.po.Employee;
import com.hefei.rms.employee.po.EmployeeCompany;
import com.hefei.rms.employee.po.EmployeePosition;
import com.hefei.rms.employee.po.UserEmployee;
import com.hefei.service.framework.exception.ServiceException;

public interface IEmployeeService {
	public Employee createEmployee(Employee employee) throws ServiceException;
//	public EmployeeInfo createEmployee(EmployeeInfo employeeInfo) throws ServiceException;
	
	public EmployeeInfo createEmployee(SalaryDTO salaryInfo, EmployeeInfo employeeInfo, Long companyId, Long departmentId, Long positionId) throws ServiceException;
	
	public EmployeeCompany getEmployeeCompany(Long id) throws ServiceException;
	public EmployeeCompany getEmployeeCompany(Long companyId, Long employeeId) throws ServiceException;
	public EmployeeCompany addEmployeeToCompany(EmployeeCompany employeeCompany)  throws ServiceException;
	public UserEmployee createEmployeeLogin(Long userId, Long employeeId) throws ServiceException;
	
	public void updateEmployee(EmployeeInfo employeeInfo) throws ServiceException;
	
	public Pagination<EmployeeInfo> findPagination(String cardNo, String sexs, String statuses, Long companyId, Integer pageIndex, Integer pageSize) throws ServiceException;
	public EmployeeInfo getByIdNo(String idNo) throws ServiceException;
	public EmployeeInfo getEmployeeInfo(Long employeeId) throws ServiceException;
	public EmployeeInfo getEmployeeInfoByUserId(Long userId) throws ServiceException;
	public EmployeeInfo getEmployeeInfo(Long companyId, Long employeeId) throws ServiceException;
	
	public List<EmployeePosition> grantPosition(Long employeeId, List<Long> positionIds)  throws ServiceException;
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId,Long employeeId) throws ServiceException;
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId, Long departmentId,Long positionId)  throws ServiceException;
}
