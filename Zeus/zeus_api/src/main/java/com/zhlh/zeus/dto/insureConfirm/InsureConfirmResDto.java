package com.zhlh.zeus.dto.insureConfirm;

import com.zhlh.zeus.dto.BaseResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/19
 * @comment 核保 返回参数
 */
public class InsureConfirmResDto extends BaseResDto {
    private String innerPlcyNo; // 内部保单号
    private String insuCom; // 投保公司
    private String insureSuccessFlag; // 投保确认成功标识
    private String insureFailReason; // 投保确认失败信息
    private String vciProposalNo; // 商业险投保单号
    private String tciProposalNo; // 交强险投保单号
    private String tciUdwrtFlag;  // 交强险核保标志
    private String tciUdwrtMessage; // 交强险核保信息
    private String vciUdwrtFlag; // 商业险核保标志
    private String vciUdwrtMessage; // 商业险核保信息

    public String getInnerPlcyNo() {
        return innerPlcyNo;
    }

    public void setInnerPlcyNo(String innerPlcyNo) {
        this.innerPlcyNo = innerPlcyNo;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getInsureSuccessFlag() {
        return insureSuccessFlag;
    }

    public void setInsureSuccessFlag(String insureSuccessFlag) {
        this.insureSuccessFlag = insureSuccessFlag;
    }

    public String getInsureFailReason() {
        return insureFailReason;
    }

    public void setInsureFailReason(String insureFailReason) {
        this.insureFailReason = insureFailReason;
    }

    public String getVciProposalNo() {
        return vciProposalNo;
    }

    public void setVciProposalNo(String vciProposalNo) {
        this.vciProposalNo = vciProposalNo;
    }

    public String getTciProposalNo() {
        return tciProposalNo;
    }

    public void setTciProposalNo(String tciProposalNo) {
        this.tciProposalNo = tciProposalNo;
    }

    public String getTciUdwrtFlag() {
        return tciUdwrtFlag;
    }

    public void setTciUdwrtFlag(String tciUdwrtFlag) {
        this.tciUdwrtFlag = tciUdwrtFlag;
    }

    public String getTciUdwrtMessage() {
        return tciUdwrtMessage;
    }

    public void setTciUdwrtMessage(String tciUdwrtMessage) {
        this.tciUdwrtMessage = tciUdwrtMessage;
    }

    public String getVciUdwrtFlag() {
        return vciUdwrtFlag;
    }

    public void setVciUdwrtFlag(String vciUdwrtFlag) {
        this.vciUdwrtFlag = vciUdwrtFlag;
    }

    public String getVciUdwrtMessage() {
        return vciUdwrtMessage;
    }

    public void setVciUdwrtMessage(String vciUdwrtMessage) {
        this.vciUdwrtMessage = vciUdwrtMessage;
    }
}
