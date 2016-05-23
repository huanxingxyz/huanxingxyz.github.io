package com.zhlh.zeus.dto.quote;

import com.zhlh.zeus.dto.BaseReqDto;

import java.io.Serializable;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/9 01:19
 * @comment
 */
public class CustomerDataDto extends BaseReqDto {

    /**
     * 以下信息必传，如果是车主，手机号非必传
     */
    private String personType; // 关系人类型 0车主 1被保人 2投保人
    private String name; // 姓名
    private String certNo; // 证件号码;
    private String mobile; // 手机号;

    /**
     * 目前用默认值
     */
    private String personClass = "0"; // 客户类别 0自然人 1机关 2企业
    private String certType = "01"; // 证件类型: 01身份证 02户口本 03 。。。。。
    private String customerType = "1"; // 客户类型: 1个人 2单位客户
    private String vehicleRelation = "1"; // 车辆所有关系: 1所有 2使用 3管理

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getVehicleRelation() {
        return vehicleRelation;
    }

    public void setVehicleRelation(String vehicleRelation) {
        this.vehicleRelation = vehicleRelation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPersonClass() {
        return personClass;
    }

    public void setPersonClass(String personClass) {
        this.personClass = personClass;
    }
}
