package com.zhlh.zeus.naip.xmlbean.insuranceSurrender;

import com.zhlh.zeus.naip.xmlbean.common.Entity;

/**
 * @author wangjiadong
 * @creatTime 2016-05-06
 * @version
 * @comment 撤单entity对象
 */
public class ResCpicCancelPolicyEntity extends Entity {
    private String plcBusinessNo; // 业务号
    private String plcApplyNo; // 投保单号
    private String plcNo; // 保单号
    private String endorseApplNo; // 批单申请号
    private String  endorseNo; // 批单号
    private String plcStatus; // 保批单状态
    private String plcEffctDate; // 批单生效日期
    private String plcSumInsured; // 含附加险保额
    public String getPlcBusinessNo() {
        return plcBusinessNo;
    }

    public void setPlcBusinessNo(String plcBusinessNo) {
        this.plcBusinessNo = plcBusinessNo;
    }

    public String getPlcApplyNo() {
        return plcApplyNo;
    }

    public void setPlcApplyNo(String plcApplyNo) {
        this.plcApplyNo = plcApplyNo;
    }

    public String getPlcNo() {
        return plcNo;
    }

    public void setPlcNo(String plcNo) {
        this.plcNo = plcNo;
    }

    public String getEndorseApplNo() {
        return endorseApplNo;
    }

    public void setEndorseApplNo(String endorseApplNo) {
        this.endorseApplNo = endorseApplNo;
    }

    public String getEndorseNo() {
        return endorseNo;
    }

    public void setEndorseNo(String endorseNo) {
        this.endorseNo = endorseNo;
    }

    public String getPlcStatus() {
        return plcStatus;
    }

    public void setPlcStatus(String plcStatus) {
        this.plcStatus = plcStatus;
    }

    public String getPlcEffctDate() {
        return plcEffctDate;
    }

    public void setPlcEffctDate(String plcEffctDate) {
        this.plcEffctDate = plcEffctDate;
    }

    public String getPlcSumInsured() {
        return plcSumInsured;
    }

    public void setPlcSumInsured(String plcSumInsured) {
        this.plcSumInsured = plcSumInsured;
    }
}
