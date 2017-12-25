package com.hefei.user.email.dao;

import com.hefei.service.framework.exception.DaoException;
import com.hefei.user.email.po.Email;

public interface IEmailDao {

	public Email saveEmail(Email email) throws DaoException;
	
	public Email getByEmail(String email, String type) throws DaoException;
	
	public Email getLoginEmailByUserId(Long userId) throws DaoException;
}
