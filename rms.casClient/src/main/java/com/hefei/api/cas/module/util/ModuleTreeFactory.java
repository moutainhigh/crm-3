package com.hefei.api.cas.module.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.user.menu.SysMenu;

public class ModuleTreeFactory {

	public static List<SysTree> generateTree(List<SysMenu> sysMenus){
		if(sysMenus == null || sysMenus.size() == 0)
			return null;
		
		List<SysTree> sysTrees = new ArrayList<SysTree>();
		SysTree sysTree = null;
		SysMenu sysMenu = null;
		for(int i=0; i< sysMenus.size(); i++){
			sysMenu = sysMenus.get(i);
			sysTree = new SysTree();
			sysTree.setSystemId(sysMenu.getSystemId());
			sysTree.setSystemName(sysMenu.getSystemName());
			sysTree.setModuleTrees(generate(sysMenu.getModules()));
			
			sysTrees.add(sysTree);
		}
		return sysTrees;
	}
	public static List<ModuleTree> generate(List<ModuleInfo> moduleInfos){
		if(moduleInfos == null || moduleInfos.size() == 0)
			return null;
		
		Map<Long, ModuleInfo> all = new HashMap<Long, ModuleInfo>();
		Map<Long, List<ModuleInfo>> allChildren = new HashMap<Long, List<ModuleInfo>>();
		for (ModuleInfo module:moduleInfos ) {
			all.put(module.getId(), module);
			if(module.getParentId() != null){
				List<ModuleInfo> children = allChildren.get(module.getParentId());
				if(children == null){
					children =new ArrayList<ModuleInfo>();
					allChildren.put(module.getParentId(), children);
				}
				children.add(module);
			}
		}
		
		List<ModuleTree> trees= new ArrayList<ModuleTree>();
		ModuleTree tree = null;
		for (ModuleInfo module:moduleInfos ) {
			tree = new ModuleTree(module);
			if(module.getParentId() !=null){
				tree.setParent(all.get(module.getParentId()));
			}else{
				trees.add(tree);
			}
			tree.setChildren(allChildren.get(module.getId()));
		}
		return trees;
	}
}
