package com.hefei.api.rms.salary.dto;

import java.util.Date;

public class SalaryPayTransactionDTO extends SalaryTransactionDTO{

	private static final long serialVersionUID = -6515324544851118510L;
	
	private String ssCard;//社保卡号
	private Double ssBaseSalary;//社保缴存基数
	private Double ylaoInsurance;//'养老总计',
	private Double ylaoComRate ;// '养老公司缴存比率',
	private Double ylaoComCash ;// '养老公司缴存金额',
	private Double ylaoPersonalRate ;// '养老个人缴存比率',
	private Double ylaoPersonalCash ;// '养老个人缴存金额',
	
	private Double syeInsurance;//'失业总计',
	private Double syeComRate ;// '失业公司缴存比率',
	private Double syeComCash ;// '失业公司缴存金额',
	private Double syePersonalRate ;// '失业个人缴存比率',
	private Double syePersonalCash ;// '失业个人缴存金额',
	
	private Double gshInsurance;//'工伤总计',
	private Double gshComRate ;// '工伤公司缴存比率',
	private Double gshComCash ;// '工伤公司缴存金额',
	private Double gshPersonalRate ;// '工伤个人缴存比率',
	private Double gshPersonalCash ;// '工伤个人缴存金额',
	
	private Double syuInsurance;//'生育总计',
	private Double syuComRate ;// '生育公司缴存比率',
	private Double syuComCash ;// '生育公司缴存金额',
	private Double syuPersonalRate ;// '生育个人缴存比率',
	private Double syuPersonalCash ;// '生育个人缴存金额',
	
	private Double yliaoInsurance;//'医疗总计',
	private Double yliaoComRate ;// '医疗公司缴存比率',
	private Double yliaoComCash ;// '医疗公司缴存金额',
	private Double yliaoPersonalRate ;// '医疗个人缴存比率',
	private Double yliaoPersonalCash ;// '医疗个人缴存金额',
	
	private Double gjjInsurance;//'公积金总计',
	private Double gjjComRate ;// '公积金公司缴存比率',
	private Double gjjComCash ;// '公积金公司缴存金额',
	private Double gjjPersonalRate ;// '公积金个人缴存比率',
	private Double gjjPersonalCash ;// '公积金个人缴存金额',
	private Double gjjAdd ;// '公积金补充金额',
	
	private String ssStatus;
	private Date ssPayedTime;//公积金发放时间
	
