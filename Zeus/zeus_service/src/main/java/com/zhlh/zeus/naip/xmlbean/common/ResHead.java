package com.zhlh.zeus.naip.xmlbean.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.individualAccount.ResponseCompleteMessageStatus;

import java.io.Serializable;


/**
 * cpic响应报文头HEAD
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("head")
public class ResHead implements Serializable{

	private static final long serialVersionUID = 3686891751282303106L;
	private String partnerCode; // 合作方编码
	private String transactionCode; // 业务交易码
	private String messageId; // 交易流水号
	private String transactionEffectiveDate; // 交易时间
	@XStreamAlias("responseCompleteMessageStatus")
	private ResponseCompleteMessageStatus responseCompleteMessageStatus;// 服务响应状态及描述信息

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTransactionEffectiveDate() {
		return transactionEffectiveDate;
	}

	public void setTransactionEffectiveDate(String transactionEffectiveDate) {
		this.transactionEffectiveDate = transactionEffectiveDate;
	}

	public ResponseCompleteMessageStatus getResponseCompleteMessageStatus() {
		return responseCompleteMessageStatus;
	}

	public void setResponseCompleteMessageStatus(ResponseCompleteMessageStatus responseCompleteMessageStatus) {
		this.responseCompleteMessageStatus = responseCompleteMessageStatus;
	}
}
