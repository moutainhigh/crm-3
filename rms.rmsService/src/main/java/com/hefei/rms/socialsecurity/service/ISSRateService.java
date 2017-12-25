package com.hefei.rms.socialsecurity.service;

import com.hefei.rms.socialsecurity.po.SSRate;
import com.hefei.service.framework.exception.ServiceException;

public interface ISSRateService {

	public SSRate getSSRate(String provinceCode, String cityCode) throws ServiceException;
}
