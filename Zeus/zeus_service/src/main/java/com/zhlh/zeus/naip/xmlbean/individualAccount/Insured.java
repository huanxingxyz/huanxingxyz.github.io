package com.zhlh.zeus.naip.xmlbean.individualAccount;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * cpic请求报文体中的节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("insured")
public class Insured {

	private String isrdName; // 被保人名称
	private String isrdCretType; // 被保人证件类型
	private String isrdCretCode; // 被保人证件号码
	private String isrdTelephone; // 被保人固定电话
	private String isrdEmail; // 被保人email
	private String isrdMobile; // 被保人移动电话
	private String isrdAddress; // 被保人地址信息

	public String getIsrdName() {
		return isrdName;
	}

	public void setIsrdName(String isrdName) {
		this.isrdName = isrdName;
	}

	public String getIsrdCretType() {
		return isrdCretType;
	}

	public void setIsrdCretType(String isrdCretType) {
		this.isrdCretType = isrdCretType;
	}

	public String getIsrdCretCode() {
		return isrdCretCode;
	}

	public void setIsrdCretCode(String isrdCretCode) {
		this.isrdCretCode = isrdCretCode;
	}

	public String getIsrdTelephone() {
		return isrdTelephone;
	}

	public void setIsrdTelephone(String isrdTelephone) {
		this.isrdTelephone = isrdTelephone;
	}

	public String getIsrdEmail() {
		return isrdEmail;
	}

	public void setIsrdEmail(String isrdEmail) {
		this.isrdEmail = isrdEmail;
	}

	public String getIsrdMobile() {
		return isrdMobile;
	}

	public void setIsrdMobile(String isrdMobile) {
		this.isrdMobile = isrdMobile;
	}

	public String getIsrdAddress() {
		return isrdAddress;
	}

	public void setIsrdAddress(String isrdAddress) {
		this.isrdAddress = isrdAddress;
	}

}
