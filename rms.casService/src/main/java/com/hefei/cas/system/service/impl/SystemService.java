package com.hefei.cas.system.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.cas.system.dao.ISystemDao;
import com.hefei.cas.system.po.Sys;
import com.hefei.cas.system.service.ISystemService;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class SystemService implements ISystemService{
	private static final Logger logger = Logger.getLogger(SystemService.class);
	
	@Autowired
	private ISystemDao systemDao;
		
	@Override
	public Sys createSystem(SystemInfo sys) throws ServiceException {
		try {
			if(sys == null)
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			if(StringUtils.isBlank(sys.getSystemName()))
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Sys system = systemDao.getBySystemName(sys.getSystemName());
			if(system != null)
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
			
			system = new Sys();
			BeanUtils.copyProperties(sys, system);
			Date now = Calendar.getInstance().getTime();
			system.setCreateTime(now);
			system.setUpdateTime(now);
			system.setDelFlag(SystemInfo.DEL_FLAG_NO);
			systemDao.saveSystem(system);
			return system;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	public Pagination<SystemInfo> find(String systemName, int pageSize, int pageIndex) throws ServiceException{
		try {
			int totalCount = systemDao.getTotalCount(systemName);
			List<Sys> list = systemDao.findSys(systemName, pageIndex, pageSize);
			List<SystemInfo> destList = null;
			if(list != null && list.size() > 0){
				destList = new ArrayList<SystemInfo>();
				SystemInfo sysInfo = null;
				for(Sys sys : list){
					sysInfo = new SystemInfo();
					BeanUtils.copyProperties(sys, sysInfo);
					destList.add(sysInfo);
				}
			}
			Pagination<SystemInfo> pagination = new Pagination<SystemInfo>();
			pagination.setItems(destList);
			pagination.setPageIndex(pageIndex);
			pagination.setPageSize(pageSize);
			pagination.setTotalCount(totalCount);
			return pagination;
			
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Sys getById(Long id) throws ServiceException {
		try {
			return systemDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
