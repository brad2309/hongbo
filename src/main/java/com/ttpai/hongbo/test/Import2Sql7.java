package com.ttpai.hongbo.test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ttpai.util.DateFormatUtil;
import com.ttpai.util.JdbcUtil;
import com.ttpai.util.MD5;

public class Import2Sql7 {

	public static void main(String[] args) throws Exception{
		List<String> lines = FileUtils.readLines(new File("E:\\ttpai\\201711\\Noname7.txt"));
		for(int i=0;i<lines.size();i++){
			if(i==0)continue;
			String s = lines.get(i);
			String[] arr = s.split("\t");
			String userId=arr[0].trim(),phone=arr[4].trim();
			System.out.println("UPDATE ttpai_sso.SSO_USERS SET MOBILE_PHONE='"+phone+"',PSWD='1000:cbfb31f10e89ab70f34463ee4c58d2ccbbcc2013ea89ac92:d8fab40570b6fb63378c1190de5cf5ec232488f49f0e4dd8' WHERE ID="+userId+";");
		}
		for(int i=0;i<lines.size();i++){
			if(i==0)continue;
			String s = lines.get(i);
			String[] arr = s.split("\t");
			String userId=arr[0].trim(),phone=arr[4].trim();
			System.out.println("UPDATE ttpai_pai.TTP_BUSINESS_USERS SET MOBILE_PHONE='"+phone+"' WHERE ID="+userId+";");
		}
		String sql = "insert into ttpai_sso.SSO_BUSINESS_USERS_BANK_INFO (BUSINESS_ID, BANK_CARD_NO, BANK_ACCOUNT_PERSON_NAME, BANK_ACCOUNT_BANK_NAME, " +
				"BANK_ACCOUNT_BANK_BRANCH, BANK_ACCOUNT_PROVINCE, BANK_ACCOUNT_CITY, BANK_DEFAULT, CREATE_TIME) " +
				"values ";
//		System.out.println(sql);
		String sql2 = "insert into ttpai_pai.TTP_BUSINESS_USERS_BANK_INFO (BUSINESS_ID, BANK_CARD_NO, BANK_ACCOUNT_PERSON_NAME, BANK_ACCOUNT_BANK_NAME, " +
				"BANK_ACCOUNT_BANK_BRANCH, BANK_ACCOUNT_PROVINCE, BANK_ACCOUNT_CITY, BANK_DEFAULT, CREATE_TIME) " +
				"values ";
		System.out.println(sql2);
		for(int i=0;i<lines.size();i++){
			if(i==0)continue;
			String s = lines.get(i);
			String[] arr = s.split("\t");
			String userId=arr[0].trim(),regTimeStr=arr[2].trim(),bankUser=arr[5].trim(),cardNo=arr[7].trim().replaceAll(" ", ""),bankBranch=arr[6].trim(),bank=arr[6].trim().substring(0,6);
			String zoneName = arr[3].trim();
			String zoneId = JdbcUtil.INSTANCE.queryOne("select ID from ttpai_boss_v1.ZONE a where a.NAME='"+zoneName+"'");
			String py = JdbcUtil.INSTANCE.queryOne("select PROVINCE_ID from ttpai_boss_v1.ZONE a where a.NAME='"+zoneName+"'");
			if(StringUtils.isBlank(zoneId)||StringUtils.isBlank(py)){
				System.out.println(zoneName);
				throw new RuntimeException();
			}
			Date regTime = DateFormatUtil.parse(regTimeStr,"yyyy/MM/dd");
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
