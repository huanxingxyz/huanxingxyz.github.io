package com.zhlh.zeus.dto.insureConfirm;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/19
 * @comment 核保 请求参数
 */
public class InsureConfirmReqDto extends BaseReqDto {
    private String channel; // 渠道
    private String insuCom; // 保险公司编码
    private String quoteNo; // Remex 报价主键
    private String flowNo; // 流水号
    private String insuComApplicationNo; // 保险公司询价单号
    private String verificationCode; // 手机验证码
    private String licenseNo; // 车牌号
    private String uniqueId; // 人保专用
    //保单寄送信息
    private String postName; // 配送人姓名
    private String postMobile; // 配送人手机号
    private String postAddr; // 配送人地址

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

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getInsuComApplicationNo() {
        return insuComApplicationNo;
    }

    public void setInsuComApplicationNo(String insuComApplicationNo) {
        this.insuComApplicationNo = insuComApplicationNo;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostMobile() {
        return postMobile;
    }

    public void setPostMobile(String postMobile) {
        this.postMobile = postMobile;
    }

    public String getPostAddr() {
        return postAddr;
    }

    public void setPostAddr(String postAddr) {
        this.postAddr = postAddr;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
}
