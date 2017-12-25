package com.hefei.frontsample.sample.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.frontsample.sample.client.ISampleManager;
import com.hefei.frontsample.sample.client.SampleManager;
import com.hefei.frontsample.sample.client.SampleVO;
import com.hefei.frontsample.sample.service.ISampleService;

@Service
public class SampleService implements ISampleService{
	
	private static Logger logger = Logger.getLogger(SampleService.class);
	
	private ISampleManager sampleManager = new SampleManager();

	@Override
	public SampleVO create(SampleVO sample) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(Long userId, SampleVO sample) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination<SampleVO> findPagination(String type, int pageIndex,
			int pageSize) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SampleVO get(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
