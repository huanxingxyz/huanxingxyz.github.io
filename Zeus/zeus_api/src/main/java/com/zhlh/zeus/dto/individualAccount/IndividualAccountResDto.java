package com.zhlh.zeus.dto.individualAccount;

import com.zhlh.zeus.dto.BaseResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/30
 * @comment 盗刷险返回对象
 */
public class IndividualAccountResDto extends BaseResDto {
    private String policyStatus;//结果代码
    private String policyMsg;//结果信息
    private String proposalNo;//投保单号
    private String policyNo;//保单号
    private String startDate;
    private String endDate;

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getPolicyMsg() {
        return policyMsg;
    }

    public void setPolicyMsg(String policyMsg) {
        this.policyMsg = policyMsg;
    }

    public String getProposalNo() {
        return proposalNo;
    }

    public void setProposalNo(String proposalNo) {
        this.proposalNo = proposalNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
