package com.hefei.api.cas.module.util;

import java.util.List;

public class SysTree {

	private Long systemId;
	private String systemName;
	private List<ModuleTree> moduleTrees;

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

	public List<ModuleTree> getModuleTrees() {
		return moduleTrees;
	}

	public void setModuleTrees(List<ModuleTree> moduleTrees) {
		this.moduleTrees = moduleTrees;
	}
}
