package com.hefei.cas.admin.cas.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.cas.module.po.Module;
import com.hefei.service.framework.base.mapper.Mapper;

public interface AdminCasMapper extends Mapper{

	public List<Module> getModuleMenus(Map map);
}
