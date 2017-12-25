package com.hefei.user.info.mapper;

import com.hefei.service.framework.base.mapper.Mapper;
import com.hefei.user.info.po.User;

public interface UserMapper extends Mapper{

	public void saveUser(User user);
	
	public User getById(Long id);
	public User getByIdNo(String idNo);
	public void updateUser(User user);
}
