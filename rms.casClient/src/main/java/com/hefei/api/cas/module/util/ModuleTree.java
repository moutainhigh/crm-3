package com.hefei.api.cas.module.util;

import java.util.ArrayList;
import java.util.List;

import com.hefei.api.cas.module.vo.ModuleInfo;

public class ModuleTree{

	private ModuleInfo module;
	
	private ModuleInfo parent;
	
	private List<ModuleInfo> children;

	public ModuleTree(ModuleInfo module){
		this.module = module;
	}
	
	public void addChild(ModuleInfo module){
		if(children == null)
			children = new ArrayList<ModuleInfo>();
		children.add(module);
	}
	public ModuleInfo getModule() {
		return module;
	}

	public void setModule(ModuleInfo module) {
		this.module = module;
	}

	public ModuleInfo getParent() {
		return parent;
	}

	public void setParent(ModuleInfo parent) {
		this.parent = parent;
	}

	public List<ModuleInfo> getChildren() {
		return children;
	}

	public void setChildren(List<ModuleInfo> children) {
		this.children = children;
	}
	
}
