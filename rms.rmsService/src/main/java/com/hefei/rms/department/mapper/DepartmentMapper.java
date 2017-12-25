package com.hefei.rms.department.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.department.po.Department;
import com.hefei.service.framework.base.mapper.Mapper;

public interface DepartmentMapper extends Mapper{

	public void saveDepartmentInfo(Department beInfo);

	public void updateDepartment(Map<String, Object> map);

	public Department getDepartmentById(Long id);
	public Department getDepartmentByName(Map map);
	public List<Department> findDepartmentPaginationByCompanyItems(Map map);
	
	public int findDepartmentPaginationByCompanyCount(Map map);
	
}
