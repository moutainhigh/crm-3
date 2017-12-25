package com.hefei.api.user.reg.manager;

import com.hefei.api.user.reg.vo.RegisterRequestByEmail;
import com.hefei.api.user.reg.vo.RegisterRequestByEmailMobile;
import com.hefei.api.user.reg.vo.RegisterRequestByMobile;
import com.hefei.common.exception.ClientException;

public interface IRegisterManager {
	
	/**
	 * 新增邮箱
	 * @param userEmail
	 * @return
	 * @throws ClientException
	 */
	public Long regByEmail(RegisterRequestByEmail registerRequestByEmail)throws ClientException;
	
	public Long regByMobile(RegisterRequestByMobile registerRequestByMobile)throws ClientException;
	
	/**
	 * 公司在员工管理添加员工
	 */
	public Long companyAddEmployee(RegisterRequestByEmailMobile registerRequestByEmailMobile)throws ClientException;
}
