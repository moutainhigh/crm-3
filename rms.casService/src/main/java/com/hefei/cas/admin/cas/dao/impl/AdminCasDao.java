package com.hefei.cas.admin.cas.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.cas.admin.cas.dao.IAdminCasDao;
import com.hefei.cas.admin.cas.mapper.AdminCasMapper;
import com.hefei.cas.admin.dao.impl.AdminDao;
import com.hefei.cas.module.po.Module;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class AdminCasDao implements IAdminCasDao{

	private static final Logger logger = Logger.getLogger(AdminDao.class);
	
	@Autowired
	private AdminCasMapper adminCasMapper;
	
	public List<Module> getModuleMenus(Long adminId, Long systemId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("adminId", adminId);
			map.put("moduleType", ModuleInfo.TYPE_MENU);
			map.put("systemId", systemId);
			List<Module> modules = adminCasMapper.getModuleMenus(map);
			Warning.warnFuntionTimer("AdminCasDao", "getModuleMenus", null, (System.currentTimeMillis() - begintimer), begintimer);
			Warning.warnCollectionSize(modules, "AdminCasDao", "getModuleMenus", null,  begintimer);
			return modules;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}
}
