package com.hefei.common.util.encrypt;

public enum AESVersion {

	V1("1","3fs4b5fe", new byte[]{'C', 'O', 'M', 'M', 'O', 'N', 'U','L','E','A','E','S','U','T','I','L'}),
	V2("2","3fs4b1ee", new byte[]{'X', 'O', 'M', 'M', 'O', 'N', 'U','L','E','A','E','S','U','T','I','L'});
	
	private String key;
	private String version;
	private byte[] iv;

	AESVersion(String version,String key,byte[] iv){
		this.version = version;
		this.key = key;
		this.iv =iv;
	}
	
	public static AESVersion getAESVersion(String version){
		for(AESVersion aesV : AESVersion.values()){
			if(aesV.getVersion().equals(version))
				return aesV;
		}
		return null;
	}
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public byte[] getIv() {
		return iv;
	}

	public void setIv(byte[] iv) {
		this.iv = iv;
	}

}
