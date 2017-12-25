package com.hefei.rms.physical.po;

import java.io.Serializable;
import java.util.Date;

public class PhysicalInfo implements Serializable{

	private static final long serialVersionUID = 1385643703118739069L;
	
	private Long id;
	private Long companyId;
	private Long employeeId;
	private String sex;
	private Double height;
	private Double weight;
	private Double visionLeft;
	private Double visionRight;
	private String colourBlind;
	private Integer pulse;
	private Double bloodPre;
	private String heartLung;
	private String liverKidney;
	private String doppler;
	private String ecg;
	private String dieaseHistory;
	private Long creator;
	private Date createTime;
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Double getHight() {
		return height;
	}
	public void setHight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getVisionLeft() {
		return visionLeft;
	}
	public void setVisionLeft(Double visionLeft) {
		this.visionLeft = visionLeft;
	}
	public Double getVisionRight() {
		return visionRight;
	}
	public void setVisionRight(Double visionRight) {
		this.visionRight = visionRight;
	}
	public String getColourBlind() {
		return colourBlind;
	}
	public void setColourBlind(String colourBlind) {
		this.colourBlind = colourBlind;
	}
	public Integer getPulse() {
		return pulse;
	}
	public void setPulse(Integer pulse) {
		this.pulse = pulse;
	}
	public Double getBloodPre() {
		return bloodPre;
	}
	public void setBloodPre(Double bloodPre) {
		this.bloodPre = bloodPre;
	}
	public String getHeartLung() {
		return heartLung;
	}
	public void setHeartLung(String heartLung) {
		this.heartLung = heartLung;
	}
	public String getLiverKidney() {
		return liverKidney;
	}
	public void setLiverKidney(String liverKidney) {
		this.liverKidney = liverKidney;
	}
	public String getDoppler() {
		return doppler;
	}
	public void setDoppler(String doppler) {
		this.doppler = doppler;
	}
	public String getEcg() {
		return ecg;
	}
	public void setEcg(String ecg) {
		this.ecg = ecg;
	}
	public String getDieaseHistory() {
		return dieaseHistory;
	}
	public void setDieaseHistory(String dieaseHistory) {
		this.dieaseHistory = dieaseHistory;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
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
	@Override
	public String toString() {
		return "PhysicalInfo [id=" + id + ", companyId=" + companyId
				+ ", employeeId=" + employeeId + ", sex=" + sex + ", hight="
				+ height + ", weight=" + weight + ", visionLeft=" + visionLeft
				+ ", visionRight=" + visionRight + ", colourBlind="
				+ colourBlind + ", pulse=" + pulse + ", bloodPre=" + bloodPre
				+ ", heartLung=" + heartLung + ", liverKidney=" + liverKidney
				+ ", doppler=" + doppler + ", ecg=" + ecg + ", dieaseHistory="
				+ dieaseHistory + ", creator=" + creator + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
