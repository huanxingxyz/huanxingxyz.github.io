package com.zhlh.zeus.naip.xmlbean.individualAccount;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * cpic请求报文体中的节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-10-31
 * 
 */
@XStreamAlias("plcBase")
public class PlcBase {

	private String plcTerminalNo; // 业务归属地编码
	private String plcBusinessNo; // 业务号(订单号)
	private String plcPlanCode; // 承保方案代码
	private String plcStartDate; // 保险起期
	private String plcEndDate; // 保险止期
	private String plcAmount; // 保单保额
	private String plcCopies; // 承保份数
	private String plcElcFlag; // 打印方式

	public String getPlcTerminalNo() {
		return plcTerminalNo;
	}

	public void setPlcTerminalNo(String plcTerminalNo) {
		this.plcTerminalNo = plcTerminalNo;
	}

	public String getPlcBusinessNo() {
		return plcBusinessNo;
	}

	public void setPlcBusinessNo(String plcBusinessNo) {
		this.plcBusinessNo = plcBusinessNo;
	}

	public String getPlcPlanCode() {
		return plcPlanCode;
	}

	public void setPlcPlanCode(String plcPlanCode) {
		this.plcPlanCode = plcPlanCode;
	}

	public String getPlcStartDate() {
		return plcStartDate;
	}

	public void setPlcStartDate(String plcStartDate) {
		this.plcStartDate = plcStartDate;
	}

	public String getPlcEndDate() {
		return plcEndDate;
	}

	public void setPlcEndDate(String plcEndDate) {
		this.plcEndDate = plcEndDate;
	}

	public String getPlcAmount() {
		return plcAmount;
	}

	public void setPlcAmount(String plcAmount) {
		this.plcAmount = plcAmount;
	}

	public String getPlcCopies() {
		return plcCopies;
	}

	public void setPlcCopies(String plcCopies) {
		this.plcCopies = plcCopies;
	}

	public String getPlcElcFlag() {
		return plcElcFlag;
	}

	public void setPlcElcFlag(String plcElcFlag) {
		this.plcElcFlag = plcElcFlag;
	}

}
