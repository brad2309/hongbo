package com.ttpai.httptest;

import org.apache.commons.codec.binary.Base64;

import com.ttpai.hongbo.test.DESUtil;

public class Test1 {
	
    public static final String UTF8 = "UTF-8";

	public static void main(String[] args) throws Exception{
		String s = "FT9Yt0alUIDZZthfG6Ptx8Ov9gU1A2I/smo4ykTzNvadsRg94Kz rD4d4sJHaV9xowRdTO1mJCsT1XXI4q4D53FfYpYqjKrF5aMUiHTAlCjrE7e7ISvEH5e4NcfVg2aqbkW7AyvkcwRfUIvS 9ltlRv2GKIMJa7A tqevtTAk5WH8je4wKIdaP7lPQdJXNS9oCMxh4ol9vK p6j2AgsN29fmzYYps00141hMG4qnXMSeXwekTlF9zo/BM6d4Za/OAc3T3psqKYPYJkGvIT4/SYPgXMRDj4T0rl2KRy/av6kr/KKYo5Xk3Ft2 KQDFU8xDPa1pu12uUCLHFO3ymH5JQ5CmTnI76kEy5VRIfVnhfIwdVFuMjuAXaWw/UAUZZWqmvYAGx9RX40AKz/yjxVrwfZ7eqfD7NY0rai1UXlN1Hmb2VXfwYL6UvgVNbMQmQYCMEDAZqXoED8NCakaLCkpYr6fExekDBqUojNKvxDwI8HzjvTlMDFATjbRTyFAR0JrtXqa ZCFhOuVzxdX6znh TeV4uYC9CyuQbClkCBycoCQyYZ9t1CS7y2rtxkgDpXLL7R0yCmix71xwQxVsdF6Mq7lCDe Q/ucir8/ixSzE oIsMYmcAa25jup3C5ayAjfWxfa4yMms86HbltjDMjDax5BDkd5EoS3m4oLTcTdlZ0=";
//		String b = new String(Base64.decodeBase64(s.getBytes("utf-8")), "iso8859-1");
		String b = DESUtil.decryptDESwithBase64(s, "cxtsc@51");
		System.out.println(b);
	}
	
}
