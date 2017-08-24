package com.ttpai.hongbo.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.ttpai.hongbo.test.ComplaintConstant.ComplaintCustomerType;
import com.ttpai.hongbo.test.ComplaintConstant.ComplaintDutyParty;
import com.ttpai.hongbo.test.ComplaintConstant.ComplaintHasDuty;
import com.ttpai.hongbo.test.ComplaintConstant.ComplaintLevel;
import com.ttpai.hongbo.test.ComplaintConstant.ComplaintType;

public class Import2Sql {

	public static void main(String[] args) throws Exception{
		Import2Sql imp = new Import2Sql();
		imp.test1("E:\\ttpai\\201708\\daoru\\Noname1.txt");
		imp.test1("E:\\ttpai\\201708\\daoru\\Noname2.txt");
		imp.test1("E:\\ttpai\\201708\\daoru\\Noname3.txt");
		imp.test1("E:\\ttpai\\201708\\daoru\\Noname4.txt");
		imp.test1("E:\\ttpai\\201708\\daoru\\Noname5.txt");
		imp.test1("E:\\ttpai\\201708\\daoru\\Noname6.txt");
	}
	
	void test1(String filename) throws Exception{
		List<String> lines = FileUtils.readLines(new File(filename));
		List<BossComplaintVO> list = new ArrayList<BossComplaintVO>();
		for(int i=0;i<lines.size();i++){
			if(i<3){
				continue;
			}
			String line = lines.get(i);
			String[] arr = line.split("\t");
			BossComplaintVO comp = new BossComplaintVO();
			try{
				setToBean(arr,comp);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("-------------"+line);
				throw new RuntimeException();
			}
			list.add(comp);
		}
//		System.out.println(list.size());
		buildSql(list);
	}
	void buildSql(List<BossComplaintVO> list){
		for(BossComplaintVO v:list){
			String sql1 = "insert into BOSS_COMPLAINT (CUSTOMER_TYPE,COMP_LEVEL,COMPLAINT_TYPE,COMP_STATUS," +
					"CONTENT,CREATE_TIME,PHONE,LICENSE,AUCTION_ID,HANDLER_USER_ID,HANDLER_USER_NAME,UPDATE_TIME) values (" +v.getCustomerType()+
					","+v.getCompLevel()+"," +v.getComplaintType()+","+v.getCompStatus()+",'"+v.getContent()+"','"+v.getCreateTimeText()+"','"+v.getPhone()+"','"+v.getLicense()+"',"+
					v.getAuctionId()+","+v.getHandlerUserId()+",'"+v.getHandlerUserName()+"',now()"
					+");";
			System.out.println(sql1);
			BossComplaintResultVO r = v.getResult();
			String sql2 = "insert into BOSS_COMPLAINT_RESULT (COMPLAINT_ID,HAS_DUTY,DUTY_PARTY,REASON,DEPART_RESULT,COMPLAINT_RESULT,RESULT_STATUS) VALUES(" +
					"(select max(ID) from BOSS_COMPLAINT)," +r.getHasDuty()+","+r.getDutyParty()+",'"+r.getReason()+"','"+r.getDepartResult()+"','"+r.getComplaintResult()+"',"+r.getResultStatus()+
					");";
			sql2 = sql2.replaceAll("'null'", "null");
			System.out.println(sql2);
		}
	}
	void setToBean(String[] arr,BossComplaintVO comp) throws Exception{
		BossComplaintResultVO res = new BossComplaintResultVO();
		comp.setResult(res);
		String s0 = arr[0];
		comp.setCreateTime(DateUtils.parseDate("2017年"+s0, "yyyy年MM月dd日"));
		if(StringUtils.isNotBlank(arr[1]))
			comp.setAuctionId(Integer.valueOf(arr[1]));
		comp.setLicense(arr[2].trim());
		String s5 = arr[5];
		comp.setCustomerType(ComplaintCustomerType.getIdByName(s5));
		comp.setPhone(arr[6]);
		comp.setComplaintType(ComplaintType.getIdByName(arr[7]));
		comp.setContent(arr[8]);
		comp.setCompLevel(ComplaintLevel.getIdByName(arr[9]));
		String s10 = arr[10];
		if("张总".equals(s10))
			s10 = "张岚";
		comp.setHandlerUserName(s10);
		comp.setHandlerUserId(getUserIdByName(s10));
		
		comp.setCompStatus(25);
		res.setResultStatus(0);
		if(arr.length<=12){
			return;
		}
		res.setHasDuty(ComplaintHasDuty.getIdByName(arr[12]));
		if(arr.length==13){
			return;
		}
		res.setDutyParty(ComplaintDutyParty.getIdByName(arr[13]));
		if(arr.length==14){
			return;
		}
		res.setReason(arr[14]);
		if(arr.length==15){
			return;
		}
		res.setDepartResult(arr[15]);
		res.setComplaintResult(arr[16]);
		if(arr.length==17){
			return;
		}
		res.setResultStatus("是".equals(arr[17])?1:0);
		if(res.getResultStatus()==1){
			comp.setCompStatus(30);
		}
	}
	
//	刘琼	153	rel_qiong.liu
//	张岚	277	rel_helen.zhang
//	李祥惠	114	rel_xianghui.li
//	汪诚	2311	rel_cheng.wang
//	翟蓉	2068	rel_rong.zhai
//	邱伟	125	rel_wei.qiu
	Integer getUserIdByName(String name){
		if("刘琼".equals(name)){
			return 153;
		}else if("张岚".equals(name)){
			return 277;
		}else if("李祥惠".equals(name)){
			return 114;
		}else if("汪诚".equals(name)){
			return 2311;
		}else if("翟蓉".equals(name)){
			return 2068;
		}else if("邱伟".equals(name)){
			return 125;
		}
		throw new RuntimeException(name+"error");
	}
	
}
