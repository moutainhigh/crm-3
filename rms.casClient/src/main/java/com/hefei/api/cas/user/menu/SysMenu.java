package com.hefei.api.cas.user.menu;

import java.util.List;

import com.hefei.api.cas.module.vo.ModuleInfo;

public class SysMenu {

	private Long systemId;
	private String systemName;
	
	private List<ModuleInfo> modules;

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<ModuleInfo> getModules() {
		return modules;
	}

	public void setModules(List<ModuleInfo> modules) {
		this.modules = modules;
	}
}
