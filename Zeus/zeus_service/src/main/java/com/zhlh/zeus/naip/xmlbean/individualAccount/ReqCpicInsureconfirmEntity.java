package com.zhlh.zeus.naip.xmlbean.individualAccount;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.common.Entity;


/**
 * cpic请求报文体中的entity节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("entity")
public class ReqCpicInsureconfirmEntity extends Entity implements Serializable{

	private static final long serialVersionUID = 6787950437967378251L;
	private PlcBase plcBase;
	private Applicant applicant;
	@XStreamAlias("insuredList")
	private List<Insured> insuredList;
	@XStreamAlias("addressList")
	private List<Address> addressList;
	private ElcPolicy elcPolicy;
	private String plcApplyNo;
	private String plcNo;
	private String plcStatus;
	private String plcSumInsured;
	private String plcPremium;
	private String plcEffctDate;
	private String plcElcFlag;
	private String elcMsgFlag;
	private String elcEmlFlag;

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

	public String getPlcStatus() {
		return plcStatus;
	}

	public void setPlcStatus(String plcStatus) {
		this.plcStatus = plcStatus;
	}

	public String getPlcNo() {
		return plcNo;
	}

	public void setPlcNo(String plcNo) {
		this.plcNo = plcNo;
	}

	public String getPlcApplyNo() {
		return plcApplyNo;
	}

	public void setPlcApplyNo(String plcApplyNo) {
		this.plcApplyNo = plcApplyNo;
	}

	public PlcBase getPlcBase() {
		return plcBase;
	}

	public void setPlcBase(PlcBase plcBase) {
		this.plcBase = plcBase;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public List<Insured> getInsuredList() {
		return insuredList;
	}

	public void setInsuredList(List<Insured> insuredList) {
		this.insuredList = insuredList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public ElcPolicy getElcPolicy() {
		return elcPolicy;
	}

	public void setElcPolicy(ElcPolicy elcPolicy) {
		this.elcPolicy = elcPolicy;
	}

}
