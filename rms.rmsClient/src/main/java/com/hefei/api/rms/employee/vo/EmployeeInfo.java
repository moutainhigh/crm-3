package com.hefei.api.rms.employee.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeeInfo implements Serializable{

	private static final long serialVersionUID = -4920964509349601074L;

	public static final String HUKOU_TYPE_CITY="1";
	public static final String HUKOU_TYPE_RURAL="2";
	public static final String HUKOU_TYPE_FOREIGN="3";
	
	public static final String DEL_FLAG_TRUE="1";
	public static final String DEL_FLAG_FALSE="0";
	
	public static final String SEX_MAILE="1";
	public static final String SEX_FEMAILE="2";
	public static final String SEX_UNKNOWN="3";
	public static final Map<String, String> sexDict = new HashMap<String, String>();
	static{
		sexDict.put(SEX_MAILE, "男");
		sexDict.put(SEX_FEMAILE, "女 ");
		sexDict.put(SEX_UNKNOWN, "未知");
	}
	
	/** 编号 */
	private Long userId;
	private Long employeeId;
	/** 公司编号 */
	private Long companyId;
	
	/** 姓名 */
	private String name;
	/** 省份证号码*/
	private String idNo;
	/** 性别：1男 2女 */
	private String sex;
	private Long superior;//上级 T_EMPLOYEE.ID
	private String superiorEmployeeName;
	
	private Date birthday;
	private String hukouType;
	private String hukouProvinceCode;
	private String hukouCityCode;
	private String hukouAreaCode;
	private String liveProvinceCode;
	private String liveCityCode;
	private String liveAreaCode;
	private String liveAddress;
	private String status;
	
	private String cardNo;
	private String email;
	private String mobile;
	private Date onboardDate;
	private Date leaveDate;
	/** 创建人 */
	private Long creator;
	/** 更新人 */
	private Long updater;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getSuperior() {
		return superior;
	}
	public void setSuperior(Long superior) {
		this.superior = superior;
	}
	public String getSuperiorEmployeeName() {
		return superiorEmployeeName;
	}
	public void setSuperiorEmployeeName(String superiorEmployeeName) {
		this.superiorEmployeeName = superiorEmployeeName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getOnboardDate() {
		return onboardDate;
	}
	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
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
