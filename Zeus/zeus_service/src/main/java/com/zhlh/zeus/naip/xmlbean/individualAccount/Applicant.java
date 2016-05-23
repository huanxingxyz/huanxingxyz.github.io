package com.zhlh.zeus.naip.xmlbean.individualAccount;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * cpic请求报文体中的节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("applicant")
public class Applicant {

	private String apltName; // 投保人名称
	private String apltCretType; // 投保人证件类型
	private String apltCretCode; // 投保人证件号码
	private String apltTelephone; // 投保人固定电话
	private String apltEmail; // 投保人email
	private String apltMobile; // 投保人移动电话
	private String apltAddress; // 投保人地址信息

	public String getApltAddress() {
		return apltAddress;
	}

	public void setApltAddress(String apltAddress) {
		this.apltAddress = apltAddress;
	}

	public String getApltName() {
		return apltName;
	}

	public void setApltName(String apltName) {
		this.apltName = apltName;
	}

	public String getApltCretType() {
		return apltCretType;
	}

	public void setApltCretType(String apltCretType) {
		this.apltCretType = apltCretType;
	}

	public String getApltCretCode() {
		return apltCretCode;
	}

	public void setApltCretCode(String apltCretCode) {
		this.apltCretCode = apltCretCode;
	}

	public String getApltTelephone() {
		return apltTelephone;
	}

	public void setApltTelephone(String apltTelephone) {
		this.apltTelephone = apltTelephone;
	}

	public String getApltEmail() {
		return apltEmail;
	}

	public void setApltEmail(String apltEmail) {
		this.apltEmail = apltEmail;
	}

	public String getApltMobile() {
		return apltMobile;
	}

	public void setApltMobile(String apltMobile) {
		this.apltMobile = apltMobile;
	}
}
