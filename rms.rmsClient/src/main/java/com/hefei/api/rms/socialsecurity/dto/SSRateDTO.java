package com.hefei.api.rms.socialsecurity.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * 各地五险一金比例
 * @author zhouzhiyong
 *
 */
public class SSRateDTO implements Serializable{

	private static final long serialVersionUID = 4409368181592565900L;
	
	public static final String DEL_FLAG_TRUE="1";
	public static final String DEL_FLAG_FLASE="0";
	
	private Long id;
	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;
	private String ylaoComRate;//养老公司缴存比率
	private String ylaoPersonalRate;//养老个人缴存比率
	
	private String syeComRate;//失业
	private String syePersonalRate;
	private String gshComRate;//工伤
	private String gshPersonalRate;
	private String syuComRate;//生育
	private String syuPersonalRate;
	private String yliaoComRate;//医疗
	private String yliaoPersonalRate;
	private String gjjComRate;//公积金'
	private String gjjPersonalRate;
	private String delFlag;
	private Date createTime;
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getYlaoComRate() {
		return ylaoComRate;
	}
	public void setYlaoComRate(String ylaoComRate) {
		this.ylaoComRate = ylaoComRate;
	}
	public String getYlaoPersonalRate() {
		return ylaoPersonalRate;
	}
	public void setYlaoPersonalRate(String ylaoPersonalRate) {
		this.ylaoPersonalRate = ylaoPersonalRate;
	}
	public String getSyeComRate() {
		return syeComRate;
	}
	public void setSyeComRate(String syeComRate) {
		this.syeComRate = syeComRate;
	}
	public String getSyePersonalRate() {
		return syePersonalRate;
	}
	public void setSyePersonalRate(String syePersonalRate) {
		this.syePersonalRate = syePersonalRate;
	}
	public String getGshComRate() {
		return gshComRate;
	}
	public void setGshComRate(String gshComRate) {
		this.gshComRate = gshComRate;
	}
	public String getGshPersonalRate() {
		return gshPersonalRate;
	}
	public void setGshPersonalRate(String gshPersonalRate) {
		this.gshPersonalRate = gshPersonalRate;
	}
	public String getSyuComRate() {
		return syuComRate;
	}
	public void setSyuComRate(String syuComRate) {
		this.syuComRate = syuComRate;
	}
	public String getSyuPersonalRate() {
		return syuPersonalRate;
	}
	public void setSyuPersonalRate(String syuPersonalRate) {
		this.syuPersonalRate = syuPersonalRate;
	}
	public String getYliaoComRate() {
		return yliaoComRate;
	}
	public void setYliaoComRate(String yliaoComRate) {
		this.yliaoComRate = yliaoComRate;
	}
	public String getYliaoPersonalRate() {
		return yliaoPersonalRate;
	}
	public void setYliaoPersonalRate(String yliaoPersonalRate) {
		this.yliaoPersonalRate = yliaoPersonalRate;
	}
	public String getGjjComRate() {
		return gjjComRate;
	}
	public void setGjjComRate(String gjjComRate) {
		this.gjjComRate = gjjComRate;
	}
	public String getGjjPersonalRate() {
		return gjjPersonalRate;
	}
	public void setGjjPersonalRate(String gjjPersonalRate) {
		this.gjjPersonalRate = gjjPersonalRate;
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
