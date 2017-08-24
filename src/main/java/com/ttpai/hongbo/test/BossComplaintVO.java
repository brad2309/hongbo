package com.ttpai.hongbo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;


public class BossComplaintVO {
	
	
	private BossComplaintResultVO result;
	/**
	* 
	*/
	private Integer id;//
	/**
	 * 投诉客户名称
	 */
	private String customerName;// 投诉客户名称
	/**
	 * 客户类型：车主1、车商2、员工3、其他0
	 */
	private Integer customerType;// 客户类型：车主1、车商2、员工3、其他0
	private String customerTypeText;
	/**
	 * 严重程度：高3、中2、低1
	 */
	private Integer compLevel;// 严重程度：高3、中2、低1
	/**
	 * 投诉类型：服务投诉1、业务投诉2、咨询3
	 */
	private Integer complaintType;// 投诉类型：服务投诉1、业务投诉2、咨询3
	private String complaintTypeText;
	/**
	 * 投诉处理状态：待分配15、处理中20、待审核10、暂果25、已办结30、重复投诉35
	 */
	private Integer compStatus;// 投诉处理状态：待分配15、处理中20、待审核10、暂果25、已办结30、重复投诉35
	/**
	 * 投诉内容
	 */
	private String content;// 投诉内容
	/**
	 * 投诉创建时间
	 */
	private Date createTime;// 投诉创建时间
	/**
	 * 投诉概要
	 */
	private String summary;// 投诉概要
	/**
	 * 投诉创建人
	 */
	private Integer createUserId;// 投诉创建人
	/**
	 * 联系方式
	 */
	private String phone;// 联系方式
	/**
	 * 车牌号
	 */
	private String license;// 车牌号
	/**
	 * 来源：400客服1、投诉邮箱2、内部流转3、公众媒体4、专员受理5、其他0
	 */
	private Integer compSource;// 来源：400客服1、投诉邮箱2、内部流转3、公众媒体4、专员受理5、其他0
	/**
	 * 车源ID
	 */
	private Integer auctionId;// 车源ID
	/**
	 * 分配的处理人员
	 */
	private Integer handlerUserId;// 分配的处理人员
	private String handlerUserName;// 分配的处理人员

	/**
	 * 新增的联系人名称
	 */
	private String secondCustomerName;// 新增的联系人名称
	/**
	 * 新增的联系人电话
	 */
	private String secondCustomerPhone;// 新增的联系人电话
	/**
	 * 预约跟进时间
	 */
	private Date followTime;// 预约跟进时间
	/**
	 * 修改时间
	 */
	private Date updateTime;// 修改时间
	/**
	 * 最后操作时间
	 */
	private Date lastOperateTime;// 最后操作时间
	
	private Date assignTime;// 投诉创建时间
	private String familyStr;//车系
	private String brand;//车品牌
	private Date createTimeStart;//查询开始时间
	private Date createTimeEnd;//查询结束时间
	private Integer auctionCityId;//车源所属城市
	private Integer auctionState;//车源全局状态
	private Integer statusOrder;//用于按状态排序
	private List<String> tempFollowPicList;
	private Integer tempPicId;
	private String phoneEn;
	private String secondCustomerPhoneEn;

	/**
	* 
	*/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	* 
	*/
	public Integer getId() {
		return this.id;
	}

	/**
	 * 投诉客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 投诉客户名称
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * 客户类型：车主1、车商2、员工3、其他0
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	/**
	 * 客户类型：车主1、车商2、员工3、其他0
	 */
	public Integer getCustomerType() {
		return this.customerType;
	}


	/**
	 * 投诉类型：服务投诉1、业务投诉2、咨询3
	 */
	public void setComplaintType(Integer complaintType) {
		this.complaintType = complaintType;
	}

	/**
	 * 投诉类型：服务投诉1、业务投诉2、咨询3
	 */
	public Integer getComplaintType() {
		return this.complaintType;
	}


	/**
	 * 投诉内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 投诉内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 投诉创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 投诉创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	public String getCreateTimeText(){
		return DateFormatUtils.format(createTime, "yyyy-MM-dd");
	}

	/**
	 * 投诉概要
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * 投诉概要
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * 投诉创建人
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 投诉创建人
	 */
	public Integer getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 联系方式
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 车牌号
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * 车牌号
	 */
	public String getLicense() {
		return this.license;
	}


	/**
	 * 车源ID
	 */
	public void setAuctionId(Integer auctionId) {
		this.auctionId = auctionId;
	}

	/**
	 * 车源ID
	 */
	public Integer getAuctionId() {
		return this.auctionId;
	}

