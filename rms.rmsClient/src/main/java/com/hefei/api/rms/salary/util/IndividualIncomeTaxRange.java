package com.hefei.api.rms.salary.util;

public class IndividualIncomeTaxRange {

	private double min;// 应纳税所得额(含税) 下线
	private double max;//应纳税所得额(含税) 上线
	private double rate;//税率
	private double deduct;//速算扣除数 
	
	public IndividualIncomeTaxRange(){}
	
	public IndividualIncomeTaxRange(double min, double max, double rate, double deduct){
		this.min=min;
		this.max =max;
		this.rate=rate;
		this.deduct=deduct;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getDeduct() {
		return deduct;
	}

	public void setDeduct(double deduct) {
		this.deduct = deduct;
	}
}
