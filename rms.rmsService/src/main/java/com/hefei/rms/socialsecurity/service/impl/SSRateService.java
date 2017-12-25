package com.hefei.rms.socialsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.rms.socialsecurity.dao.ISSRateDao;
import com.hefei.rms.socialsecurity.po.SSRate;
import com.hefei.rms.socialsecurity.service.ISSRateService;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class SSRateService implements ISSRateService{

	@Autowired
	private ISSRateDao ssRateDao;
	
	public SSRate getSSRate(String provinceCode, String cityCode) throws ServiceException {
		try {
			return ssRateDao.getSSRate(provinceCode, cityCode);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	

}
