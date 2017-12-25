package com.hefei.rms.employee.dao;

import java.util.List;

import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.rms.employee.po.Employee;
import com.hefei.rms.employee.po.EmployeeCompany;
import com.hefei.rms.employee.po.EmployeePosition;
import com.hefei.rms.employee.po.UserEmployee;
import com.hefei.service.framework.exception.DaoException;

public interface IEmployeeDao {

	public Employee saveEmployee(Employee employee) throws DaoException;
	
	public EmployeeCompany getEmployeeCompany(Long id) throws DaoException;
	public EmployeeCompany getEmployeeCompany(Long companyId, Long employeeId) throws DaoException;
	public EmployeeCompany saveEmployeeCompany(EmployeeCompany employeeCompany) throws DaoException;
	public void updateEmployeeCompany(EmployeeCompany employeeCompany) throws DaoException;
	
	public UserEmployee getUserEmployeeByUserId(Long userid) throws DaoException;
	public UserEmployee getUserEmployeeByEmployeeId(Long employeeId) throws DaoException;
	public UserEmployee saveUserEmployee(UserEmployee userEmployee) throws DaoException;
	
	public EmployeePosition saveEmployeePosition(EmployeePosition employeePosition) throws DaoException;
	public void updateEmployee(Employee employeeInfo) throws DaoException;
	
	public List<EmployeeInfo> getEmployeePaginationItems(String cardNo, List<String> sex, List<String> status, Long companyId, Integer pageIndex, Integer pageSize) throws DaoException;
	public Integer getEmployeePaginationCount(String cardNo, List<String> sex, List<String> status, Long companyId) throws DaoException;
	public EmployeeInfo getEmployee(Long id) throws DaoException;
	public EmployeeInfo getEmployeeByIdNo(String idNo) throws DaoException;
	public EmployeeInfo getEmployeeByUserId(Long userId) throws DaoException;

	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId, Long employeeId) throws DaoException;
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId,Long departmentId, Long positionId) throws DaoException;
}
