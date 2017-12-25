package com.hefei.user.api.email.manager;

import com.hefei.common.exception.ClientException;
import com.hefei.user.api.email.vo.EmailInfo;

public interface IEmailManager {

	public EmailInfo getLoginEmailByUserId(Long userId) throws ClientException;
	
	public EmailInfo getByEmail(String email, String type) throws ClientException;
}
