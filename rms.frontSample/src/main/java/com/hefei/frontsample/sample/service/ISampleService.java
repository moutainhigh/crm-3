package com.hefei.frontsample.sample.service;

import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.frontsample.sample.client.SampleVO;

public interface ISampleService {
	
	public SampleVO create(SampleVO sample) throws BusinessException;
	public void edit(Long userId, SampleVO sample) throws BusinessException;
	
	
	public Pagination<SampleVO> findPagination(String type, int pageIndex, int pageSize) throws BusinessException;
	
	public SampleVO get(Long id) throws BusinessException;
	
}
