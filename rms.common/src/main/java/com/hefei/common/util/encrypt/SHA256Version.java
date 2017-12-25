package com.hefei.common.util.encrypt;

public enum SHA256Version {

	V1("1","$5$NeNcyY4h"),
	V2("2", "$5$Q9xEUxDa"),
	V3("3", "$5$y1S7RpPn");
	
	private String version;
	private String key;

	SHA256Version(String version, String key){
		this.version = version;
		this.key =key;
	}
	
	public static SHA256Version getSHA256Version(String version){
		for(SHA256Version sha256V : SHA256Version.values()){
			if(sha256V.getVersion().equals(version))
				return sha256V;
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

}
