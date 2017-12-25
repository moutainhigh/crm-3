package com.hefei.rms.contract.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.contract.dao.IContractDao;
import com.hefei.rms.contract.po.ContractInfo;
import com.hefei.rms.contract.service.IContractService;
import com.hefei.rms.interview.service.impl.InterviewService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class ContractService implements IContractService{
	private static final Logger logger = Logger.getLogger(ContractService.class);
	@Autowired
	IContractDao contractDao;
	
	@Override
	public void saveInfo(ContractInfo info) throws ServiceException {
		if(info == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			info.setCreateTime(now);
			info.setUpdateTime(now);
			contractDao.saveInfo(info);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void updateContract(Long id, String auditStatus,String contractType, String delFlag) throws ServiceException {
			if(id == null || StringUtils.isBlank(auditStatus) || StringUtils.isBlank(contractType) || StringUtils.isBlank(delFlag)){
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			try {
				Date now = Calendar.getInstance().getTime();
				
				contractDao.updateContract(id, auditStatus, contractType,delFlag,now);
			} catch (DaoException e) {
				throw new ServiceException(e.getErrorCode());
			} catch (Exception e) {
				logger.error(RequestThreadLocal.getLoggerStr() + " updateEmployee error ",e);
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
			}
	}

	@Override
	public List<ContractInfo> findContract(Long employeeId) throws ServiceException {
		if(employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			List<ContractInfo> list = contractDao.findContract(employeeId);
			return list;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
