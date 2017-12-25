package com.hefei.agg.log.dao;

import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.service.framework.exception.DaoException;

public interface IUserLogHistoryDao {

	public int saveUserLoginHistory(UserLoginHistory userLoginHistory)throws DaoException;

}
