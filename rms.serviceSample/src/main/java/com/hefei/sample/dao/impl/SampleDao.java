package com.hefei.sample.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.sample.dao.ISampleDao;
import com.hefei.sample.mapper.SampleMapper;
import com.hefei.sample.po.Sample;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SampleDao implements ISampleDao{
	
	private static final Logger logger = Logger.getLogger(SampleDao.class);
	
	@Autowired
	private SampleMapper sampleMapper;
	private IdManager idManager = new IdManagerImpl();
	
	@Override
	public Sample save(Sample sample) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			sample.setId(idManager.getNextId());
			sampleMapper.save(sample);
			Warning.warnFuntionTimer("SampleDao", "save", null, (System.currentTimeMillis() - begintimer), begintimer);
			return sample;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public Sample getById(Long id) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Sample sample = sampleMapper.getById(id);
			Warning.warnFuntionTimer("SampleDao", "getById", null, (System.currentTimeMillis() - begintimer), begintimer);
			return sample;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

}
