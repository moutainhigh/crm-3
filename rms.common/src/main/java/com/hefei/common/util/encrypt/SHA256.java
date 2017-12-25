package com.hefei.common.util.encrypt;

import com.hefei.commons.codec.digest.Sha2Crypt;

public class SHA256 {

	public static String encrtypt(String plain, String version){
		return Sha2Crypt.sha256Crypt(plain.toString().getBytes(), SHA256Version.getSHA256Version(version).getKey());
	}
	
	public static String encrtyptSalt(String plain, String salt){
		return Sha2Crypt.sha256Crypt(plain.toString().getBytes(), salt);
	}
	
	public static void main(String[] a){
		System.out.println(encrtypt("admin", "1"));
	}
}
