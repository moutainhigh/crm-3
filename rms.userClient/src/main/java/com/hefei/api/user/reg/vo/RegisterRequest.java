package com.hefei.api.user.reg.vo;

import java.util.Date;

public class RegisterRequest {

	private String type;//UserInfo.type
	private String plainPassword;
	private Long companyId;
	private String companyName;
	private String registerType;
	private String registerSubType;
	private String registerPosition;
	private String ip;
	private String browser;
	private String mac;
	
	
	/** 姓名 */
	private String name;
	/** 省份证号码*/
	private String idNo;
	private String sex;
	private Date birthday;
	private String hukouType;
	private String hukouProvinceCode;
	private String hukouCityCode;
	private String hukouAreaCode;
	private String liveProvinceCode;
	private String liveCityCode;
	private String liveAreaCode;
	private String liveAddress;
	
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRegisterType() {
		return registerType;
	}
	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}
	public String getRegisterSubType() {
		return registerSubType;
	}
	public void setRegisterSubType(String registerSubType) {
		this.registerSubType = registerSubType;
	}
	public String getRegisterPosition() {
		return registerPosition;
	}
	public void setRegisterPosition(String registerPosition) {
		this.registerPosition = registerPosition;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHukouType() {
		return hukouType;
	}
	public void setHukouType(String hukouType) {
		this.hukouType = hukouType;
	}
	public String getHukouProvinceCode() {
		return hukouProvinceCode;
	}
	public void setHukouProvinceCode(String hukouProvinceCode) {
		this.hukouProvinceCode = hukouProvinceCode;
	}
	public String getHukouCityCode() {
		return hukouCityCode;
	}
	public void setHukouCityCode(String hukouCityCode) {
		this.hukouCityCode = hukouCityCode;
	}
	public String getHukouAreaCode() {
		return hukouAreaCode;
	}
	public void setHukouAreaCode(String hukouAreaCode) {
		this.hukouAreaCode = hukouAreaCode;
	}
	public String getLiveProvinceCode() {
		return liveProvinceCode;
	}
	public void setLiveProvinceCode(String liveProvinceCode) {
		this.liveProvinceCode = liveProvinceCode;
	}
	public String getLiveCityCode() {
		return liveCityCode;
	}
	public void setLiveCityCode(String liveCityCode) {
		this.liveCityCode = liveCityCode;
	}
	public String getLiveAreaCode() {
		return liveAreaCode;
	}
	public void setLiveAreaCode(String liveAreaCode) {
		this.liveAreaCode = liveAreaCode;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
}
