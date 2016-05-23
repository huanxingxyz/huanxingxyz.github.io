package com.zhlh.zeus.dto.pay;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/23
 * @comment 支付 请求参数
 */
public class LuoberPayReqDto extends BaseReqDto {
    private String channel; // 交易渠道
    private String insuCom; // 保险公司
    private String bizOrderId; // 业务订单号 内部保单号Id
    private String tradeType; // 微信交易类型  微信支付 JSAPI 扫码支付 NATIVE
    private String bizNo; // 业务流水号   萝卜订单号
    private String chargeComCode; // 支付商代码  WX=微信
    private String userId; // 账号 openid
    private String rcvName; // 收货人姓名
    private String rcvPostAddress; // 收货地址
    private String rcvMobile; // 收获人手机号
    private String orderPremium; // 订单金额
    private String licenseNo; // 车牌号

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getBizOrderId() {
        return bizOrderId;
    }

    public void setBizOrderId(String bizOrderId) {
        this.bizOrderId = bizOrderId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getChargeComCode() {
        return chargeComCode;
    }

    public void setChargeComCode(String chargeComCode) {
        this.chargeComCode = chargeComCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRcvName() {
        return rcvName;
    }

    public void setRcvName(String rcvName) {
        this.rcvName = rcvName;
    }

    public String getRcvPostAddress() {
        return rcvPostAddress;
    }

    public void setRcvPostAddress(String rcvPostAddress) {
        this.rcvPostAddress = rcvPostAddress;
    }

    public String getRcvMobile() {
        return rcvMobile;
    }

    public void setRcvMobile(String rcvMobile) {
        this.rcvMobile = rcvMobile;
    }

    public String getOrderPremium() {
        return orderPremium;
    }

    public void setOrderPremium(String orderPremium) {
        this.orderPremium = orderPremium;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
}
