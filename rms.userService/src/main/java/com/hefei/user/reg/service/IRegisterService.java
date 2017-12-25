package com.hefei.user.reg.service;

import com.hefei.api.user.reg.vo.RegisterRequestByEmail;
import com.hefei.api.user.reg.vo.RegisterRequestByEmailMobile;
import com.hefei.api.user.reg.vo.RegisterRequestByMobile;
import com.hefei.service.framework.exception.ServiceException;

public interface IRegisterService {

	public Long regByEmail(RegisterRequestByEmail registerRequestByEmail)throws ServiceException;
	public Long regByMobile(RegisterRequestByMobile registerRequestByMobile)throws ServiceException;
	public Long companyAddEmployee(RegisterRequestByEmailMobile registerRequestByEmailMobile)throws ServiceException;
}
