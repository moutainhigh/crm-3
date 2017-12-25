package com.hefei.rms.leave.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.leave.po.Leave;
import com.hefei.service.framework.base.mapper.Mapper;

public interface LeaveMapper extends Mapper{

	public void saveLeave(Leave leave);
	
	public void saveLeaveAudit(Map map);
	
	public List<Leave> getLeavePaginationItems(Map map);
	public Integer getLeavePaginationCount(Map map);
	
	public Leave getLeave(Long id);
}
