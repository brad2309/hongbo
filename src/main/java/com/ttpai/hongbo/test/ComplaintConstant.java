package com.ttpai.hongbo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class ComplaintConstant {

	
	/**投诉客户类型*/
	public enum ComplaintCustomerType{
		
		CHEZHU(1,"车主"),
		CHESHANG(2,"车商"),
		YUANGONG(3,"员工"),
		QITA(4,"其他");
		public int id;
		public String value;
		private ComplaintCustomerType(int id,String value) {
			this.id = id;
			this.value = value;
		}
		
		public static Integer getIdByName(String value){
			for(ComplaintCustomerType e:ComplaintCustomerType.values()){
				if(value.equals(e.value)){
					return e.id;
				}
			}
			if(StringUtils.isBlank(value)){
				return null;
			}
			throw new RuntimeException(value+" error.");
		}
		
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintCustomerType e:ComplaintCustomerType.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
		public int getId() {
			return id;
		}
		public String getValue() {
			return value;
		}
		
	}
	
	/**严重程度*/
	public enum ComplaintLevel{
		
		L1(1,"低"),
		L2(2,"中"),
		L3(3,"高");
		public int id;
		public String value;
		private ComplaintLevel(int id,String value) {
			this.id = id;
			this.value = value;
		}
		public static int getIdByName(String value){
			for(ComplaintLevel e:ComplaintLevel.values()){
				if(value.equals(e.value)){
					return e.id;
				}
			}
			throw new RuntimeException(value+" error.");
		}
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintLevel e:ComplaintLevel.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
		public int getId() {
			return id;
		}
		public String getValue() {
			return value;
		}
		
	}
	
	/**投诉类型*/
	public enum ComplaintType{
		
		FUWU(1,"服务投诉"),
		YEWU(2,"业务投诉"),
		ZIXUN(3,"咨询");
		public int id;
		public String value;
		private ComplaintType(int id,String value) {
			this.id = id;
			this.value = value;
		}
		public static int getIdByName(String value){
			for(ComplaintType e:ComplaintType.values()){
				if(value.equals(e.value)){
					return e.id;
				}
			}
			throw new RuntimeException(value+" error.");
		}
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintType e:ComplaintType.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
		public int getId() {
			return id;
		}
		public String getValue() {
			return value;
		}
		
	}
	
	/**投诉处理状态*/
	public enum ComplaintStatus{

		TO_AUDIT(10,"待审核"),
		TO_ASSIGN(15,"待分配"),
		IN_PROCESS(20,"处理中"),
		ZAN_GUO(25,"暂果"),
		BANJIE(30,"已办结"),
		REPEAT(35,"重复投诉");
		public int id;
		public String value;
		private ComplaintStatus(int id,String value) {
			this.id = id;
			this.value = value;
		}
		
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintStatus e:ComplaintStatus.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
		public int getId() {
			return id;
		}
		public String getValue() {
			return value;
		}
		
	}
	
	/**投诉来源*/
	public enum ComplaintSource{

		KEFU400(1,"400客服"),
		EMAIL(2,"投诉邮箱"),
		NEIBU(3,"内部流转"),
		GONGZHONG(4,"公众媒体"),
		ZHUANYUAN(5,"专员受理"),
		QITA(0,"其他");
		public int id;
		public String value;
		private ComplaintSource(int id,String value) {
			this.id = id;
			this.value = value;
		}
		
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintSource e:ComplaintSource.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
		
	}
	
	/**最终处理结果是否有责*/
	public enum ComplaintHasDuty{

		YES(1,"有"),
		NO(0,"无");
		public int id;
		public String value;
		private ComplaintHasDuty(int id,String value) {
			this.id = id;
			this.value = value;
		}
		public static Integer getIdByName(String value){
			for(ComplaintHasDuty e:ComplaintHasDuty.values()){
				if(value.equals(e.value)){
					return e.id;
				}
			}
			if(StringUtils.isBlank(value)){
				return null;
			}
			throw new RuntimeException(value+" error.");
		}
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintHasDuty e:ComplaintHasDuty.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
	}
	
	/**最终处理责任方*/
	public enum ComplaintDutyParty{

		JIANCE_YAOYUE(1,"检测邀约"),
		JIANCE(2,"检测"),
		JIANCE2(2,"检测部"),
		CHENGJIAO_YAOYUE(3,"成交邀约"),
		MENDIAN(4,"门店责任"),
		MENDIAN2(4,"门店接待"),
		SHANGHAI_SHOUHOU_ZHONGXIN(5,"上海售后中心责任"),
		BANZHENG_SHANG(6,"办证商责任"),
		SHANGJIA(7,"上家责任"),
		SHANGJIA2(7,"上家"),
		QITA(9,"公司"),
		QITA2(9,"商拓问题"),
		QITA3(9,"售后跟单员"),
		QITA4(9,"其他"),
		QITA5(9,"售后"),
		QITA6(9,"供应商"),
		QITA7(9,"车管所"),
		XIAJIA2(8,"下家"),
		XIAJIA3(8,"下家违约"),
		XIAJIA(8,"下家责任");
		public int id;
		public String value;
		private ComplaintDutyParty(int id,String value) {
			this.id = id;
			this.value = value;
		}
		public static Integer getIdByName(String value){
			for(ComplaintDutyParty e:ComplaintDutyParty.values()){
				if(value.trim().equals(e.value)){
					return e.id;
				}
			}
			if(StringUtils.isBlank(value)){
				return null;
			}
			throw new RuntimeException(value+" error.");
		}
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintDutyParty e:ComplaintDutyParty.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}
		
	}
	
	/**最终处理状态*/
	public enum ComplaintResultStatus{

		ZANGUO(1,"暂果"),
		BANJIE(2,"办结");
		public int id;
		public String value;
		private ComplaintResultStatus(int id,String value) {
			this.id = id;
			this.value = value;
		}
		
		public static String getValue(Integer id){
			if(id!=null){
				for(ComplaintResultStatus e:ComplaintResultStatus.values()){
					if(e.id==id.intValue()){
						return e.value;
					}
				}
			}
			return "";
		}
	}
	
	/**操作日志*/
	public enum ComplaintLog{

		ADD("新增投诉","新增投诉"),
		ASSIGN("分配","分配任务至*"),
		ADD_LICENSE("新增车源","新增车源“*”（车牌号）"),
		EDIT("修改投诉信息","修改投诉信息"),
		CALL("联系投诉人","打电话给投诉人 听录音（点击“听录音”下载录音）"),
		SET_NOT_CALL("设置未打通","变更联系人状态为“未打通”，系统设置跟进时间为*"),
		SET_FOLLOW_TIME("设置预约跟进","变更联系人状态为“预约跟进”，设置明确跟进时间为*"),
		ADD_CONTACT("新增备用联系人","新增联系方式"),
		ADD_FOLLOW("提交跟进记录","新增跟进记录"),
		ADD_RESULT("提交投诉处理结果","提交处理结果为“*”"),
		AUDIT_YES("审核通过","审核通过"),
		AUDIT_NO("审核不通过","审核不通过"),
		RESTART("投诉重启","重启投诉"),
		SET_REPEAT("设置重复投诉","判定为重复投诉"),
		CANCEL_REPEAT("取消重复投诉","取消重复投诉");
		public String operation;
		public String content;
		private ComplaintLog(String operation,String content) {
			this.operation = operation;
			this.content = content;
		}
	}
	
	public static List<Map<String, Object>> getEnumValueList(Class<?> enumClass){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(enumClass.equals(ComplaintCustomerType.class)){
			for(ComplaintCustomerType e:ComplaintCustomerType.values()){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", e.id);
				m.put("value", e.value);
				list.add(m);
			}
		}else if(enumClass.equals(ComplaintSource.class)){
				for(ComplaintSource e:ComplaintSource.values()){
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("id", e.id);
					m.put("value", e.value);
					list.add(m);
				}
		}else if(enumClass.equals(ComplaintLevel.class)){
			for(ComplaintLevel e:ComplaintLevel.values()){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", e.id);
				m.put("value", e.value);
				list.add(m);
			}
		}else if(enumClass.equals(ComplaintType.class)){
			for(ComplaintType e:ComplaintType.values()){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", e.id);
				m.put("value", e.value);
				list.add(m);
			}
		}else if(enumClass.equals(ComplaintStatus.class)){
			for(ComplaintStatus e:ComplaintStatus.values()){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", e.id);
				m.put("value", e.value);
				list.add(m);
			}
		}
		return list;
	}

}
