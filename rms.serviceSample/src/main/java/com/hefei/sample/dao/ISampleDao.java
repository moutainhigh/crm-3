package com.hefei.sample.dao;

import com.hefei.sample.po.Sample;
import com.hefei.service.framework.exception.DaoException;


public interface ISampleDao {

	public Sample save(Sample sample)throws DaoException;
	
	public Sample getById(Long id) throws DaoException;
}
