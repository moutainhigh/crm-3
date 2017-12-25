package com.hefei.cas.system.dao;

import java.util.List;

import com.hefei.cas.system.po.Sys;
import com.hefei.service.framework.exception.DaoException;

public interface ISystemDao {

	public void saveSystem(Sys system) throws DaoException;
	
	public Sys getById(Long id) throws DaoException;
	
	public Sys getBySystemName(String systemName) throws DaoException;
	
	public int getTotalCount(String systemName) throws DaoException;
	
	public List<Sys> findSys(String systemName, int pageIndex, int pageSize) throws DaoException;
}
