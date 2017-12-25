package com.hefei.user.api.mobile.manager;

import com.hefei.common.exception.ClientException;
import com.hefei.user.api.mobile.vo.MobileInfo;

public interface IMobileManager {

	public MobileInfo getLoginMobileByUserId(Long userId) throws ClientException;
	
	public MobileInfo getByMobile(String mobile, String type) throws ClientException;
}
