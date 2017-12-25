package com.hefei.rms.salary.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.salary.po.SalaryHistory;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SalaryHistoryMapper extends Mapper{

	public void saveSalaryHistory(SalaryHistory salaryHistoryInfo);
	
	public List<SalaryHistory> findSalaryHistoryPaginationItems(Map<String, Object> map);
	
	public int findSalaryHistoryPaginationCount(Map<String, Object> map);
}
