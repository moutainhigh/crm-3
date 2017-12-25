package com.hefei.api.rms.behavior.manager;

import java.util.List;

import com.hefei.api.rms.behavior.vo.BehaviorInfo;
import com.hefei.common.exception.ClientException;

public interface IBehaviorManager {

	public List<BehaviorInfo> findBehavior(Long employeeId,String month) throws ClientException;
	public Boolean saveBehavior(BehaviorInfo behaviorInfo) throws ClientException;
	public Boolean updateBehavior(Long id,String status) throws ClientException;
}
