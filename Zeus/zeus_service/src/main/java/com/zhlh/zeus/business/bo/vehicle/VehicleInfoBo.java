package com.zhlh.zeus.business.bo.vehicle;

import com.zhlh.zeus.util.Util;
import zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqVehicleInfo;

public class VehicleInfoBo {
    private String insuCom; // 保险公司
    private String licenseNo; // 车牌号码
    private String cityCode; // 投保城市
    private String licenseColor; // 号牌底色
    private String licenseType; // 号牌类型
    private String frameNo; // 车架号
    private String engineNo; // 发动机号
    private String enrollDate; // 初次登记日期
    private String searchSequenceNo; // 车型查询码（北京行业平台生成，报价时必须传）
    private String actualValue; // 车辆实际价值
    private String mdfyPurchasePrice; // 修改后新车购置价
    private String owner; // 车主
    private String ownerCertType; // 证件类型
    private String ownerCertNo; // 证件号码
    private String ownerType; // 车主类型
    private String useType; // 使用性质
    private String runArea; // 行驶区域
    private String rBCode; // 车型代码
    private String brandName; // 厂牌型号
    private String vehicleKind; // 车辆种类
    private String newVehicleFlag; // 新车标志
    private String ecdemicVehicleFlag; // 外地车标志
    private String vehicleType; // 车辆类型代码
    private String vehicleTypeDesc; // 车辆类型描述
    private String bodyColor; // 车身颜色
    private String purchasePrice; // 新车购置价
    private String fuelType; // 燃料种类
    private String vehicleBrand; // 车辆品牌
    private String importFlag; // 产地种类
    private String makeDate; // 出厂日期
    private String manufacturer; // 厂商名称
    private String vehicleSeries; // 车系名称
    private String seatCount; // 核定载客
    private String marketDate; // 年款(上市年份)
    private String vehicleTonnage; // 核定载质量
    private String exhaustCapacity; // 排量
    private String vehicleWeight; // 整备质量
    private String alarmFlag; // 是否有防盗装备
    private String airBagNum; // 安全气囊数
    private String transmissionType; // 变速器形式
    private String absFlag; // 是否有ABS
    private String ineffectualDate; // 校验有效日期止
    private String rejectDate; // 强制有效期止
    private String lastCheckDate; // 最近定检日期
    private String transferDate; // 转移登记日期
    private String platformRbCode; // 平台车型代码
    private String platModelCode; //平台车型代码
    private String platModelName; //平台车型名称
    private String vehicleSeriesCode;//车系编码

