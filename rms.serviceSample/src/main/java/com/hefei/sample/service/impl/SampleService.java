package com.hefei.sample.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.sample.dao.ISampleDao;
import com.hefei.sample.po.Sample;
import com.hefei.sample.service.ISampleService;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class SampleService implements ISampleService{

	private static Logger logger= Logger.getLogger(SampleService.class);
	@Autowired
	private ISampleDao sampleDao;
	
	@Override
	public Sample saveSample(Sample sample) throws ServiceException {
		try {
			return sampleDao.save(sample);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	public Sample getById(Long id) throws ServiceException{
		try {
			return sampleDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
}
