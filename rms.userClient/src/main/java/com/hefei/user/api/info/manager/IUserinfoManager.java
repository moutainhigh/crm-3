package com.hefei.user.api.info.manager;

import com.hefei.common.exception.ClientException;
import com.hefei.user.api.info.vo.UserInfo;

public interface IUserinfoManager {

	public void updateUser(UserInfo userinfo) throws ClientException;
	
	public UserInfo getById(Long id) throws ClientException;
	
	public UserInfo getByIdNo(String idNo) throws ClientException;
}
