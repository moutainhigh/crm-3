package com.hefei.rms.cas.vo;

import com.hefei.api.cas.module.vo.ModuleInfo;

public class ModuleTreeNode {
	//{ id:1, pId:0, name:"一级分类", open:true},
		public static final String NODE_TYPE_SYSTEM = "9";
		public static final String NODE_TYPE_MENU = ModuleInfo.TYPE_MENU;
		public static final String NODE_TYPE_BUTTON = ModuleInfo.TYPE_BUTTON;
		
		private Long systemId;
		private String id; // systemId + "_" + moduleId
		private String pId; // systemId + "_" + parentId
		private String name;
		private boolean open = false;
		private String nodeType;
		private String icon;
		private boolean checked = false;
		private boolean nocheck= true;
		
		public Long getSystemId() {
			return systemId;
		}
		public void setSystemId(Long systemId) {
			this.systemId = systemId;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getpId() {
			return pId;
		}
		public void setpId(String pId) {
			this.pId = pId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isOpen() {
			return open;
		}
		public void setOpen(boolean open) {
			this.open = open;
		}
		public String getNodeType() {
			return nodeType;
		}
		public void setNodeType(String nodeType) {
			this.nodeType = nodeType;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		public boolean isNocheck() {
			return nocheck;
		}
		public void setNocheck(boolean nocheck) {
			this.nocheck = nocheck;
		}
}
