package com.ttpai.hongbo.test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ttpai.util.DateFormatUtil;
import com.ttpai.util.JdbcUtil;

public class Import2Sql3 {

	
	public static void main(String[] args) throws Exception{
		List<String> lines = FileUtils.readLines(new File("E:\\ttpai\\201711\\Noname1.txt"));
		String sql = "insert into ttpai_pai.TTP_BUSINESS_MARGIN_LOG  " +
				"(AUCTION_ID, LICENSE_NUMBER_NEW, USER_ID, BALANCE, MONEY, MONEY_DESC, MONEY_TYPE, TYPE, OPERATOR_ID, " +
				"OPERATOR_NAME, CREATE_TIME, REMARK, COMPANY_NAME, ZONE_ID, ZONE_NAME, LEADER_NAME, ADMIN_NAME, ADMIN_ID, " +
				"LAST_UPDATE_TIME, ADMIN_PROVINCE_ID, ADMIN_ZONE_NAME, ADMIN_ZONE_ID, MARGIN, OPERATOR_ADMIN_ID, OPER_STATUS) " +
				"values ";
		System.out.println(sql);
		List<String> lines2 = FileUtils.readLines(new File("E:\\ttpai\\201711\\Noname2.txt"));
		List<String> lines4 = FileUtils.readLines(new File("E:\\ttpai\\201711\\Noname4.txt"));
		for(int i=0;i<lines.size();i++){
			if(i==0)continue;
			String s = lines.get(i);
			String[] arr = s.split("\t");
			String userId = arr[1];
			String zoneName = arr[4];
			String cityId = JdbcUtil.INSTANCE.queryOne("SELECT ID FROM BOSS_CITY WHERE CITY_NAME='"+zoneName+"' LIMIT 1");
			if(StringUtils.isBlank(cityId)){
				System.out.println(zoneName+","+arr[0]);
				throw new RuntimeException();
			}
			String oprTime = getOprTimeUpdate(lines2,userId,arr[3],lines4);
			int margin = getMargin(lines2, userId);
			String line = "(null, null, "+userId+", 5000, 5000, '充值', '0', '0', 143, '杨晓虎', '"+oprTime+"', '优惠活动，不予提现', " +
					"'"+arr[2]+"', "+cityId+", '"+arr[4]+"', '"+arr[2]+"', null, null, now(), 'SH', '上海', " +
					"2, "+margin+", '', '0'),";
			System.out.println(line);
		}
	}
	
	private static int getMargin(List<String> lines,String userId){
		for(String s:lines){
			String[] arr = s.split("\t");
			String id = arr[0].trim();
			if(id.equals(userId)){
				return Integer.parseInt(arr[1]);
			}
		}
		return 0;
	}
	
	private static String getOprTimeUpdate(List<String> lines,String userId,String regDate,List<String> lines4) throws Exception{
		String dateStr = getOprTime(lines, userId, regDate,lines4);
		Date d = DateFormatUtil.parse(dateStr, "yyyy/M/d HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int r1 = new Random().nextInt(4);
		if(r1==0)r1++;
		c.add(Calendar.DATE, r1);
		c.set(Calendar.HOUR_OF_DAY, 9);
		int r2 = new Random().nextInt(9*60*60);
		c.add(Calendar.SECOND, r2);
		String oprTime = DateFormatUtil.format(c.getTime());
		if(c.getTime().after(new Date())){
			throw new RuntimeException();
		}
		return oprTime;
	}
	
	private static String getOprTime(List<String> lines,String userId,String regDate,List<String> lines4){
		for(String s:lines){
			String[] arr = s.split("\t");
			String id = arr[0].trim();
			if(id.equals(userId)){
				return arr[2];
			}
		}
		for(String s:lines4){
			String[] arr = s.split("\t");
			String id = arr[0].trim();
			if(id.equals(userId)){
				return arr[1];
			}
		}
		throw new RuntimeException();
	}
	
}
