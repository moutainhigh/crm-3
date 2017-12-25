package com.hefei.rms.socialsecurity.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.socialsecurity.dao.ISSRateDao;
import com.hefei.rms.socialsecurity.mapper.SSRateMapper;
import com.hefei.rms.socialsecurity.po.SSRate;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SSRateDao implements ISSRateDao{

	private static final Logger logger = Logger.getLogger(SSRateDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SSRateMapper ssRateMapper;
	@Override
	public SSRate getSSRate(String provinceCode, String cityCode) throws DaoException {
		if(provinceCode == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		SSRate ssRate = null;
		try {
			Map map = new HashMap();
			map.put("provinceCode", provinceCode);
			map.put("cityCode", cityCode);
			ssRate = ssRateMapper.getSSRate(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SSRateDao", "getSSRate", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return ssRate;
	}

}
