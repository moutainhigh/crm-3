package com.hefei.manager.frame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.cas.module.util.SysTree;
import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.frontend.framework.base.company.CompanyCookie;
import com.hefei.frontend.framework.base.user.UserCookie;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.manager.frame.service.IFrameService;

@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class FrameController {
	
	private Logger logger = Logger.getLogger(FrameController.class);
	private ICompanyManager companyManager = new CompanyManager();
	@Autowired
	private IFrameService frameService;
	
	/**
	 * 用户管理后台地址映射
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response) {
		try{
			UserCookie user = CommonParameterThreadLocal.getUserCookie();
			CompanyCookie company = CommonParameterThreadLocal.getCompanyCookie();
			request.setAttribute("user", user);
			setMenu(request, response, user.getUserId(), company.getCompanyId());
			setAllCompany(request, response, user.getUserId(), company.getCompanyId());
		}catch(Exception e){
			
		}
		
		return "frame/main";
	}
	
	private void setMenu(HttpServletRequest request,HttpServletResponse response,Long userId,Long companyId){
		try {
			List<SysTree> menu = frameService.getMenus(userId, companyId);
			request.setAttribute("menuTree", menu);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
	}
	private void setAllCompany(HttpServletRequest request,HttpServletResponse response,Long userId,Long companyId){
		try {
			List<CompanyInfo> companys = companyManager.getCompanyByUserId(userId);
			for(CompanyInfo companyInfo : companys){
				if(companyId.compareTo(companyInfo.getId()) == 0){
					request.setAttribute("currentCompany", companyInfo);
					companys.remove(companyInfo);
					break;
				}
			}
			request.setAttribute("allCompanys", companys);
			
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
	}
	
}