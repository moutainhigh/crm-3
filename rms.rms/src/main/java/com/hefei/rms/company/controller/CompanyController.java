package com.hefei.rms.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.rms.company.vo.CompanyIndustryDTO;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;

/**
 * 公司
 * @author zn
 *
 */
@Controller
@RequestMapping(value="/company",produces="text/plain;charset=UTF-8")
public class CompanyController {
	
	private Logger logger = Logger.getLogger(CompanyController.class);
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("pageIndex", 1);
		request.setAttribute("pageSize", Pagination.DEFAULT_PAGE_SIZE);
		return "company/index";
	}
	@RequestMapping("search")
	public String searchCompany(HttpServletRequest request,HttpServletResponse response, String companyName, Integer pageIndex, Integer pageSize) {
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("companyName", companyName);
			Pagination<CompanyInfo> pagination = companyService.findManagePagination(CommonParameterThreadLocal.getUserId(), companyName, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "company/index";
	}
	
	@RequestMapping("toCreate")
	public String toCreate(HttpServletRequest request, HttpServletResponse response){
		return "company/add";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createCompany(HttpServletRequest request) {
		try{
			String industryIds = request.getParameter("industryIds");
			String companyName = request.getParameter("companyName");
			String companyAddress = request.getParameter("companyAddress");
			String companyTel = request.getParameter("companyTel");
			String companyInfo = request.getParameter("companyInfo");
			String legalPerson = request.getParameter("legalPerson");
			//String businessLicensePic = request.getParameter("businessLicensePic");
			String accountStandard = request.getParameter("accountStandard");
			String companySize = request.getParameter("companySize");
			String companyType = request.getParameter("companyType");
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			String countyCode = request.getParameter("countyCode");
			String usccCode = request.getParameter("usccCode");
			String orgCode = request.getParameter("orgCode");
			String taxCode = request.getParameter("taxCode");
			String licenseCode = request.getParameter("licenseCode");
			String taxType = request.getParameter("taxType");
			
			CompanyInfo info = new CompanyInfo();
			info.setAccountStandard(accountStandard);
			if(companyName.length()>0){
				info.setCompanyName(companyName);
			}else{
				request.setAttribute("msg", "公司名称不能为空");
				throw new Exception("公司名称不能为空");
			}
			if(companyAddress.length()>0){
				info.setCompanyAddress(companyAddress);
			}
			if(companyTel.length()>0){
				info.setCompanyTel(companyTel);
			}
			if(companyInfo.length()>0){
				info.setCompanyInfo(companyInfo);
			}
			if(legalPerson.length()>0){
				info.setLegalPerson(legalPerson);
			}
//			if(businessLicensePic.length()>0){
//				info.setBusinessLicensePic(businessLicensePic);
//			}
			info.setCompanySize(companySize);
			info.setCompanyType(companyType);
			info.setProvinceCode(provinceCode);
			info.setCityCode(cityCode);
			info.setCountyCode(countyCode);
			info.setUsccCode(usccCode);
			info.setOrgCode(orgCode);
			info.setLicenseCode(licenseCode);
			info.setTaxCode(taxCode);
			info.setTaxType(taxType);

			Long userId = CommonParameterThreadLocal.getUserId();
			
			companyService.createCompany(userId, industryIds, info);
		}catch (Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "company/add";
		}
		return "company/addResult";
	}
	
	
	@RequestMapping("toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response, Long companyId){
		try {
			CompanyInfo company = companyService.getCompany(CommonParameterThreadLocal.getUserId(), companyId);
			request.setAttribute("company", company);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			request.setAttribute("msg", "请选择当前操作公司");
			return "common/error";
		}
		try {
			List<CompanyIndustryDTO> industryList = companyService.getCompanyIndustry(companyId);
			String industryIds = "";
			if(industryList != null && industryList.size() > 0){
				for(CompanyIndustryDTO dto:industryList){
					industryIds = industryIds + dto.getIndustryId() + ",";
				}
				industryIds = industryIds.substring(0, industryIds.length()-1);
			}
			request.setAttribute("industryIds", industryIds);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "company/edit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request) {
		try{
			String industryIds = request.getParameter("industryIds");
			String companyName = request.getParameter("companyName");
			String companyAddress = request.getParameter("companyAddress");
			String companyTel = request.getParameter("companyTel");
			String companyInfo = request.getParameter("companyInfo");
			String legalPerson = request.getParameter("legalPerson");
			//String businessLicensePic = request.getParameter("businessLicensePic");
			String accountStandard = request.getParameter("accountStandard");
			String companySize = request.getParameter("companySize");
			String companyType = request.getParameter("companyType");
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			String countyCode = request.getParameter("countyCode");
			String usccCode = request.getParameter("usccCode");
			String orgCode = request.getParameter("orgCode");
			String taxCode = request.getParameter("taxCode");
			String licenseCode = request.getParameter("licenseCode");
			String taxType = request.getParameter("taxType");
			Long companyId = Long.valueOf(request.getParameter("companyId"));
			
			CompanyInfo info = new CompanyInfo();
			info.setId(companyId);
			info.setAccountStandard(accountStandard);
			if(companyName.length()>0){
				info.setCompanyName(companyName);
			}else{
				request.setAttribute("msg", "公司名称不能为空");
				throw new Exception("公司名称不能为空");
			}
			if(companyAddress.length()>0){
				info.setCompanyAddress(companyAddress);
			}
			if(companyTel.length()>0){
				info.setCompanyTel(companyTel);
			}
			if(companyInfo.length()>0){
				info.setCompanyInfo(companyInfo);
			}
			if(legalPerson.length()>0){
				info.setLegalPerson(legalPerson);
			}
//			if(businessLicensePic.length()>0){
//				info.setBusinessLicensePic(businessLicensePic);
//			}
			info.setCompanySize(companySize);
			info.setCompanyType(companyType);
			info.setProvinceCode(provinceCode);
			info.setCityCode(cityCode);
			info.setCountyCode(countyCode);
			info.setUsccCode(usccCode);
			info.setOrgCode(orgCode);
			info.setLicenseCode(licenseCode);
			info.setTaxCode(taxCode);
			info.setTaxType(taxType);

			Long userId = CommonParameterThreadLocal.getUserId();
			
			companyService.editCompany(userId, industryIds, info);
		}catch (Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "company/edit";
		}
		return "company/editResult";
	}
}