	/**
	 * 分配的处理人员
	 */
	public void setHandlerUserId(Integer handlerUserId) {
		this.handlerUserId = handlerUserId;
	}

	/**
	 * 分配的处理人员
	 */
	public Integer getHandlerUserId() {
		return this.handlerUserId;
	}

	/**
	 * 新增的联系人名称
	 */
	public void setSecondCustomerName(String secondCustomerName) {
		this.secondCustomerName = secondCustomerName;
	}

	/**
	 * 新增的联系人名称
	 */
	public String getSecondCustomerName() {
		return this.secondCustomerName;
	}

	/**
	 * 新增的联系人电话
	 */
	public void setSecondCustomerPhone(String secondCustomerPhone) {
		this.secondCustomerPhone = secondCustomerPhone;
	}

	/**
	 * 新增的联系人电话
	 */
	public String getSecondCustomerPhone() {
		return this.secondCustomerPhone;
	}

	/**
	 * 预约跟进时间
	 */
	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}

	/**
	 * 预约跟进时间
	 */
	public Date getFollowTime() {
		return this.followTime;
	}

	/**
	 * 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 最后操作时间
	 */
	public void setLastOperateTime(Date lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
	}

	/**
	 * 最后操作时间
	 */
	public Date getLastOperateTime() {
		return this.lastOperateTime;
	}


	public String getFamilyStr() {
		return familyStr;
	}

	public void setFamilyStr(String familyStr) {
		this.familyStr = familyStr;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getAuctionCityId() {
		return auctionCityId;
	}

	public void setAuctionCityId(Integer auctionCityId) {
		this.auctionCityId = auctionCityId;
	}

	public Integer getAuctionState() {
		return auctionState;
	}

	public void setAuctionState(Integer auctionState) {
		this.auctionState = auctionState;
	}

	public String getAuctionStateText() {
		if(auctionState==null){
			return "";
		}
		String s = String.valueOf(auctionState);
		if(s.startsWith("1")){
			return "检测邀约";
		}else if(s.startsWith("2")||"300".equals(s)){
			return "检测";
		}else if(s.startsWith("3")){
			return "拍卖中";
		}else if(s.startsWith("4")){
			return "成交邀约";
		}else if(s.startsWith("5")){
			return "成交接待";
		}else if(s.startsWith("6")){
			return "售后";
		}
		return auctionState+"";
	}

	/**
	 * @return the assignTime
	 */
	public Date getAssignTime() {
		return assignTime;
	}

	/**
	 * @param assignTime the assignTime to set
	 */
	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

	public String getHandlerUserName() {
		return handlerUserName;
	}

	public void setHandlerUserName(String handlerUserName) {
		this.handlerUserName = handlerUserName;
	}
	public Integer getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(Integer statusOrder) {
		this.statusOrder = statusOrder;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	

	/**
	 * @return the followTimeText
	 */
	public String getFollowTimeText() {
		if(followTime!=null){
			return DateFormatUtils.format(followTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	/**
	 * @return the lastOperateTimeText
	 */
	public String getLastOperateTimeText() {
		if(lastOperateTime!=null){
			return DateFormatUtils.format(lastOperateTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	public List<String> getTempFollowPicList() {
		return tempFollowPicList;
	}
	public void setTempFollowPicList(List<String> tempFollowPicList) {
		this.tempFollowPicList = tempFollowPicList;
	}
	public Integer getTempPicId() {
		return tempPicId;
	}
	public void setTempPicId(Integer tempPicId) {
		this.tempPicId = tempPicId;
	}


	public String getPhoneEn() {
		return phoneEn;
	}

	public void setPhoneEn(String phoneEn) {
		this.phoneEn = phoneEn;
	}

	public String getSecondCustomerPhoneEn() {
		return secondCustomerPhoneEn;
	}

	public void setSecondCustomerPhoneEn(String secondCustomerPhoneEn) {
		this.secondCustomerPhoneEn = secondCustomerPhoneEn;
	}

	public Integer getCompLevel() {
		return compLevel;
	}

	public void setCompLevel(Integer compLevel) {
		this.compLevel = compLevel;
	}

	public Integer getCompStatus() {
		return compStatus;
	}

	public void setCompStatus(Integer compStatus) {
		this.compStatus = compStatus;
	}

	public Integer getCompSource() {
		return compSource;
	}

	public void setCompSource(Integer compSource) {
		this.compSource = compSource;
	}

	public BossComplaintResultVO getResult() {
		return result;
	}
	public void setResult(BossComplaintResultVO result) {
		this.result = result;
	}
	
	
}
