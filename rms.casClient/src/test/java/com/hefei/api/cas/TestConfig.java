package com.hefei.api.cas;

import com.hefei.api.cas.config.manager.impl.CasConfigManager;
import com.hefei.common.exception.ClientException;

public class TestConfig {

	static CasConfigManager cssConfigManager = new CasConfigManager();
	public static void main(String[] args) {
		try {
			cssConfigManager.getRMSUserRoleId();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
