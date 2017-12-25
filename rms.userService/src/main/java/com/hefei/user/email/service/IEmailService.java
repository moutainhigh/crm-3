package com.hefei.user.email.service;

import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.email.po.Email;

public interface IEmailService {
	public Email saveEmail(Email email) throws ServiceException;
	
	public Email getByEmail(String email, String type) throws ServiceException;
	
	public Email getLoginEmailByUserId(Long userId) throws ServiceException;
}
