package com.zhlh.zeus.business.bo.quoteresult;

import java.util.Vector;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/13
 */
public class QuoteResultBo {

    private Vector<QuoteResultCoveragesBo> coverages = new Vector<QuoteResultCoveragesBo>(); // 险种信息
    private String insuCom; // 保险公司
    private String quoteNo; // 询价订单号
    private String orderNo; // 民生订单号
    private String premium; // 总保费
    private String vciPremium; // 商业险保费
    private String tciPremium; // 交强险保费
    private String sumTravelTax; // 车船税
    private String ownerName; // 车主姓名
    private String ownerCertNo; // 车主证件号码
    private String appName; // 投保人姓名
    private String appCertNo; // 投保人证件号码
    private String licenseNo; // 车牌号

    private String errorMessage = null;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Vector<QuoteResultCoveragesBo> getCoverages() {
        return coverages;
    }

    public void setCoverages(Vector<QuoteResultCoveragesBo> coverages) {
        this.coverages = coverages;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getVciPremium() {
        return vciPremium;
    }

    public void setVciPremium(String vciPremium) {
        this.vciPremium = vciPremium;
    }

    public String getTciPremium() {
        return tciPremium;
    }

    public void setTciPremium(String tciPremium) {
        this.tciPremium = tciPremium;
    }

    public String getSumTravelTax() {
        return sumTravelTax;
    }

    public void setSumTravelTax(String sumTravelTax) {
        this.sumTravelTax = sumTravelTax;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerCertNo() {
        return ownerCertNo;
    }

    public void setOwnerCertNo(String ownerCertNo) {
        this.ownerCertNo = ownerCertNo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCertNo() {
        return appCertNo;
    }

    public void setAppCertNo(String appCertNo) {
        this.appCertNo = appCertNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }


}
