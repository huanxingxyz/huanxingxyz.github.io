package com.zhlh.zeus.dto.vehicle;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/11 22:26
 * @comment 车辆类型描述信息
 */
public class VehicleDesInfoDto extends BaseReqDto {

    private String marketDate; // 出厂日期
    private String purchasePrice; // 新车购置价
    private String vehicleTypeDesc; // 车辆类型描述
    private String exhaustCapacity; // 排量
    private String rBCode; // 车型代码


    public String getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(String marketDate) {
        this.marketDate = marketDate;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getVehicleTypeDesc() {
        return vehicleTypeDesc;
    }

    public void setVehicleTypeDesc(String vehicleTypeDesc) {
        this.vehicleTypeDesc = vehicleTypeDesc;
    }

    public String getExhaustCapacity() {
        return exhaustCapacity;
    }

    public void setExhaustCapacity(String exhaustCapacity) {
        this.exhaustCapacity = exhaustCapacity;
    }

    public String getrBCode() {
        return rBCode;
    }

    public void setrBCode(String rBCode) {
        this.rBCode = rBCode;
    }
}
