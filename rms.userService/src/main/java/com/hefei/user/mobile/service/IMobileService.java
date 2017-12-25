package com.hefei.user.mobile.service;

import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.mobile.po.Mobile;

public interface IMobileService {

	public Mobile saveMobile(Mobile mobile) throws ServiceException;
	
	public Mobile getByMobile(String mobile, String type) throws ServiceException;
	
	public Mobile getLoginMobileByUserId(Long userId) throws ServiceException ;
}
