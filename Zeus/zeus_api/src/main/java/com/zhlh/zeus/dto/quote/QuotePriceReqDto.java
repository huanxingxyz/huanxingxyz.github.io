package com.zhlh.zeus.dto.quote;

import com.zhlh.zeus.dto.BaseReqDto;

import java.util.List;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 16:12
 * @comment 询价服务请求参数DTO
 */
public class QuotePriceReqDto extends BaseReqDto {

    /**
     * 以下字段必传
     */
    private String channel; // 询价来源
    private String insuCom; // 保险公司
    private String licenseNo; // 车牌号
    private String frameNo; // 车架号
    private String engineNo; // 发动机号
    private String enrollDate; // 车辆初始登记日期，非北京地区
    private String brandName; // 厂牌型号，非北京地区
    private String owner; // 车主姓名
    private List<CoverageDataDto> coverageDataDtoList; // 险种list

    private String purchasePrice; // 新车购置价，外地车必传（由应用端选择），报价时确定唯一车辆
    private String tciStartDate; // 选传，交强险起保日期，格式 2016-04-18
    private String vciStartDate; // 选传，商业险起保日期，格式 2016-04-18

    private Integer isInsureConfirm = 0; // 是否用于核保前报价，默认0，不是

    /**
     * 以下字段用于核保之前再次询价，所有字段必传
     */
    private String actualValue; // 实际价值
    private String tciDefaultStartDate; // 默认交强险上年止期
    private String vciDefaultStartDate; // 默认商业险上年止期
    private CustomerDataDto ownerData; // 车主信息
    private CustomerDataDto insurantData; // 被保人信息
    private CustomerDataDto policyHolderData; // 投保人信息


    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

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

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public List<CoverageDataDto> getCoverageDataDtoList() {
        return coverageDataDtoList;
    }

    public void setCoverageDataDtoList(List<CoverageDataDto> coverageDataDtoList) {
        this.coverageDataDtoList = coverageDataDtoList;
    }

    public String getTciStartDate() {
        return tciStartDate;
    }

    public void setTciStartDate(String tciStartDate) {
        this.tciStartDate = tciStartDate;
    }

    public String getVciStartDate() {
        return vciStartDate;
    }

    public void setVciStartDate(String vciStartDate) {
        this.vciStartDate = vciStartDate;
    }

    public Integer getIsInsureConfirm() {
        return isInsureConfirm;
    }

    public void setIsInsureConfirm(Integer isInsureConfirm) {
        this.isInsureConfirm = isInsureConfirm;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getTciDefaultStartDate() {
        return tciDefaultStartDate;
    }

    public void setTciDefaultStartDate(String tciDefaultStartDate) {
        this.tciDefaultStartDate = tciDefaultStartDate;
    }

    public String getVciDefaultStartDate() {
        return vciDefaultStartDate;
    }

    public void setVciDefaultStartDate(String vciDefaultStartDate) {
        this.vciDefaultStartDate = vciDefaultStartDate;
    }

    public CustomerDataDto getOwnerData() {
        return ownerData;
    }

    public void setOwnerData(CustomerDataDto ownerData) {
        this.ownerData = ownerData;
    }

    public CustomerDataDto getInsurantData() {
        return insurantData;
    }

    public void setInsurantData(CustomerDataDto insurantData) {
        this.insurantData = insurantData;
    }

    public CustomerDataDto getPolicyHolderData() {
        return policyHolderData;
    }

    public void setPolicyHolderData(CustomerDataDto policyHolderData) {
        this.policyHolderData = policyHolderData;
    }
}
