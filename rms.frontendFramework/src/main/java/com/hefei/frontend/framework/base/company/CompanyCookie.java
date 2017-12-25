package com.hefei.frontend.framework.base.company;

public class CompanyCookie {

	private Long companyId;
	private String companyName;
	
	
	@Override
	public String toString() {
		return "CompanyCookie [companyId=" + companyId + ", companyName="
				+ companyName + "]";
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
	
}
