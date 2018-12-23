package application.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String md2Encode = md5Jdk("111111");
		System.out.println("MDJDK加密后为： " + md2Encode);
		System.out.println("MDJDK加密后长度：" + md2Encode.length());
	}
	
	public static String converByteToHexString(byte[] bytes) {
		String result = "";
		for(int i = 0; i < bytes.length; i++) {
			int temp = bytes[i] & 0xff;
			String tempHex = Integer.toHexString(temp);
			if(tempHex.length() < 2) {
				result += "0" + tempHex;
			}else
				result += tempHex;
		}
		return result;
	}
	public static String md5Jdk(String message) {
		String temp = "";
		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			byte[] encodeMd5Digest = md5Digest.digest(message.getBytes());
			temp = converByteToHexString(encodeMd5Digest);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
}
