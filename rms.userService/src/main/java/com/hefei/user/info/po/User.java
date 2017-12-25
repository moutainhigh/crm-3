package com.hefei.user.info.po;

import java.util.Date;


/**
 * 用户信息
 */
public class User implements java.io.Serializable{
	
	private static final long serialVersionUID = 2027775270232679636L;
	
	private Long id;
	private String type;//1:超级管理员(注册用户) 9：普通用户
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
	private Date createTime;
	private Date updateTime;
	private String delFlag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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
