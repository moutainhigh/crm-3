package com.hefei.rms.department.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.department.dao.IDepartmentDao;
import com.hefei.rms.department.mapper.DepartmentMapper;
import com.hefei.rms.department.po.Department;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class DepartmentDao implements IDepartmentDao{

	private static final Logger logger = Logger.getLogger(DepartmentDao.class);
	
	@Autowired
	private DepartmentMapper mapper;
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Override
	public Department saveDepartmentInfo(Department department) throws DaoException {
		if(department == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try{
			Long id = idManager.getNextId();
			department.setId(id);
			mapper.saveDepartmentInfo(department);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("DepartmentDao", "saveDepartmentInfo", null, (System.currentTimeMillis() - begintimer), begintimer);
		return department;
	}
	@Override
	public void updateDepartment(Long id, String delFlag, Date updateTime) throws DaoException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("delFlag", delFlag);
		map.put("updateTime", updateTime);
		long begintimer = System.currentTimeMillis();
		mapper.updateDepartment(map);
		Warning.warnFuntionTimer("DepartmentDao", "updateDepartment", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public Department getDepartmentById(Long id) throws DaoException {
		if(id == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		Department department = null;
		try{
			department = mapper.getDepartmentById(id);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("DepartmentDao", "getDepartmentById", null, (System.currentTimeMillis() - begintimer), begintimer);
		return department;
	}
	public Department getDepartmentByName(Long companyId, String departmentName) throws DaoException{
		if(companyId == null || StringUtils.isBlank(departmentName)){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		Department department = null;
		try{
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("departmentName", departmentName);
			map.put("delFlag", DepartmentInfo.DELFLAG_NO);
			department = mapper.getDepartmentByName(map);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("DepartmentDao", "getDepartmentByName", null, (System.currentTimeMillis() - begintimer), begintimer);
		return department;
	}
	@Override
	public List<Department> findDepartmentPaginationByCompanyItems(
			Long companyId, String delFlag, int pageIndex, int pageSize)throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("delFlag", delFlag);
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			
			List<Department> departments = mapper.findDepartmentPaginationByCompanyItems(map);
			Warning.warnFuntionTimer("DepartmentDao", "findDepartmentPaginationByCompanyItems", null,(System.currentTimeMillis() - begintimer), begintimer);
			return departments;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	@Override
	public int findDepartmentPaginationByCompanyCount(Long companyId, String delFlag) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("delFlag", delFlag);
			
			int count = mapper.findDepartmentPaginationByCompanyCount(map);
			Warning.warnFuntionTimer("DepartmentDao", "findDepartmentPaginationByCompanyCount", null,(System.currentTimeMillis() - begintimer), begintimer);
			return count;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
