package com.hefei.user.info.dao;

import com.hefei.service.framework.exception.DaoException;
import com.hefei.user.info.po.User;


public interface IUserDao {

	public User saveUser(User user)throws DaoException;
	
	public User getById(Long id) throws DaoException;
	public User getByIdNo(String idNo) throws  DaoException;
	public void updateUser(User user)throws DaoException;
}
