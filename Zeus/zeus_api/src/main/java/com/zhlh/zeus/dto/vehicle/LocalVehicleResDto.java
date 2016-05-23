package com.zhlh.zeus.dto.vehicle;

import com.zhlh.zeus.dto.BaseResDto;

import java.io.Serializable;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 16:12
 * @comment
 */
public class LocalVehicleResDto extends BaseResDto {

    private String licenseNo; // 车牌号
    private String frameNo; // 车架号
    private String engineNo; // 发动机号
    private String enrollDate; // 车辆初始登记日期，非北京地区
    private String brandName; // 车型代码: 速腾FV7186TG（行驶证上），非北京地区

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
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
}
