package com.zhlh.zeus.naip.xmlbean.individualAccount;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.common.Entity;

/**
 * cpic响应报文体中的entity节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("entity")
public class ResCpicInsureconfirmEntity extends Entity {

	private String plcBusinessNo; // 业务号(订单号)
	private String plcApplyNo; // 投保单号
	private String plcNo; // 保单号
	private String plcStatus; // 保单状态
	private String plcSumInsured; // 保单保额
	private String plcPremium; // 保单保费
	private String plcRmbPremium; // 保单保费（人民币）
	private String plcExchgRate; // 折人民币汇率
	private String plcEffctDate; // 签发日期
	private String plcElcFlag; // 打印方式
	private String plcElcStatus; // 是否已生成电子保单
	private String elcMsgFlag; // 电子保单短信发送标志
	private String elcEmlFlag; // 电子保单邮件发送标志

	public String getPlcBusinessNo() {
		return plcBusinessNo;
	}

	public void setPlcBusinessNo(String plcBusinessNo) {
		this.plcBusinessNo = plcBusinessNo;
	}

	public String getPlcApplyNo() {
		return plcApplyNo;
	}

	public void setPlcApplyNo(String plcApplyNo) {
		this.plcApplyNo = plcApplyNo;
	}

	public String getPlcNo() {
		return plcNo;
	}

	public void setPlcNo(String plcNo) {
		this.plcNo = plcNo;
	}

	public String getPlcStatus() {
		return plcStatus;
	}

	public void setPlcStatus(String plcStatus) {
		this.plcStatus = plcStatus;
	}

	public String getPlcSumInsured() {
		return plcSumInsured;
	}

	public void setPlcSumInsured(String plcSumInsured) {
		this.plcSumInsured = plcSumInsured;
	}

	public String getPlcPremium() {
		return plcPremium;
	}

	public void setPlcPremium(String plcPremium) {
		this.plcPremium = plcPremium;
	}

	public String getPlcRmbPremium() {
		return plcRmbPremium;
	}

	public void setPlcRmbPremium(String plcRmbPremium) {
		this.plcRmbPremium = plcRmbPremium;
	}

	public String getPlcExchgRate() {
		return plcExchgRate;
	}

	public void setPlcExchgRate(String plcExchgRate) {
		this.plcExchgRate = plcExchgRate;
	}

	public String getPlcEffctDate() {
		return plcEffctDate;
	}

	public void setPlcEffctDate(String plcEffctDate) {
		this.plcEffctDate = plcEffctDate;
	}

	public String getPlcElcFlag() {
		return plcElcFlag;
	}

	public void setPlcElcFlag(String plcElcFlag) {
		this.plcElcFlag = plcElcFlag;
	}

	public String getPlcElcStatus() {
		return plcElcStatus;
	}

	public void setPlcElcStatus(String plcElcStatus) {
		this.plcElcStatus = plcElcStatus;
	}

	public String getElcMsgFlag() {
		return elcMsgFlag;
	}

	public void setElcMsgFlag(String elcMsgFlag) {
		this.elcMsgFlag = elcMsgFlag;
	}

	public String getElcEmlFlag() {
		return elcEmlFlag;
	}

	public void setElcEmlFlag(String elcEmlFlag) {
		this.elcEmlFlag = elcEmlFlag;
	}

}
