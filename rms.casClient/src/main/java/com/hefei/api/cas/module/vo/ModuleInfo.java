package com.hefei.api.cas.module.vo;

import java.util.Date;

/**
 *
 */
public class ModuleInfo implements java.io.Serializable{

	private static final long serialVersionUID = 8217510251449508689L;
	
	public static final String TYPE_MENU = "0";//菜单
	public static final String TYPE_BUTTON = "1";//按钮
	
	public static final String DEL_FLAG_NO = "1";//有效
	public static final String DEL_FLAG_YES = "0";//无效
	
	private Long id;
	private Long systemId;
	private String name;
	private String url;
	private String type;
	private String rank;
	private Long parentId;
	private String remark;
	private String iconPath;
	private Integer orders;
	private Date createTime;
	
	private Date updateTime;

	private String delFlag;


	@Override
	public String toString() {
		return "ModuleInfo [id=" + id + ", systemId=" + systemId + ", name="
				+ name + ", url=" + url + ", type=" + type + ", rank=" + rank
				+ ", parentId=" + parentId + ", remark=" + remark
				+ ", iconPath=" + iconPath + ", orders=" + orders
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", delFlag=" + delFlag + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getIconPath() {
		return iconPath;
	}


	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}


	public Integer getOrders() {
		return orders;
	}


	public void setOrders(Integer orders) {
		this.orders = orders;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
