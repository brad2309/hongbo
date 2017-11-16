package com.ttpai.hongbo.test;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ttpai.util.JdbcUtil;

public class Import2Sql2 {

	public static void main(String[] args) throws Exception{
		List<String> lines = FileUtils.readLines(new File("E:\\ttpai\\201710\\Noname1.txt"));
		int i = 1;
		System.out.println("INSERT INTO BOSS_INVITATION_BUICK_ZONE VALUES");
		for(String s:lines){
			String[] arr = s.split("_");
			String cityId = JdbcUtil.INSTANCE.queryOne("SELECT ID FROM BOSS_CITY WHERE CITY_NAME='"+arr[2]+"' LIMIT 1");
			String[] zones = arr[3].split("，");
			for(String z:zones){
				if(StringUtils.isBlank(z)){
					continue;
				}
				z = z.trim();
				if(z.equals("栗水"))z="溧水";
				String zoneId = JdbcUtil.INSTANCE.queryOne("SELECT ID FROM ZONE WHERE NAME like '"+z+"%' LIMIT 1");
				if(StringUtils.isBlank(zoneId)){
					if(z.endsWith("市")){
						z = z.substring(0,z.length()-1);
					}
					zoneId = JdbcUtil.INSTANCE.queryOne("SELECT ID FROM ZONE WHERE NAME like '"+z+"%' LIMIT 1");
					if(StringUtils.isBlank(zoneId)){		
//						System.out.println(z);
						continue;
					}
				}
				z = JdbcUtil.INSTANCE.queryOne("SELECT NAME FROM ZONE WHERE ID="+zoneId);
				System.out.println("("+(i+1000)+","+cityId+",'"+arr[2]+"',"+zoneId+",'"+z+"','"+arr[0]+"','"+arr[1]+"'),");
				i++;
			}
			
			
		}
	}
	
}
