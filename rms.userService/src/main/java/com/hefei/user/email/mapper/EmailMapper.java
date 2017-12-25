package com.hefei.user.email.mapper;

import java.util.Map;

import com.hefei.service.framework.base.mapper.Mapper;
import com.hefei.user.email.po.Email;

public interface EmailMapper extends Mapper{

	public void saveEmail(Email email);
	
	public Email getByEmail(Map map);
	
	public Email getLoginEmailByUserId(Map map);
}
