package com.hefei.service.framework.base.request;


public class BaseRequest implements java.io.Serializable {

	private static final long serialVersionUID = -1321427428131519044L;

	private BaseRequestHead headObj;

	private String plain;

	public BaseRequest(BaseRequestHead head, String plain) {
		this.headObj = head;
		this.plain = plain;
	}

	public BaseRequestHead getHeadObj() {
		return headObj;
	}

	public void setHeadObj(BaseRequestHead headObj) {
		this.headObj = headObj;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}
}
