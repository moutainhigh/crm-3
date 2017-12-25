package com.hefei.user.mobile.mapper;

import java.util.Map;

import com.hefei.service.framework.base.mapper.Mapper;
import com.hefei.user.mobile.po.Mobile;

public interface MobileMapper extends Mapper{

	public void saveMobile(Mobile mobile);
	
	public Mobile getByMobile(Map map);
	public Mobile getLoginMobileByUserId(Map map);
}
