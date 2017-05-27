package com.ttpai.hongbo.test;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DESUtil {

    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

    public static byte[] encryptDES(String encryptString, String encryptKey) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
        return encryptedData;
    }

    public static String encryptDESwithBase64(String encryptString, String encryptKey) throws Exception {
        return new String(Base64.encodeBase64(encryptDES(encryptString, encryptKey)), "UTF-8");
    }

    //JAVA中DES解密实现方法如下：
    public static String decryptDES(byte[] encryptedData, String decryptKey) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("UTF-8"), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(encryptedData);
        String decryptedString = new String(decryptedData, "UTF-8");
        return decryptedString;
    }

    public static String decryptDESwithBase64(String encryptedString, String decryptKey) throws Exception {
        byte[] encryptedData = Base64.decodeBase64(encryptedString.replaceAll(" ", "+").getBytes());
        return decryptDES(encryptedData, decryptKey);
    }


    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     *
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static String parseByte2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String plainText = "abcdefghihjjjkelaemn";
        String keyText = "20120401";
        plainText = "miki西游| mikixiyou@126.com";
        keyText = "12345678";


        byte[] encryptedData = encryptDES(plainText, keyText);
        String decryptedString = decryptDES(encryptedData, keyText);
        String cipherText = parseByte2HexStr(encryptedData);
        System.out.println("明文：" + plainText);
        System.out.println("密钥：" + keyText);
        System.out.println("密文 Base64 编码：" + cipherText);
        System.out.println("解密后：" + decryptedString);


        String encryptedString = encryptDESwithBase64(plainText, keyText);
        decryptedString = decryptDESwithBase64(encryptedString, keyText);
        System.out.println("明文：" + plainText);
        System.out.println("密钥：" + keyText);
        System.out.println("密文：" + encryptedString);
        System.out.println("解密后：" + decryptedString);
    }

}
