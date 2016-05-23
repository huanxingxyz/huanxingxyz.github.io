package com.zhlh.zeus.dto.insureCity;

import java.io.Serializable;

/**
 * Created by MT on 16/5/9.
 */
public class InsurerCity implements Serializable{
    private String cityCode;//城市代码
    private String cityName;//城市名称
    private String shortLicenseNo;//车牌号前缀
    private String insuCom;//保险公司代码
    private String type;//类型

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getShortLicenseNo() {
        return shortLicenseNo;
    }

    public void setShortLicenseNo(String shortLicenseNo) {
        this.shortLicenseNo = shortLicenseNo;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
