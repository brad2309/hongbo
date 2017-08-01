package com.ttpai.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESUtil {

    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

	// JAVA中DES解密实现方法如下：
	public static String decryptDES(byte[] encryptedData, String decryptKey) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("UTF-8"), "DES");
		String clazz = "DES/CBC/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(clazz);
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(encryptedData);
		String decryptedString = new String(decryptedData, "UTF-8");
		return decryptedString;
	}

	public static String decryptDESwithBase64(String encryptedString, String decryptKey) throws Exception {
		byte[] encryptedData = Base64.decodeBase64(encryptedString.replaceAll(
				" ", "+").getBytes());
		return decryptDES(encryptedData, decryptKey);
	}

}
