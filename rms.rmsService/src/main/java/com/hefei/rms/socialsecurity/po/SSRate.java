package com.hefei.rms.socialsecurity.po;

import java.io.Serializable;
import java.util.Date;
/**
 * 各地五险一金比例
 * @author zhouzhiyong
 *
 */
public class SSRate implements Serializable{

	private static final long serialVersionUID = 4409368181592565900L;
	
	private Long id;
	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;
	private Double ylaoComRate;//养老公司缴存比率
	private Double ylaoPersonalRate;//养老个人缴存比率
	
	private Double syeComRate;//失业
	private Double syePersonalRate;
	private Double gshComRate;//工伤
	private Double gshPersonalRate;
	private Double syuComRate;//生育
	private Double syuPersonalRate;
	private Double yliaoComRate;//医疗
	private Double yliaoPersonalRate;
	private Double gjjComRate;//公积金'
	private Double gjjPersonalRate;
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
	public Double getYlaoComRate() {
		return ylaoComRate;
	}
	public void setYlaoComRate(Double ylaoComRate) {
		this.ylaoComRate = ylaoComRate;
	}
	
	public Double getSyeComRate() {
		return syeComRate;
	}
	public void setSyeComRate(Double syeComRate) {
		this.syeComRate = syeComRate;
	}
	public Double getSyePersonalRate() {
		return syePersonalRate;
	}
	public void setSyePersonalRate(Double syePersonalRate) {
		this.syePersonalRate = syePersonalRate;
	}
	public Double getYlaoPersonalRate() {
		return ylaoPersonalRate;
	}
	public void setYlaoPersonalRate(Double ylaoPersonalRate) {
		this.ylaoPersonalRate = ylaoPersonalRate;
	}
	
	public Double getGshComRate() {
		return gshComRate;
	}
	public void setGshComRate(Double gshComRate) {
		this.gshComRate = gshComRate;
	}
	public Double getGshPersonalRate() {
		return gshPersonalRate;
	}
	public void setGshPersonalRate(Double gshPersonalRate) {
		this.gshPersonalRate = gshPersonalRate;
	}
	public Double getSyuComRate() {
		return syuComRate;
	}
	public void setSyuComRate(Double syuComRate) {
		this.syuComRate = syuComRate;
	}
	public Double getSyuPersonalRate() {
		return syuPersonalRate;
	}
	public void setSyuPersonalRate(Double syuPersonalRate) {
		this.syuPersonalRate = syuPersonalRate;
	}
	public Double getYliaoComRate() {
		return yliaoComRate;
	}
	public void setYliaoComRate(Double yliaoComRate) {
		this.yliaoComRate = yliaoComRate;
	}
	public Double getYliaoPersonalRate() {
		return yliaoPersonalRate;
	}
	public void setYliaoPersonalRate(Double yliaoPersonalRate) {
		this.yliaoPersonalRate = yliaoPersonalRate;
	}
	public Double getGjjComRate() {
		return gjjComRate;
	}
	public void setGjjComRate(Double gjjComRate) {
		this.gjjComRate = gjjComRate;
	}
	public Double getGjjPersonalRate() {
		return gjjPersonalRate;
	}
	public void setGjjPersonalRate(Double gjjPersonalRate) {
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
