package com.hefei.api.agg.test;

import java.util.Date;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.agg.log.manager.IUserLogManager;
import com.hefei.api.agg.log.manager.impl.UserLogManager;
import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.response.BaseResponse;

public class UserLoginHistoryTest {

	
	
	public static void main(String[] args) {
		IUserLogManager userLogManager = new UserLogManager();
		IdManager idManager = new IdManagerImpl();
		UserLoginHistory ulh = new UserLoginHistory();
		try {
			ulh.setId(String.valueOf(idManager.getNextId()));
			ulh.setUserId("111");
			ulh.setIp("127.0.0.1");
			ulh.setLoginStyle(1);
			ulh.setMac("aaa-aaa-sss-ggg");
			ulh.setSubType("firefox");
			ulh.setLoginType(1);
			ulh.setCreateTime(new Date());
			ulh.setUpdateTime(new Date());
		
			BaseResponse bresp = userLogManager.saveUserLoginHistory(ulh);
			System.out.println(bresp.getReturnCode());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
