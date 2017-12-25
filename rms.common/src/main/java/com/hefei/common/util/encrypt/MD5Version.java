package com.hefei.common.util.encrypt;

public enum MD5Version {

	V1("1", new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' }),
	V2("2", new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'e', 'f', 'g', 'h', 'i', 'j' }),
	V3("3", new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'k', 'l', 'm', 'n', 'e', 'f' });
	
	private String version;
	private char[] iv;

	MD5Version(String version, char[] iv){
		this.version = version;
		this.iv =iv;
	}
	
	public static MD5Version getMD5Version(String version){
		for(MD5Version md5V : MD5Version.values()){
			if(md5V.getVersion().equals(version))
				return md5V;
		}
		return null;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public char[] getIv() {
		return iv;
	}

	public void setIv(char[] iv) {
		this.iv = iv;
	}
}
