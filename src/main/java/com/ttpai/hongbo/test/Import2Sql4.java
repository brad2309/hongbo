package com.ttpai.hongbo.test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ttpai.util.DateFormatUtil;
import com.ttpai.util.JdbcUtil;

public class Import2Sql4 {

	
	public static void main(String[] args) throws Exception{
		List<String> lines = FileUtils.readLines(new File("E:\\ttpai\\201711\\Noname5.txt"));
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
			String userId = arr[1];
			String zoneName = arr[4];
			String zoneId = JdbcUtil.INSTANCE.queryOne("select ID from ttpai_boss_v1.ZONE a where a.NAME='"+zoneName+"'");
			String py = JdbcUtil.INSTANCE.queryOne("select PROVINCE_ID from ttpai_boss_v1.ZONE a where a.NAME='"+zoneName+"'");
			if(StringUtils.isBlank(zoneId)||StringUtils.isBlank(py)){
				System.out.println(zoneName);
				throw new RuntimeException();
			}
			Date regTime = DateFormatUtil.parse(arr[3],"yyyy/MM/dd HH:mm:ss");
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
			if(arr.length>9&&StringUtils.isNoneBlank(arr[8])){
				v = "("+userId+", '"+arr[8].trim()+"', '"+arr[7].trim()+"', '"+arr[9].trim()+"', '"+arr[10].trim()+"', '"+py+"', '"+zoneId+"', 1, '"+reg+"'),";
			}else{
//				v = "("+userId+", null, '"+arr[7].trim()+"', null, null, '"+py+"', '"+zoneId+"', 1, '"+reg+"'),";
				continue;
			}
			if(arr[9].trim().equals("天津招商银行")){
				System.out.println(s);
				return;
			}
			if(i==lines.size()-1){
				v = v.substring(0,v.length()-1)+";";
			}
//			System.out.println(v);
		}
	}
	
	
}
