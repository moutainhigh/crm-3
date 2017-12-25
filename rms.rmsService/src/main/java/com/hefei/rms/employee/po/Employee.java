package com.hefei.rms.employee.po;

import java.util.Date;

/**
 * 雇佣关系
 * @author Administrator
 *
 */
public class Employee {
	
	private Long id;//
	private Long creator;
	private Long updater;
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
