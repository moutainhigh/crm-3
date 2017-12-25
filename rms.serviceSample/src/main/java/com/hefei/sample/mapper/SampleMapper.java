package com.hefei.sample.mapper;

import com.hefei.sample.po.Sample;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SampleMapper extends Mapper{

	public void save(Sample sample);
	
	public Sample getById(Long id);
}
