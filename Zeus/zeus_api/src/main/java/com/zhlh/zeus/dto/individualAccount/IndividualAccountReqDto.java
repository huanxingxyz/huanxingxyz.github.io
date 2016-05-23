package com.zhlh.zeus.dto.individualAccount;

import com.zhlh.zeus.dto.BaseReqDto;

import java.util.Date;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/30
 * @comment盗刷险请求Dto对象
 */
public class IndividualAccountReqDto extends BaseReqDto {
    private String channel; // 渠道
    private String appType; // 访问类型 1 接口，2，app 3,微信 4，官网 5...
    private String insuredName; // 被保人姓名
    private String insuredCertNo; // 被保人身份证号码
    private String insuredMobile; // 被保人手机号
    private int period; // 保险期限 1,3,6,9,12
    private int purchaseFlag; // 购买标志 0 赠送 1 购买
    private String amount; // 保额5000、10000、50000、100000，含附加险方案需要再加100(如：50100)
    private String insureDate; // 投保日期
    private String premium; // 保费
    private String startDate; // 投保起期
    private String endDate; // 投保止期
    private String cityCode; // 城市代码
    private String payStatus; // 支付状态
    private String payPremium; // 支付金额
    private String sumPremium; // 总价
    private String payMoney; // 实际支付价格
    private String payType; // 支付类型
    private String insuComCode; // 保险公司编号
    private String chargeNo; // 支付流水号
    private String chargeType; // 支付方式
    private String chargeComCode; // 支付商代码
    private String chargeResult; // 收费结果
    private Date payTime; // 支付时间

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredCertNo() {
        return insuredCertNo;
    }

    public void setInsuredCertNo(String insuredCertNo) {
        this.insuredCertNo = insuredCertNo;
    }

    public String getInsuredMobile() {
        return insuredMobile;
    }

    public void setInsuredMobile(String insuredMobile) {
        this.insuredMobile = insuredMobile;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPurchaseFlag() {
        return purchaseFlag;
    }

    public void setPurchaseFlag(int purchaseFlag) {
        this.purchaseFlag = purchaseFlag;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInsureDate() {
        return insureDate;
    }

    public void setInsureDate(String insureDate) {
        this.insureDate = insureDate;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayPremium() {
        return payPremium;
    }

    public void setPayPremium(String payPremium) {
        this.payPremium = payPremium;
    }

    public String getSumPremium() {
        return sumPremium;
    }

    public void setSumPremium(String sumPremium) {
        this.sumPremium = sumPremium;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getInsuComCode() {
        return insuComCode;
    }

    public void setInsuComCode(String insuComCode) {
        this.insuComCode = insuComCode;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeComCode() {
        return chargeComCode;
    }

    public void setChargeComCode(String chargeComCode) {
        this.chargeComCode = chargeComCode;
    }

    public String getChargeResult() {
        return chargeResult;
    }

    public void setChargeResult(String chargeResult) {
        this.chargeResult = chargeResult;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
