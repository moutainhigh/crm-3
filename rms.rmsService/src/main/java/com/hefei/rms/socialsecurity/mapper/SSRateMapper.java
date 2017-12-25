package com.hefei.rms.socialsecurity.mapper;

import java.util.Map;

import com.hefei.rms.socialsecurity.po.SSRate;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SSRateMapper extends Mapper{

	public SSRate getSSRate(Map map);
}
