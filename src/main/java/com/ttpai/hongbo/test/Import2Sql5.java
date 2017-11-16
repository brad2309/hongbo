package com.ttpai.hongbo.test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ttpai.util.DateFormatUtil;
import com.ttpai.util.JdbcUtil;

public class Import2Sql5 {

	
	public static void main(String[] args) throws Exception{
		List<String> lines = FileUtils.readLines(new File("E:\\ttpai\\201711\\Noname6.txt"));
		String sql = "insert into ttpai_sso.SSO_BUSINESS_USERS_BANK_INFO (BUSINESS_ID, BANK_CARD_NO, BANK_ACCOUNT_PERSON_NAME, BANK_ACCOUNT_BANK_NAME, " +
				"BANK_ACCOUNT_BANK_BRANCH, BANK_ACCOUNT_PROVINCE, BANK_ACCOUNT_CITY, BANK_DEFAULT, CREATE_TIME) " +
				"values ";
		System.out.println(sql);
		String sql2 = "insert into ttpai_pai.TTP_BUSINESS_USERS_BANK_INFO (BUSINESS_ID, BANK_CARD_NO, BANK_ACCOUNT_PERSON_NAME, BANK_ACCOUNT_BANK_NAME, " +
				"BANK_ACCOUNT_BANK_BRANCH, BANK_ACCOUNT_PROVINCE, BANK_ACCOUNT_CITY, BANK_DEFAULT, CREATE_TIME) " +
				"values ";
//		System.out.println(sql2);
		for(int i=0;i<lines.size();i++){
			if(i==0)continue;
			String s = lines.get(i);
			String[] arr = s.split("\t");
			String userId = arr[1],userName=arr[2].trim(),regTimeStr=arr[3].trim(),zoneName=arr[4].trim(),
					bankUser="胡晓冬",cardNo="6236231010215378",bank="厦门国际银行",bankBranch="上海虹桥支行";
			String zoneId = "2",py="SH";
			Date regTime = DateFormatUtil.parse(regTimeStr,"yyyy/MM/dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			c.setTime(regTime);
			c.add(Calendar.MONTH, 1);
			regTime = c.getTime();
			Date now = new Date();
			if(regTime.after(now)){
				throw new RuntimeException();
			}
			String reg = DateFormatUtil.format(regTime);
			String v;
			v = "("+userId+", '"+cardNo+"', '"+bankUser+"', '"+bank+"', '"+bankBranch+"', '"+py+"', '"+zoneId+"', 1, '"+reg+"'),";
			if(i==lines.size()-1){
				v = v.substring(0,v.length()-1)+";";
			}
			System.out.println(v);
			
		}
	}
	
}
