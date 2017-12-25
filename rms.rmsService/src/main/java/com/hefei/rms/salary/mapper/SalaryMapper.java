package com.hefei.rms.salary.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.rms.salary.po.Salary;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SalaryMapper extends Mapper{

	public void saveSalary(Salary salaryInfo);
	public void updateSalary(Map<String, Object> map);
	public Salary getSalary(Map<String, Object> map);
	public List<SalarySSDTO> findSalaryPaginationItems(Map<String, Object> map);
	public int findSalaryPaginationCount(Map<String, Object> map);
	
	
}
