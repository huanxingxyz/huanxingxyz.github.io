package com.zhlh.zeus.naip.xmlbean.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * cpic请求报文头HEAD
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-10-31
 * 
 */
@XStreamAlias("head")
public class ReqHead {

	private String partnerCode; // 合作方编码
	private String transactionCode; // 业务交易码
	private String messageId; // 交易流水号
	private String transactionEffectiveDate;// 交易时间
	private String user; // 合作方用户
	private String password; // 用户密码

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
