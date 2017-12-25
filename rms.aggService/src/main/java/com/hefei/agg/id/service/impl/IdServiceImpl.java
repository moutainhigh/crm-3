package com.hefei.agg.id.service.impl;

import org.springframework.stereotype.Service;

import com.hefei.agg.id.service.IdService;
import com.hefei.agg.id.util.IdWorker;
import com.hefei.agg.util.AggConstants;

@Service
public class IdServiceImpl implements IdService{
	
	private IdWorker idworker = new IdWorker(AggConstants.WORKER_ID, AggConstants.DATA_CENTER_ID);
	
	@Override
	public Long getNextId() {
		Long nextId = idworker.nextId();
		return nextId;
	}

}