	public Double getSsBaseSalary() {
		return ssBaseSalary;
	}
	public void setSsBaseSalary(Double ssBaseSalary) {
		this.ssBaseSalary = ssBaseSalary;
	}
	public String getSsCard() {
		return ssCard;
	}
	public void setSsCard(String ssCard) {
		this.ssCard = ssCard;
	}
	public Double getYlaoInsurance() {
		return ylaoInsurance;
	}
	public void setYlaoInsurance(Double ylaoInsurance) {
		this.ylaoInsurance = ylaoInsurance;
	}
	public Double getYlaoComRate() {
		return ylaoComRate;
	}
	public void setYlaoComRate(Double ylaoComRate) {
		this.ylaoComRate = ylaoComRate;
	}
	public Double getYlaoComCash() {
		return ylaoComCash;
	}
	public void setYlaoComCash(Double ylaoComCash) {
		this.ylaoComCash = ylaoComCash;
	}
	public Double getYlaoPersonalRate() {
		return ylaoPersonalRate;
	}
	public void setYlaoPersonalRate(Double ylaoPersonalRate) {
		this.ylaoPersonalRate = ylaoPersonalRate;
	}
	public Double getYlaoPersonalCash() {
		return ylaoPersonalCash;
	}
	public void setYlaoPersonalCash(Double ylaoPersonalCash) {
		this.ylaoPersonalCash = ylaoPersonalCash;
	}
	public Double getSyeInsurance() {
		return syeInsurance;
	}
	public void setSyeInsurance(Double syeInsurance) {
		this.syeInsurance = syeInsurance;
	}
	public Double getSyeComRate() {
		return syeComRate;
	}
	public void setSyeComRate(Double syeComRate) {
		this.syeComRate = syeComRate;
	}
	public Double getSyeComCash() {
		return syeComCash;
	}
	public void setSyeComCash(Double syeComCash) {
		this.syeComCash = syeComCash;
	}
	public Double getSyePersonalRate() {
		return syePersonalRate;
	}
	public void setSyePersonalRate(Double syePersonalRate) {
		this.syePersonalRate = syePersonalRate;
	}
	public Double getSyePersonalCash() {
		return syePersonalCash;
	}
	public void setSyePersonalCash(Double syePersonalCash) {
		this.syePersonalCash = syePersonalCash;
	}
	public Double getGshInsurance() {
		return gshInsurance;
	}
	public void setGshInsurance(Double gshInsurance) {
		this.gshInsurance = gshInsurance;
	}
	public Double getGshComRate() {
		return gshComRate;
	}
	public void setGshComRate(Double gshComRate) {
		this.gshComRate = gshComRate;
	}
	public Double getGshComCash() {
		return gshComCash;
	}
	public void setGshComCash(Double gshComCash) {
		this.gshComCash = gshComCash;
	}
	public Double getGshPersonalRate() {
		return gshPersonalRate;
	}
	public void setGshPersonalRate(Double gshPersonalRate) {
		this.gshPersonalRate = gshPersonalRate;
	}
	public Double getGshPersonalCash() {
		return gshPersonalCash;
	}
	public void setGshPersonalCash(Double gshPersonalCash) {
		this.gshPersonalCash = gshPersonalCash;
	}
	public Double getSyuInsurance() {
		return syuInsurance;
	}
	public void setSyuInsurance(Double syuInsurance) {
		this.syuInsurance = syuInsurance;
	}
	public Double getSyuComRate() {
		return syuComRate;
	}
	public void setSyuComRate(Double syuComRate) {
		this.syuComRate = syuComRate;
	}
	public Double getSyuComCash() {
		return syuComCash;
	}
	public void setSyuComCash(Double syuComCash) {
		this.syuComCash = syuComCash;
	}
	public Double getSyuPersonalRate() {
		return syuPersonalRate;
	}
	public void setSyuPersonalRate(Double syuPersonalRate) {
		this.syuPersonalRate = syuPersonalRate;
	}
	public Double getSyuPersonalCash() {
		return syuPersonalCash;
	}
	public void setSyuPersonalCash(Double syuPersonalCash) {
		this.syuPersonalCash = syuPersonalCash;
	}
	public Double getYliaoInsurance() {
		return yliaoInsurance;
	}
	public void setYliaoInsurance(Double yliaoInsurance) {
		this.yliaoInsurance = yliaoInsurance;
	}
	public Double getYliaoComRate() {
		return yliaoComRate;
	}
	public void setYliaoComRate(Double yliaoComRate) {
		this.yliaoComRate = yliaoComRate;
	}
	public Double getYliaoComCash() {
		return yliaoComCash;
	}
	public void setYliaoComCash(Double yliaoComCash) {
		this.yliaoComCash = yliaoComCash;
	}
	public Double getYliaoPersonalRate() {
		return yliaoPersonalRate;
	}
	public void setYliaoPersonalRate(Double yliaoPersonalRate) {
		this.yliaoPersonalRate = yliaoPersonalRate;
	}
	public Double getYliaoPersonalCash() {
		return yliaoPersonalCash;
	}
	public void setYliaoPersonalCash(Double yliaoPersonalCash) {
		this.yliaoPersonalCash = yliaoPersonalCash;
	}
	public Double getGjjInsurance() {
		return gjjInsurance;
	}
	public void setGjjInsurance(Double gjjInsurance) {
		this.gjjInsurance = gjjInsurance;
	}
	public Double getGjjComRate() {
		return gjjComRate;
	}
	public void setGjjComRate(Double gjjComRate) {
		this.gjjComRate = gjjComRate;
	}
	public Double getGjjComCash() {
		return gjjComCash;
	}
	public void setGjjComCash(Double gjjComCash) {
		this.gjjComCash = gjjComCash;
	}
	public Double getGjjPersonalRate() {
		return gjjPersonalRate;
	}
	public void setGjjPersonalRate(Double gjjPersonalRate) {
		this.gjjPersonalRate = gjjPersonalRate;
	}
	public Double getGjjPersonalCash() {
		return gjjPersonalCash;
	}
	public void setGjjPersonalCash(Double gjjPersonalCash) {
		this.gjjPersonalCash = gjjPersonalCash;
	}
	public Double getGjjAdd() {
		return gjjAdd;
	}
	public void setGjjAdd(Double gjjAdd) {
		this.gjjAdd = gjjAdd;
	}
	public String getSsStatus() {
		return ssStatus;
	}
	public void setSsStatus(String ssStatus) {
		this.ssStatus = ssStatus;
	}
	public Date getSsPayedTime() {
		return ssPayedTime;
	}
	public void setSsPayedTime(Date ssPayedTime) {
		this.ssPayedTime = ssPayedTime;
	}
	
	
}
