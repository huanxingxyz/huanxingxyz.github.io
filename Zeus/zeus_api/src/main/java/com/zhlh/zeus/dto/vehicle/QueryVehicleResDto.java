package com.zhlh.zeus.dto.vehicle;

import com.zhlh.zeus.dto.BaseResDto;

import java.util.ArrayList;
import java.util.List;

public class QueryVehicleResDto extends BaseResDto {

    private String insuCom; // 保险公司
    private String licenseNo; // 车牌号
    private String frameNo; // 车架号
    private String engineNo; // 发动机号
    private String enrollDate; // 车辆初始登记日期
    private String brandName; // 厂牌型号
    private String owner; // 车主(北京地区可以获取到真实车主姓名)

    // 车辆类型描述信息
    private List<VehicleDesInfoDto> vehicleDesInfoDtoList = new ArrayList<VehicleDesInfoDto>();


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

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<VehicleDesInfoDto> getVehicleDesInfoDtoList() {
        return vehicleDesInfoDtoList;
    }

    public void setVehicleDesInfoDtoList(List<VehicleDesInfoDto> vehicleDesInfoDtoList) {
        this.vehicleDesInfoDtoList = vehicleDesInfoDtoList;
    }
}
