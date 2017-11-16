package cn.tedu.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class WebUtils {

	private WebUtils(){}
 
	public static boolean isNull(String str){
		
		return str == null || "".equals(str.trim());
	}
	
	public static String md5(String password){
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(password.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("加密失败");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for(int i=0;i<32-md5code.length();i++){
			md5code = "0" + md5code;
		}
		return md5code;
	}
}
