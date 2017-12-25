package com.hefei.common.util.encrypt;

import java.security.MessageDigest;

/**
 * MD5 加密工具类
 * @author zhanglei
 * @date 2016年8月10日 上午11:18:10
 */
public class MD5 {
	
	/**
	 * @param enstr 需加密字符串
	 * @param version 密钥版本号
	 * @return
	 * @throws Exception
	 */
	/*public final static String MD5Encrypt(final String enstr,final char[] hexDigits) throws Exception {
		try {
			byte[] strTemp = enstr.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			throw e;
		}
	}
	*/
	public final static String MD5Encrypt(final String enstr,String version) throws Exception {
		
		try {
			MD5Version md5V = MD5Version.getMD5Version(version);
			
			byte[] strTemp = enstr.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = md5V.getIv()[byte0 >>> 4 & 0xf];
				str[k++] =  md5V.getIv()[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			throw e;
		}
	}
	
	

}
