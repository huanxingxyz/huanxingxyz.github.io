package com.zhlh.zeus.business.bo.vehicle;

import java.util.ArrayList;
import java.util.List;

public class QueryVehicleBo {

    private String insuCom; // 保险公司
    private String licenseNo;  // 车牌号码
    private String frameNo;  // 车架号
    private String engineNo;  // 发动机号
    private String enrollDate; // 初次登记日期
    private String brandName;  // 厂牌型号
    private String owner; // 车主姓名
    private List<VehicleInfoBo> vehicleInfoList = new ArrayList<VehicleInfoBo>();

    // 人保保费计算
    private String uniqueId;

    // 富德 阳光 国寿
    private String flowNo;


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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public List<VehicleInfoBo> getVehicleInfoList() {
        return vehicleInfoList;
    }

    public void setVehicleInfoList(List<VehicleInfoBo> vehicleInfoList) {
        this.vehicleInfoList = vehicleInfoList;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }
}
