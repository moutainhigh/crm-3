package com.hefei.sample.service;

import com.hefei.sample.po.Sample;
import com.hefei.service.framework.exception.ServiceException;

public interface ISampleService {

	public Sample saveSample(Sample sample) throws ServiceException;
	
	public Sample getById(Long id) throws ServiceException;
}
