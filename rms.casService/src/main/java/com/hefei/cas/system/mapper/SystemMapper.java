package com.hefei.cas.system.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.cas.system.po.Sys;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SystemMapper extends Mapper {

	public void saveSystem(Sys system);
	
	public Sys getById(Long id);
	public Sys getBySystemName(String systemName);
	public int getTotalCount(Map map);
	
	public List<Sys> findSys(Map map);
}
