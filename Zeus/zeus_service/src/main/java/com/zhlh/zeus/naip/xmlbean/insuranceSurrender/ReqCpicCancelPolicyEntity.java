package com.zhlh.zeus.naip.xmlbean.insuranceSurrender;

import com.zhlh.zeus.naip.xmlbean.common.Entity;

import java.io.Serializable;

/**
 * @author wangjiadong
 * @creatTime 2016-05-06
 * @version
 * @comment 撤单请求entity
 */
public class ReqCpicCancelPolicyEntity extends Entity implements Serializable {
    private static final long serialVersionUID = 6787950437967378251L;
    private String plcTerminalNo; // 业务归属地编码
    private String plcBusinessNo; // 业务号
    private String plcNo; // 保单号
    private String plcApplyReason; // 退保原因说明

    public String getPlcTerminalNo() {
        return plcTerminalNo;
    }

    public void setPlcTerminalNo(String plcTerminalNo) {
        this.plcTerminalNo = plcTerminalNo;
    }

    public String getPlcBusinessNo() {
        return plcBusinessNo;
    }

    public void setPlcBusinessNo(String plcBusinessNo) {
        this.plcBusinessNo = plcBusinessNo;
    }

    public String getPlcNo() {
        return plcNo;
    }

    public void setPlcNo(String plcNo) {
        this.plcNo = plcNo;
    }

    public String getPlcApplyReason() {
        return plcApplyReason;
    }

    public void setPlcApplyReason(String plcApplyReason) {
        this.plcApplyReason = plcApplyReason;
    }
}
