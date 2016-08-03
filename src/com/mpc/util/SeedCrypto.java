package com.mpc.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 암복호화 클래스
 * 
 * @author hongmin.an
 *
 */
public class SeedCrypto {
	
	private final static String encryptKey = "poiuytacdefghijklmlopqr";
	
	/**
	 * 암호화
	 * @param str
	 * @return
	 */
	public static String encrypt(String str){
		return encrypt(str,encryptKey);
	}
	
	/**
	 * 복호화
	 * @param str
	 * @return
	 */
	public static String decrypt(String str){
		return decrypt(str,encryptKey);
	}
	
	/**
	 * 암호화
	 * @param str
	 * @param key
	 * @return
	 */
	public static String encrypt(String str,String key){
		String result = "";
//		if (key.length() != 24) {
//			return "";
//	    }
		if(str != null && !str.equals("")){
			try {
				String strTemp = "";
				BASE64Encoder encoder = new BASE64Encoder();
				SeedAlg seedAlg = new SeedAlg(key.getBytes());
				strTemp = new String(encoder.encode(seedAlg.encrypt(str.getBytes())));
				for(int i = 0; i < strTemp.length(); i++) {
					if(strTemp.charAt(i) != '\n' && strTemp.charAt(i) != '\r') {
						result = result + strTemp.charAt(i);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   return result;
	}
	
	/**
	 * 복호화
	 * @param str
	 * @param key
	 * @return
	 */
	public static String decrypt(String str,String key){
		String result = "";
//		if (key.length() != 24) {
//			return "";
//		}

		try {
			String strTemp = "";
			BASE64Decoder decoder = new BASE64Decoder();
			SeedAlg seedAlg = new SeedAlg(key.getBytes());
			strTemp = new String(seedAlg.decrypt(decoder.decodeBuffer(str)));
			for (int i = 0; i < strTemp.length() && strTemp.charAt(i) != 0;) {
				if (strTemp.charAt(i) != '\n' && strTemp.charAt(i) != '\r') {
					result = result + strTemp.charAt(i);
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
