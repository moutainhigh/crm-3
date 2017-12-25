package com.hefei.user.mobile.dao;

import com.hefei.service.framework.exception.DaoException;
import com.hefei.user.mobile.po.Mobile;

public interface IMobileDao {

	public Mobile saveMobile(Mobile mobile) throws DaoException;
	
	public Mobile getByMobile(String mobile, String type) throws DaoException;
	
	public Mobile getLoginMobileByUserId(Long userId) throws DaoException;
}
