package com.zhlh.zeus.business.bo.quote;


import com.zhlh.zeus.business.idcard.IdCardManager;
import com.zhlh.zeus.util.Util;
import zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCustomerInfo;


public class CustomerBo {

    /**
     * 以下信息必传，如果是车主，手机号非必传
     */
    public String personType; // 关系人类型 0车主 1被保人 2投保人
    public String name; // 姓名
    public String certNo; // 证件号码;
    public String mobile; // 手机号;

    /**
     * 目前用默认值
     */
    public String personClass = "0"; // 客户类别 0自然人 1机关 2企业
    public String certType = "01"; // 证件类型: 01身份证 02户口本 03 。。。。。详见CBS
    public String customerType = "1"; // 客户类型: 1个人 2单位客户
    public String vehicleRelation = "1"; // 车辆所有关系: 1所有 2使用 3管理


    public CustomerBo() {
    }

    public ReqCustomerInfo getReqCustomerForQuote() {
        ReqCustomerInfo info = new ReqCustomerInfo();
        info.setPersonType(personType);
        info.setPersonClass(personClass);
        info.setName(name);
        info.setCertType(certType);
        info.setCertNo(certNo);
        info.setCustomerType(customerType);
        info.setVehicleRelation(vehicleRelation);
        info.setMobile(mobile);
        // 18位身份证号的倒数第二位、15位身份证号的倒数第一位,奇数为男,偶数为女。"2"为女,"1"为男
        info.setSex(IdCardManager.getSexFlag(certNo));

        return info;
    }

    // 询价 - 构造默认车主，投保人，被保人信息
    public static ReqCustomerInfo getDefaultReqCustomerForQuote(String personType, String owner,
                                                                String ownerCertNo) {
        ReqCustomerInfo customer = new ReqCustomerInfo();

        customer.setPersonClass("0");
        customer.setCustomerType("1");
        customer.setVehicleRelation("1");
        customer.setCertType("01");
        customer.setPersonType(personType);
        customer.setName(Util.checkValue(owner, "安旭"));
        customer.setCertNo(Util.checkValue(ownerCertNo, "110101198807251534"));
        customer.setSex(IdCardManager.getSexFlag(customer.getCertNo()));

        return customer;
    }

    // 上年止期 - 构造默认车主，投保人，被保人信息
    public static zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqCustomerInfo
    getDefaultReqCustomerForEndDate(String personType, String owner, String ownerCertNo) {
        zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqCustomerInfo customer = new
                zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqCustomerInfo();

        ReqCustomerInfo info = getDefaultReqCustomerForQuote(personType, owner, ownerCertNo);
        Util.copy(info, customer);

        return customer;
    }

}
