package com.hefei.user.info.service;

import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.info.po.User;

public interface IUserService {

	public User saveUser(User user) throws ServiceException;
	
	public User getById(Long id) throws ServiceException;
	
	public User getByIdNo(String idNo) throws ServiceException;
	
	public void updateUser(User user)throws ServiceException;
}
