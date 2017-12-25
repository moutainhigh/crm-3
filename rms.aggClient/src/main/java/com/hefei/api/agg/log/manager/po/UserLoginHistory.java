package com.hefei.api.agg.log.manager.po;

import java.io.Serializable;
import java.util.Date;

/****
 * 系统用户登录/登出历史记录
 * @author zhanglei
 * @date 2016年10月18日 下午4:54:38
 */
public class UserLoginHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7736362088794741515L;

	private String id;
	
	private String userId;
	
	private Integer type;
	
	private String subType;
	
	private String ip;
	
	private String mac;
	
	private Integer loginStyle;
	
	private Integer loginType;
	
	private Date createTime;
	
	private Date updateTime;
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("UserEmail:[id:"+id+"userId:"+userId+",type:"+type+",subType:"+subType+
				",ip:"+ip+",mac:"+mac+",loginStyle:"+loginStyle+
				",loginType:"+loginType+",createTime:"+createTime+",updateTime:"+updateTime);
		return str.toString();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getLoginStyle() {
		return loginStyle;
	}

	public void setLoginStyle(Integer loginStyle) {
		this.loginStyle = loginStyle;
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

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
