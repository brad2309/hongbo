package com.ttpai.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5 {
	private static final Logger logger =  LoggerFactory.getLogger(MD5.class);
    /**
     * @param s       待加密字符串
     * @param charset 编码格式
     * @return
     * @Description: TODO
     * @author: simon.JY
     * @date: 2015年6月30日 下午5:36:08
     */
    public final static String encoder(String s, String charset) {
        try {
            if (null == s) {
                return null;
            }
            if (null == charset) {
                charset = "UTF-8";
            }
            byte[] btInput = s.getBytes(charset);
            return encoder(btInput);
        } catch (Exception e) {
        	logger.error("转换异常",e);
            return null;
        }
    }

    public final static String encoder(String s) {
        return encoder(s, "UTF-8");
    }

    /**
     * @param bytes 需要编码的字节
     * @author: kail
     * @date: 2015年9月1日 下午16:36
     */
    public final static String encoder(byte[] bytes) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(bytes);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
        	logger.error("转换异常",e);
            return null;
        }
    }
}
