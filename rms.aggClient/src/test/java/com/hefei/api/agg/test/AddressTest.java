package com.hefei.api.agg.test;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import com.hefei.api.agg.location.manager.ILocationManager;
import com.hefei.api.agg.location.manager.impl.LoactionManager;
import com.hefei.api.agg.location.po.AddressInfo;

public class AddressTest {

	
	public static void main(String[] args) throws Exception{
		
		
		ILocationManager locLoactionManager = new LoactionManager();
		
		String province = "安徽省";
		String city = "合肥市";
		String areas = "蜀山区";
		
		String code = "340000";
		
		AddressInfo addressInfo = locLoactionManager.getAddressByCode(code);
		System.out.println("addressInfo = " + addressInfo);
		
//		Map<String, String> map = locLoactionManager.getCodeByAddress(province, city, areas);
//		System.out.println("map = " + map);
		
	}
}
