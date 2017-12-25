package com.hefei.rms.socialsecurity.dao;

import com.hefei.rms.socialsecurity.po.SSRate;
import com.hefei.service.framework.exception.DaoException;

public interface ISSRateDao {

	public SSRate getSSRate(String provinceCode, String cityCode) throws DaoException;
}
