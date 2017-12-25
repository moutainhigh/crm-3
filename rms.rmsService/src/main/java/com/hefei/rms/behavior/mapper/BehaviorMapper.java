package com.hefei.rms.behavior.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.service.framework.base.mapper.Mapper;

public interface BehaviorMapper extends Mapper{

	public List<BehaviorInfo> findBehavior(Long employeeId,String month);
	public void saveBehaviorInfo(BehaviorInfo beInfo);
	public void updateBehavior(Map<String, Object> map);
}
