package com.rmsClient.test;



import java.util.Date;

import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyInfo;

public class CompanyManagerTest {

	private static void createCompany(){
		CompanyInfo company = new CompanyInfo();
		//IdManager im = new IdManagerImpl();
		ICompanyManager CompanyM = new CompanyManager();
		try {
			//Long id = im.getNextId();
			company.setId(12124313l);
			company.setCompanyName("1");
			company.setCompanyTel("13275816321");
			company.setCompanyInfo("1");
			company.setLegalPerson("1");
			company.setBusinessLicensePic("123456");
			company.setUsccCode("22");
			company.setOrgCode("2121");
			company.setTaxCode("212");
			company.setLicenseCode("433");
			company.setTaxType("544");
			company.setAccountStandard("343");
			company.setCompanySize("3");
			company.setCompanyType("1");
			company.setCreateTime(new Date());
			company.setUpdateTime(new Date());
			company.setDelFlag("1");
//			CompanyM.createCompany(company);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			public static void main(String[] args) {
			//	createCompany();
			}
}