    public float getPurchasePriceAsFloat() {
        try {
            return Float.parseFloat(purchasePrice.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public ReqVehicleInfo getRequestVehicleInfoForPolicyEndDateQuery() {
        ReqVehicleInfo request = new ReqVehicleInfo();

        request.setLicenseNo(licenseNo);
        request.setCityCode(cityCode);
        request.setLicenseColor(licenseColor);
        request.setLicenseType(licenseType);
        request.setFrameNo(frameNo);
        request.setEngineNo(engineNo);
        request.setEnrollDate(enrollDate);
        request.setCertDate("");
        request.setSearchSequenceNo(searchSequenceNo);
        request.setActualValue(purchasePrice);
        request.setMdfyPurchasePrice(purchasePrice);
        request.setOwner(owner);
        request.setRunArea("02");
        request.setRunMile("20000");
        request.setSpecialModelFlag("0");
        request.setTransferFlag("0");
        request.setInvoiceDate("");
        request.setRBCode(rBCode);
        request.setBrandName(brandName);
        request.setVehicleKind(vehicleKind);
        request.setNewVehicleFlag(newVehicleFlag);
        request.setEcdemicVehicleFlag(ecdemicVehicleFlag);
        request.setVehicleType(vehicleType);
        request.setVehicleTypeDesc(vehicleTypeDesc);
        request.setPurchasePrice(purchasePrice);
        request.setVehicleBrand(vehicleBrand);
        request.setImportFlag(importFlag);
        request.setVehicleTonnage(vehicleTonnage);
        request.setExhaustCapacity(exhaustCapacity);
        request.setVehicleWeight(vehicleWeight);
        request.setAlarmFlag(alarmFlag);
        request.setAirBagNum(airBagNum);
        request.setTransmissionType(transmissionType);

        request.setVehicleSeries(vehicleSeries);
        request.setVehicleSeriesCode(vehicleSeriesCode);
        request.setPlatModelCode(platModelCode);
        request.setPlatModelName(platModelName);


        String buff;

        buff = fuelType;
        if (buff == null || buff.length() <= 0) {
            buff = Util.isBeijingVehicle(licenseNo) ? "A" : "0";
        }
        request.setFuelType(buff);

        buff = useType;
        if (buff == null || buff.length() <= 0) {
            buff = "8A";
        }
        request.setUseType(buff);

        buff = seatCount;
        int seatCount = 5;
        try {
            seatCount = Integer.parseInt(buff);
        } catch (NumberFormatException e) {
        }
        if (seatCount <= 0) {
            seatCount = 5;
        }
        request.setSeatCount("" + seatCount);

        buff = bodyColor;
        if (buff == null || buff.length() <= 0) {
            request.setBodyColor("03");
        } else {
            request.setBodyColor(buff);
        }

        request.setOwnerCertType("");
        request.setAlarmFlag("");
        request.setMakeDate("");

        return request;
    }

    public zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqVehicleInfo
    getReqVehicleInfo4Quoteprice() {
        zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqVehicleInfo reqVehicleInfo = new zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqVehicleInfo();

        reqVehicleInfo.setLicenseNo(Util.checkValue(licenseNo, ""));
        reqVehicleInfo.setLicenseColor(Util.checkValue(licenseColor, "01"));
        reqVehicleInfo.setLicenseType(Util.checkValue(licenseType, "02"));
        reqVehicleInfo.setFrameNo(Util.checkValue(frameNo, ""));
        reqVehicleInfo.setEngineNo(Util.checkValue(engineNo, ""));
        reqVehicleInfo.setEnrollDate(Util.checkValue(enrollDate, "2011-09-26"));
        reqVehicleInfo.setCertDate("");
        reqVehicleInfo.setSearchSequenceNo(Util.checkValue(searchSequenceNo,
                "39YDPIC2150000000000013865441I"));

        if (Util.isEmpty(actualValue) || actualValue.startsWith("0")) {
            // 此处应该终止流程 TODO
            reqVehicleInfo.setActualValue("100000");
        } else {
            reqVehicleInfo.setActualValue(actualValue);
        }
        reqVehicleInfo.setMdfyPurchasePrice(Util.checkValue(mdfyPurchasePrice, "100000"));
        reqVehicleInfo.setOwner(Util.checkValue(owner, "安旭"));
        reqVehicleInfo.setOwnerCertType(Util.checkValue(ownerCertType, "01"));
        reqVehicleInfo.setOwnerCertNo(Util.checkValue(ownerCertNo, "110101198807251534"));
        reqVehicleInfo.setUseType(Util.checkValue(useType, "8A"));
        reqVehicleInfo.setRunArea(Util.checkValue(runArea, "02"));
        reqVehicleInfo.setRunMile("20000");
        reqVehicleInfo.setTotalRunMile("15000");
        reqVehicleInfo.setSpecialModelFlag("0");
        reqVehicleInfo.setInvoiceDate("");
        reqVehicleInfo.setVehiOriginCertType("01");
        reqVehicleInfo.setVehiOriginCertNo("");
        reqVehicleInfo.setVehiOriginCertDate("");
        reqVehicleInfo.setRBCode(Util.checkValue(rBCode, "BZAAED0066"));
        reqVehicleInfo.setBrandName(Util.checkValue(brandName, ""));
        reqVehicleInfo.setVehicleKind(Util.checkValue(vehicleKind, "A0"));
        reqVehicleInfo.setNewVehicleFlag("0");
        reqVehicleInfo.setEcdemicVehicleFlag(Util.checkValue(ecdemicVehicleFlag, "0"));
        reqVehicleInfo.setTransferFlag(Util.checkValue("0", "0"));
        reqVehicleInfo.setVehicleType("K33");
        reqVehicleInfo.setVehicleTypeDesc(Util.checkValue(vehicleTypeDesc, ""));
        reqVehicleInfo.setBodyColor(Util.checkValue(bodyColor, "07"));
        reqVehicleInfo.setPurchasePrice(Util.checkValue(purchasePrice, ""));
        reqVehicleInfo.setFuelType(Util.checkValue(fuelType, Util.isBeijingVehicle(licenseNo) ? "A" : "0"));
        reqVehicleInfo.setVehicleBrand(Util.checkValue(vehicleBrand, ""));
        reqVehicleInfo.setImportFlag(Util.checkValue(importFlag,
                frameNo.startsWith("L") ? "B" : "A")); //是否是国产车
        reqVehicleInfo.setMakeDate(Util.checkValue(makeDate, ""));
        reqVehicleInfo.setVehicleSeries(Util.checkValue(vehicleSeries, ""));
        reqVehicleInfo.setSeatCount(Util.checkValue(seatCount, "5"));
        reqVehicleInfo.setMarketDate(Util.checkValue(makeDate, "200801"));
        reqVehicleInfo.setVehicleTonnage(Util.checkValue(vehicleTonnage, "0"));

        //
        reqVehicleInfo.setExhaustCapacity(Util.checkValue(exhaustCapacity, "0"));
        reqVehicleInfo.setVehicleWeight(Util.checkValue(vehicleWeight, "1200"));
        reqVehicleInfo.setAlarmFlag(Util.checkValue(alarmFlag, "1"));
        reqVehicleInfo.setAirBagNum(Util.checkValue(airBagNum, "0"));
        reqVehicleInfo.setTransmissionType(Util.checkValue(transmissionType, "手动挡"));
        reqVehicleInfo.setPlatformRbCode(Util.checkValue(platformRbCode, ""));
        reqVehicleInfo.setVehicleSeriesCode(vehicleSeriesCode);
        reqVehicleInfo.setPlatModelCode(platModelCode);
        reqVehicleInfo.setPlatModelName(platModelName);

        return reqVehicleInfo;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLicenseColor() {
        return licenseColor;
    }

    public void setLicenseColor(String licenseColor) {
        this.licenseColor = licenseColor;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
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

    public String getSearchSequenceNo() {
        return searchSequenceNo;
    }

    public void setSearchSequenceNo(String searchSequenceNo) {
        this.searchSequenceNo = searchSequenceNo;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getMdfyPurchasePrice() {
        return mdfyPurchasePrice;
    }

    public void setMdfyPurchasePrice(String mdfyPurchasePrice) {
        this.mdfyPurchasePrice = mdfyPurchasePrice;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerCertType() {
        return ownerCertType;
    }

    public void setOwnerCertType(String ownerCertType) {
        this.ownerCertType = ownerCertType;
    }

    public String getOwnerCertNo() {
        return ownerCertNo;
    }

    public void setOwnerCertNo(String ownerCertNo) {
        this.ownerCertNo = ownerCertNo;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getRunArea() {
        return runArea;
    }

    public void setRunArea(String runArea) {
        this.runArea = runArea;
    }

    public String getrBCode() {
        return rBCode;
    }

    public void setrBCode(String rBCode) {
        this.rBCode = rBCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getVehicleKind() {
        return vehicleKind;
    }

    public void setVehicleKind(String vehicleKind) {
        this.vehicleKind = vehicleKind;
    }

    public String getNewVehicleFlag() {
        return newVehicleFlag;
    }

    public void setNewVehicleFlag(String newVehicleFlag) {
        this.newVehicleFlag = newVehicleFlag;
    }

    public String getEcdemicVehicleFlag() {
        return ecdemicVehicleFlag;
    }

    public void setEcdemicVehicleFlag(String ecdemicVehicleFlag) {
        this.ecdemicVehicleFlag = ecdemicVehicleFlag;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeDesc() {
        return vehicleTypeDesc;
    }

    public void setVehicleTypeDesc(String vehicleTypeDesc) {
        this.vehicleTypeDesc = vehicleTypeDesc;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getImportFlag() {
        return importFlag;
    }

    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(String vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(String marketDate) {
        this.marketDate = marketDate;
    }

    public String getVehicleTonnage() {
        return vehicleTonnage;
    }

    public void setVehicleTonnage(String vehicleTonnage) {
        this.vehicleTonnage = vehicleTonnage;
    }

    public String getExhaustCapacity() {
        return exhaustCapacity;
    }

    public void setExhaustCapacity(String exhaustCapacity) {
        this.exhaustCapacity = exhaustCapacity;
    }

    public String getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(String vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public String getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(String alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public String getAirBagNum() {
        return airBagNum;
    }

    public void setAirBagNum(String airBagNum) {
        this.airBagNum = airBagNum;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getAbsFlag() {
        return absFlag;
    }

    public void setAbsFlag(String absFlag) {
        this.absFlag = absFlag;
    }

    public String getIneffectualDate() {
        return ineffectualDate;
    }

    public void setIneffectualDate(String ineffectualDate) {
        this.ineffectualDate = ineffectualDate;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(String lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getPlatformRbCode() {
        return platformRbCode;
    }

    public void setPlatformRbCode(String platformRbCode) {
        this.platformRbCode = platformRbCode;
    }

    public String getPlatModelCode() {
        return platModelCode;
    }

    public void setPlatModelCode(String platModelCode) {
        this.platModelCode = platModelCode;
    }

    public String getPlatModelName() {
        return platModelName;
    }

    public void setPlatModelName(String platModelName) {
        this.platModelName = platModelName;
    }

    public String getVehicleSeriesCode() {
        return vehicleSeriesCode;
    }

    public void setVehicleSeriesCode(String vehicleSeriesCode) {
        this.vehicleSeriesCode = vehicleSeriesCode;
    }
}
