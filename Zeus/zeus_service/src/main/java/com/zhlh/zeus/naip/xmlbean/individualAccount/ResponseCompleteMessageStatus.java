package com.zhlh.zeus.naip.xmlbean.individualAccount;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 服务响应状态及描述信息
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("responseCompleteMessageStatus")
public class ResponseCompleteMessageStatus implements Serializable{

	private static final long serialVersionUID = 8335153810111860084L;
	private String messageStatusCode; // 服务状态编码
	private String messageStatusDescriptionNumber; // 服务响应描述信息个数
	@XStreamAlias("messageStatusDescriptionList")
	private List<MessageStatusDescription> messageStatusDescription;// 服务响应描述信息

	public String getMessageStatusCode() {
		return messageStatusCode;
	}

	public void setMessageStatusCode(String messageStatusCode) {
		this.messageStatusCode = messageStatusCode;
	}

	public String getMessageStatusDescriptionNumber() {
		return messageStatusDescriptionNumber;
	}

	public void setMessageStatusDescriptionNumber(String messageStatusDescriptionNumber) {
		this.messageStatusDescriptionNumber = messageStatusDescriptionNumber;
	}

	public List<MessageStatusDescription> getMessageStatusDescription() {
		return messageStatusDescription;
	}

	public void setMessageStatusDescription(List<MessageStatusDescription> messageStatusDescription) {
		this.messageStatusDescription = messageStatusDescription;
	}
}
