package com.hefei.rms.contract.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.contract.dao.IContractDao;
import com.hefei.rms.contract.mapper.ContractMapper;
import com.hefei.rms.contract.po.ContractInfo;
import com.hefei.rms.position.dao.impl.PositionDao;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class ContractDao implements IContractDao {

private static final Logger logger = Logger.getLogger(PositionDao.class);
	
	@Autowired
	private ContractMapper Mapper;
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Override
	public void saveInfo(ContractInfo info) throws DaoException {
		if(info == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try{
			//Long id = idManager.getNextId();
			//beInfo.setId(id);
			Mapper.saveContract(info);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("ContractDao", "saveInfo", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public void updateContract(Long id, String auditStatus,String contractType, String delFlag, Date updateTime) throws DaoException {
		if(id == null || StringUtils.isBlank(auditStatus) || StringUtils.isBlank(contractType) || StringUtils.isBlank(delFlag) || updateTime == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("auditStatus", auditStatus);
			map.put("contractType", contractType);
			map.put("delFlag", delFlag);
			map.put("updateTime", updateTime);
			Mapper.updateContract(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " updateInterview error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("ContractDao", "updateContract", null, System.currentTimeMillis() - begintimer, begintimer);
	}
	@Override
	public List<ContractInfo> findContract(Long employeeId) throws DaoException {
		if(employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		List<ContractInfo> list = null;
		try{
			list = Mapper.findContract(employeeId);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" findContract error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("ContractDao", "findContract", null, (System.currentTimeMillis() - begintimer), begintimer);
		return list;
	}
}
