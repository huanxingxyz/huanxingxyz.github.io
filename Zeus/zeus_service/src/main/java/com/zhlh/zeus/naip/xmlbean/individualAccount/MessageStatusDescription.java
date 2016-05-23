package com.zhlh.zeus.naip.xmlbean.individualAccount;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 服务响应描述信息
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("messageStatusDescription")
public class MessageStatusDescription {

	private String messageStatusSubCode; // 服务响应码
	private String messageStatusSubDescription; // 服务响应描述

	public String getMessageStatusSubCode() {
		return messageStatusSubCode;
	}

	public void setMessageStatusSubCode(String messageStatusSubCode) {
		this.messageStatusSubCode = messageStatusSubCode;
	}

	public String getMessageStatusSubDescription() {
		return messageStatusSubDescription;
	}

	public void setMessageStatusSubDescription(String messageStatusSubDescription) {
		this.messageStatusSubDescription = messageStatusSubDescription;
	}
}
