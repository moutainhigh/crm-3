package com.hefei.api.agg.id.manager.impl;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.agg.client.AggUrlConstants;
import com.hefei.api.agg.common.BaseManager;
import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.worker.IdWorkerConstants;
import com.hefei.api.agg.id.worker.IdWorkerFactory;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class IdManagerImpl extends BaseManager implements IdManager{
	
	private Logger logger = Logger.getLogger(IdManagerImpl.class);
	
	@Override
	public Long getNextId() throws Exception {
		long timer = System.currentTimeMillis();
		logger.info("getNextId time:" + timer );
		if(useLocalIdWorker()){
			return IdWorkerFactory.getIstance().getIdWorker().nextId();
		}
		RequestHead head = RequestHead.get(timer,ClientFactory.client);
		ObjectNode dataJson = JacksonUtil.createObjectNode();
		String result = SendRequest.send(AggUrlConstants.AGG_SERVER_ID_GET_NEXTID, dataJson, timer, head);
		logger.info("getNextId time:" + timer + " result:" + result);
		logger.info("getNextId " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
		BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
		Long id = null;
		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
			id = (Long) resp.getReturnObject();
		}
		return id;
	}

	private static boolean useLocalIdWorker(){
		if(IdWorkerConstants.IDWORKER_LOCAL == null)
			return false;
		if(IdWorkerConstants.IDWORKER_LOCAL_YES.equals(IdWorkerConstants.IDWORKER_LOCAL)){
			return true;
		}
		return false;
	}
}
