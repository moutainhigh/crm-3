package com.hefei.agg.location.mapper;

import java.util.List;

import com.hefei.agg.location.po.Location;
import com.hefei.service.framework.base.mapper.Mapper;

public interface LocationMapper extends Mapper{

	public List<Location> getAllAddressList();
	public List<Location> getAllProvinceList();
	public List<Location> getCityListByCode(String provinceCode);
	public List<Location> getAreasListByCode(String cityCode);
	
}
