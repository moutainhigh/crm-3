package com.hefei.api.rms.company.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CompanyInfo implements Serializable{

	private static final long serialVersionUID = 7608513365165787856L;

	public static final String DELFLAG_NO = "1";
	public static final String DELFLAG_YES = "0";
	/**
	 * 增值税类型 ：1:生产型增值税   2:收入型增值税  3:消费型增值税
	 */
	public static final String TAX_TYPE_PRODUCTION="1";
	public static final String TAX_TYPE_INCOME="2";
	public static final String TAX_TYPE_CONSUMPTION ="3";
	public static final Map<String, String> taxTypeDict = new HashMap<String, String>();
	static{
		taxTypeDict.put(TAX_TYPE_PRODUCTION, "生产型增值税");
		taxTypeDict.put(TAX_TYPE_INCOME, "收入型增值税 ");
		taxTypeDict.put(TAX_TYPE_CONSUMPTION, "消费型增值税");
	}
	
	/**
	 * 企业会计准则： 1：小企业会计准则 2：一般企业会计准则
	 */
	public static final String ACCOUNT_STANDARD_SMALL="1";
	public static final String ACCOUNT_STANDARD_NORMAL="2";
	public static final Map<String, String> accountStandardDict = new HashMap<String, String>();
	static{
		accountStandardDict.put(ACCOUNT_STANDARD_SMALL, "小企业会计准则");
		accountStandardDict.put(ACCOUNT_STANDARD_NORMAL, "一般企业会计准则");
	}
	/**
	 * 企业规模
	 * 1.少于20人;2.20～50人；3.50～100人；4.100～500人;5.500人以上
	 */
	public static final String COMPANY_SIZE_1_20="1";
	public static final String COMPANY_SIZE_20_50="2";
	public static final String COMPANY_SIZE_50_100="3";
	public static final String COMPANY_SIZE_100_500="4";
	public static final String COMPANY_SIZE_500_X="5";
	public static final Map<String, String> companySizeDict = new HashMap<String, String>();
	static{
		companySizeDict.put(COMPANY_SIZE_1_20, "少于20人");
		companySizeDict.put(COMPANY_SIZE_20_50, "20～50人");
		companySizeDict.put(COMPANY_SIZE_50_100, "50～100人");
		companySizeDict.put(COMPANY_SIZE_100_500, "100～500人");
		companySizeDict.put(COMPANY_SIZE_500_X, "500人以上");
	}
	/**
	 * 企业类型
	 * 1.民营企业;2.国企 3.外资独资 4外资合资 
	 */
	public static final String COMPANY_TYPE_PRIVATELY="1";
	public static final String COMPANY_TYPE_STATE_OWNED="2";
	public static final String COMPANY_TYPE_FOREIGN_WHOLLY="3";
	public static final String COMPANY_TYPE_FOREIGN_JOIN="4";
	
	public static final Map<String, String> companyTypeDict = new HashMap<String, String>();
	static{
		companyTypeDict.put(COMPANY_TYPE_PRIVATELY, "民营企业");
		companyTypeDict.put(COMPANY_TYPE_STATE_OWNED, "国企");
		companyTypeDict.put(COMPANY_TYPE_FOREIGN_WHOLLY, "外资独资");
		companyTypeDict.put(COMPANY_TYPE_FOREIGN_JOIN, "外资合资");
	}
	
	private Long id;//公司id
	private String companyName;//公司名称
	private String companyAddress;//公司地址
	private String companyTel;//公司电话
	private String companyInfo;//公司简介
	private String legalPerson;//公司法人
	private String businessLicensePic;//营业执照
	private String usccCode;
	private String orgCode;
	private String taxCode;
	private String licenseCode;
	private String taxType;
	private String accountStandard;
	private String companySize;//公司规模
	private String companyType;//公司类型
	private String provinceCode;//省
	private String cityCode;//市
	private String countyCode;//区
	private String delFlag;//删除标识
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getBusinessLicensePic() {
		return businessLicensePic;
	}
	public void setBusinessLicensePic(String businessLicensePic) {
		this.businessLicensePic = businessLicensePic;
	}
	public String getUsccCode() {
		return usccCode;
	}
	public void setUsccCode(String usccCode) {
		this.usccCode = usccCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getAccountStandard() {
		return accountStandard;
	}
	public void setAccountStandard(String accountStandard) {
		this.accountStandard = accountStandard;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
