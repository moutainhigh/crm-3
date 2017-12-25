package com.hefei.manager.frame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.base.CompanyCookieUtil;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.company.CompanyCookie;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class ChangeCompanyController {

		private Logger logger = Logger.getLogger(ChangeCompanyController.class);
		
		private ICompanyManager companyManager = new CompanyManager();
		/**
		 * 用户管理后台地址映射
		 * @param path
		 * @return
		 */
		@RequestMapping(value = "/changeCompany")
		@ResponseBody
		public String changeCompany(String companyId, HttpServletRequest request,HttpServletResponse response) {
			BaseResponse baseResponse = new BaseResponse();
			try {
				if(companyId != null){
					Long cid = Long.valueOf(companyId);
					CompanyInfo company = companyManager.getCompany(CommonParameterThreadLocal.getUserId(), cid);
					CompanyCookie companyCookie = new CompanyCookie();
					companyCookie.setCompanyId(company.getId());
					companyCookie.setCompanyName(company.getCompanyName());
					
					CompanyCookieUtil.setCurrentCompanyCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_CURRENT_COMPANY, companyCookie, CookieConstants.COOKIE_AGE_SESSION, true, false);
					CommonParameterThreadLocal.get().setCompanyCookie(companyCookie);
					
					baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				}else{
					baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				}
			} catch (Exception e) {
				logger.error(RequestThreadLocal.getTimer() + " error", e);
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			}
			
			return baseResponse.toString();
		}
	
}
