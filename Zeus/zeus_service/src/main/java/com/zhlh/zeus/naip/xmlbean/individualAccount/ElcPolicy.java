package com.zhlh.zeus.naip.xmlbean.individualAccount;


/**
 * cpic请求报文体中的节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
public class ElcPolicy {

	private String elcMsgFlag; // 短信发送标志
	private String elcMobile; // 短信接收手机号
	private String elcEmlFlag; // 邮件发送标志
	private String elcEmail; // 电子保单接收邮箱

	public String getElcMsgFlag() {
		return elcMsgFlag;
	}

	public void setElcMsgFlag(String elcMsgFlag) {
		this.elcMsgFlag = elcMsgFlag;
	}

	public String getElcMobile() {
		return elcMobile;
	}

	public void setElcMobile(String elcMobile) {
		this.elcMobile = elcMobile;
	}

	public String getElcEmlFlag() {
		return elcEmlFlag;
	}

	public void setElcEmlFlag(String elcEmlFlag) {
		this.elcEmlFlag = elcEmlFlag;
	}

	public String getElcEmail() {
		return elcEmail;
	}

	public void setElcEmail(String elcEmail) {
		this.elcEmail = elcEmail;
	}

}
