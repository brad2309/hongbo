package com.ttpai.hongbo.test;


public class BossComplaintResultVO {
	/**
	* 
	*/
	private Integer id;//
	/**
	 * 关联投诉ID一对一
	 */
	private Integer complaintId;// 关联投诉ID一对一
	/**
	 * 是否有责：1有0无
	 */
	private Integer hasDuty;// 是否有责：1有0无
	/**
	 * 责任方：检测邀约1、检测2、成交邀约3、门店4、上海售后中心5、办证商6、商家责任7、下家责任8
	 */
	private Integer dutyParty;// 责任方：检测邀约1、检测2、成交邀约3、门店4、上海售后中心5、办证商6、商家责任7、下家责任8
	private String  dutyPartyText;
	/**
	 * 责任原因
	 */
	private String reason;// 责任原因
	/**
	 * 部门处理结果
	 */
	private String departResult;// 部门处理结果
	/**
	 * 投诉中心处理结果
	 */
	private String complaintResult;// 投诉中心处理结果
	/**
	 * 附件
	 */
	private String attach;// 附件
	
	/**
	 * 前端传的附件url
	 */
	private String fileListStr;
	
	/**
	 * 暂果1办结2
	 */
	private Integer resultStatus;// 暂果1办结2

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
	 * 关联投诉ID一对一
	 */
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	/**
	 * 关联投诉ID一对一
	 */
	public Integer getComplaintId() {
		return this.complaintId;
	}

	/**
	 * 是否有责：1有0无
	 */
	public void setHasDuty(Integer hasDuty) {
		this.hasDuty = hasDuty;
	}

	/**
	 * 是否有责：1有0无
	 */
	public Integer getHasDuty() {
		return this.hasDuty;
	}

	/**
	 * 责任方：检测邀约1、检测2、成交邀约3、门店4、上海售后中心5、办证商6、商家责任7、下家责任8
	 */
	public void setDutyParty(Integer dutyParty) {
		this.dutyParty = dutyParty;
	}

	/**
	 * 责任方：检测邀约1、检测2、成交邀约3、门店4、上海售后中心5、办证商6、商家责任7、下家责任8
	 */
	public Integer getDutyParty() {
		return this.dutyParty;
	}

	/**
	 * 责任原因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 责任原因
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * 部门处理结果
	 */
	public void setDepartResult(String departResult) {
		this.departResult = departResult;
	}

	/**
	 * 部门处理结果
	 */
	public String getDepartResult() {
		return this.departResult;
	}

	/**
	 * 投诉中心处理结果
	 */
	public void setComplaintResult(String complaintResult) {
		this.complaintResult = complaintResult;
	}

	/**
	 * 投诉中心处理结果
	 */
	public String getComplaintResult() {
		return this.complaintResult;
	}

	/**
	 * 附件
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}

	/**
	 * 附件
	 */
	public String getAttach() {
		return this.attach;
	}

	/**
	 * 暂果1办结2
	 */
	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	/**
	 * 暂果1办结2
	 */
	public Integer getResultStatus() {
		return this.resultStatus;
	}


	/**
	 * @param dutyPartyText the dutyPartyText to set
	 */
	public void setDutyPartyText(String dutyPartyText) {
		this.dutyPartyText = dutyPartyText;
	}

	/**
	 * @return the fileListStr
	 */
	public String getFileListStr() {
		return fileListStr;
	}

	/**
	 * @param fileListStr the fileListStr to set
	 */
	public void setFileListStr(String fileListStr) {
		this.fileListStr = fileListStr;
	}

}
