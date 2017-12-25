package com.hefei.agg.address.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hefei.agg.common.spring.AppContextHandler;
import com.hefei.agg.location.po.Location;
import com.hefei.agg.location.service.impl.LocationService;
import com.hefei.common.util.StringUtils;

public class AddressUtil {
	
	private static final Logger logger = Logger.getLogger(AddressUtil.class);
	
	private static LocationService locationService = (LocationService) AppContextHandler.getBean("locationService");
	
	private static Map<String, Location> provinceCodeObj = new HashMap<String, Location>();
	private static Map<String, Location> cityCodeObj = new HashMap<String, Location>();
	private static Map<String, Location> areasCodeObj = new HashMap<String, Location>();
	
	private static Map<String, String> provinceNameCode = new HashMap<String, String>();
	private static Map<String, String> cityNameCode = new HashMap<String, String>();
	private static Map<String, String> areasNameCode = new HashMap<String, String>();
	
	private static Map<String, Location> codeAddress = new HashMap<String, Location>();
	
	/**
	 * 市MAP key：省；value：该省的市LIST。
	 */
	public static HashMap<String, List<Location>> CITY_MAP = new HashMap<String, List<Location>>();
	
	/**
	 * 镇MAP key：市；value：该市的镇LIST。
	 */
	public static HashMap<String, List<Location>> AREA_MAP = new HashMap<String, List<Location>>();
	
	public static Location getAddressByCode(String code){
		return codeAddress.get(code);
	}
	
	public static String getProvinceCodeByName(String name){
		String name1 = null;
		if(name.indexOf("省") != -1){
			name1=name.substring(0,name.indexOf("省"));
			return provinceNameCode.get(name1);
		}else{
			return provinceNameCode.get(name);
		}
	}
	public static String getCityCodeByName(String name){
		
		return cityNameCode.get(name);
	}
	public static String getAreaCodeByName(String name){
		return areasNameCode.get(name);
	}
	
	public static void initAddressData() throws Exception {
		
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		LocationService locationService = (LocationService) ctx.getBean("locationService");
		
		long begin = System.currentTimeMillis();
		
		try {
			//加载所有code对应地址
			List<Location> allAddressList = locationService.getAllAddressList();
			for(Location location : allAddressList){
				codeAddress.put(location.getCode(), location);
			}
			
			//获取所有省列表
			List<Location> provincesList = locationService.getAllProvinceList();
			if(provincesList == null){
				throw new Exception("province list is null");
			}
			
			// 遍历省
			for(Location province : provincesList){
				String provinceCode = province.getCode();
				provinceCodeObj.put(provinceCode, province);
				provinceNameCode.put(province.getName(),provinceCode);
				
				//获取该省下所有市列表
				List<Location> cityList = locationService.getCityListByCode(provinceCode);
				CITY_MAP.put(province.getCode(), cityList);
				// 遍历市
				if(cityList != null){
					for(Location city : cityList){
						
						String cityCode = city.getCode();
						cityCodeObj.put(cityCode, city);
						cityNameCode.put(city.getName(), cityCode);
						
						List<Location> areaslist = locationService.getAreasListByCode(cityCode);
						AREA_MAP.put(city.getCode(), areaslist);
						// 遍历区域
						if (areaslist != null){
							for (Location area : areaslist ){
								areasCodeObj.put(area.getCode(), area);
								areasNameCode.put(area.getName(),area.getCode());
							}
							
							
						}
					}
				}
						
			}
				
			logger.info(" 地址封装耗时:" + (System.currentTimeMillis() - begin));	
				
					
		} catch (Exception e) {
			logger.error("initAddressData error:", e);
			throw e;
		}
		
	}
	
	/**
	 * 匹配市
	 * @param addStr 地址
	 * @param provCode 省编号
	 * @return
	 */
	public static Map<String,String> getCityIndex(String addStr,String provCode) {
		Map<String,String>  addrMap=new HashMap<String,String>();
		HashMap<String, List<Location>> cityMap =CITY_MAP;
		List<Location> list=cityMap.get(provCode);
		for (Location location : list) {
			String cityName=location.getName();
			if(StringUtils.isNotBlank(cityName)){
				String cityName1= cityName;
				if(cityName.indexOf("市") != -1){
					cityName1=cityName.substring(0,cityName.indexOf("市"));
				}
				if(addStr.contains(cityName)||addStr.contains(cityName1)){
					String cityCode=location.getCode();
					addrMap.put("cityCode",cityCode);
					addrMap.put("addStr",addStr.substring(addStr.indexOf(cityName)+cityName.length()));
					return addrMap;
				}
			}
		}
		return null;
	}
	
	public static Map<String,String> getAreaIndex(String addStr,String cityCode) {
		Map<String,String>  addrMap=new HashMap<String,String>();
		HashMap<String, List<Location>> areaMap=AREA_MAP;
		List<Location> list=areaMap.get(cityCode);
		for (Location location : list) {
			String areaName=location.getName();
			if(addStr.contains(areaName)){
				String areaCode=location.getCode();
				addrMap.put("areasCode",areaCode);
				addrMap.put("addStr",addStr.substring(addStr.indexOf(areaName)+areaName.length()));
				return addrMap;
			}
		}
		return null;
	}
}
