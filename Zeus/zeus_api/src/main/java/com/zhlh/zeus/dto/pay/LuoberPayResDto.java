package com.zhlh.zeus.dto.pay;

import com.zhlh.zeus.dto.BaseResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/32
 * @comment  支付信息返回参数
 */
public class LuoberPayResDto extends BaseResDto {
    private String chargeComCode; // 支付商代码
    private String chargeComName; //支付商名称
    // 支付宝
    private String chargeComUrl; // 支付商链接
    private String serviceUrl; // 服务器通知地址
    // 微信
    private String merchantId; // 商户编号
    private String nonceStr; // 微信支付随机字符串
    private String sign; // 签名
    private String signType; // 签名方式
    private String timeStamp; // 时间戳
    private String bizDesc; // 商品描述
    private String chargeNo; // 支付订单号
    private String currency; // 币种
    private String amount; // 支付金额
    private String codeUrl; // 微信二维码Url
    private String tradeType; // 支付类型 原生支付、扫码支付
    // 第三方
    private String chargeOrderId; // 支付订单号
    private String partner; // 合作者ID
    private String charset; // 参数编码
    private String preOutChargeNo; // 第三方预支付订单号
    private String certId; // 证书号

    public String getChargeComCode() {
        return chargeComCode;
    }

    public void setChargeComCode(String chargeComCode) {
        this.chargeComCode = chargeComCode;
    }

    public String getChargeComName() {
        return chargeComName;
    }

    public void setChargeComName(String chargeComName) {
        this.chargeComName = chargeComName;
    }

    public String getChargeComUrl() {
        return chargeComUrl;
    }

    public void setChargeComUrl(String chargeComUrl) {
        this.chargeComUrl = chargeComUrl;
    }

    public String getChargeOrderId() {
        return chargeOrderId;
    }

    public void setChargeOrderId(String chargeOrderId) {
        this.chargeOrderId = chargeOrderId;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }


    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getPreOutChargeNo() {
        return preOutChargeNo;
    }

    public void setPreOutChargeNo(String preOutChargeNo) {
        this.preOutChargeNo = preOutChargeNo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
