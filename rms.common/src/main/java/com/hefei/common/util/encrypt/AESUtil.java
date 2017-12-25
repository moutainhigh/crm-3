package com.hefei.common.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import com.hefei.common.util.StringUtils;
import com.hefei.commons.codec.binary.Base64;
/**
 * AES 加解密
 * @title  AESUtil
 * @author chensen
 * 2015年9月6日 上午9:48:00
 */
public class AESUtil {
	
	static final String KEY_ALGORITHM = "AES";
												  //算法/模式/补码方 式
    static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
	private AESUtil() {
		
	}

	public static void main(String[] args) throws Exception {
//		byte[] iv = { 'C', 'O', 'M', 'M', 'O', 'N', 'U','L','E','A','E','S','U','T','I','L'};  //向量
//		//   自定义密匙
//		String str=encrypt("邮乐AESUtil数据加解密","自定义密匙", iv);
//		System.out.println(str);
//		System.out.println(decrypt("Utid+leHM6TBITlGFLA0tMtyIahh8/tHkvcfcY2DaeRA1eQQ4/4w2ut3KQ6sOFBK","3fs4b5fe", iv));
		//url加密
		AESVersion aesV = AESVersion.getAESVersion("1");
		String url1=encrypt("admin",aesV.getKey(), aesV.getIv());
		System.out.println(url1);
//		System.out.println(decrypt(url1,"自定义密匙", iv));
	}
      
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     * @throws UnsupportedEncodingException 
     */  
	private static String base64Encode(byte[] bytes) throws UnsupportedEncodingException{  
        return new String(Base64.encodeBase64(bytes, false),"utf-8");  
    }  
      
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */  
	private static byte[] base64Decode(String base64Code) throws Exception{  
		return StringUtils.isEmpty(base64Code) ? null :Base64.decodeBase64(base64Code) ;  
    }  
      
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
   /* private static String encrypt(String content) throws Exception {  
        return encrypt(content,secretKey) ;
    }  */
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
    private static byte[] encrypt(byte[] content, String encryptKey, final byte[] iv) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);  
        SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encryptKey.getBytes());
        kgen.init(128, secureRandom); 
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);  
        cipher.init(Cipher.ENCRYPT_MODE, kgen.generateKey(), new IvParameterSpec(iv));  
        byte[] bt = cipher.doFinal(content);
        return bt;  
    }  
      
    /** 
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的base 64 code 
     * @throws Exception 
     */  
    public static String encrypt(String content, String encryptKey, final byte[] iv) throws Exception {  
        return base64Encode(encrypt(content.getBytes("utf-8"), encryptKey, iv));  
    }  
    
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */  
    private static String decrypt(byte[] encryptBytes, String decryptKey, final byte[] iv) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);  
        SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(decryptKey.getBytes());
        kgen.init(128, secureRandom); 
          
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);  
        cipher.init(Cipher.DECRYPT_MODE, kgen.generateKey(), new IvParameterSpec(iv));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
          
        return new String(decryptBytes);  
    }  
      
    /** 
     * 将base 64 code AES解密 
     * @param encryptStr 待解密的base 64 code 
     * @param decryptKey 解密密钥 
     * @return 解密后的string 
     * @throws Exception 
     */  
    public static String decrypt(String encryptStr, String decryptKey, final byte[] iv) throws Exception {  
        return StringUtils.isEmpty(encryptStr) ? null : decrypt(base64Decode(encryptStr), decryptKey, iv);  
    }  
}
