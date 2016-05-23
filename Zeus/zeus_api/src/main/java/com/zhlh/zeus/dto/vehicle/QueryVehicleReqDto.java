package com.zhlh.zeus.dto.vehicle;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 16:12
 * @comment 车辆信息查询 请求参数DTO
 */
public class QueryVehicleReqDto extends BaseReqDto {

    /**
     * 以下字段必传
     */
    private String channel; // 询价来源
    private String licenseNo; // 车牌号
    private String frameNo; // 车架号
    private String engineNo; // 发动机号

    /**
     * 以下字段非北京地区必传
     */
    private String enrollDate; // 车辆初始登记日期，非北京地区必须
    private String brandName; // 厂牌型号，非北京地区必须
    private String owner; // 车主姓名，非北京地区必须

    /**
     * 以下字段选传
     */
    private String insuCom; // 保险公司(统一走英大的接口)


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

}
