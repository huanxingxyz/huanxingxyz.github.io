package com.zhlh.zeus.dto.identityCollect;

import com.zhlh.zeus.dto.BaseReqDto;

import java.io.Serializable;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/12
 * @comment 身份采集 请求参数
 */
public class IdentityCollectReqDto extends BaseReqDto implements Serializable {

    private  String  channel; // 来源
    private  String  insuCom; // 保险公司

    private  String certNo; // 投保人同被保人身份证号
    private  String name; // 投保人姓名
    private  String mobile; // 投保人手机号
    private  String quoteNo; // 报价id
    private  String uniqueId; // 人保专用
    private  String flowNo; // 福德专用报价单号
    private  String licenseNo; // 车牌号

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

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
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

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
}
