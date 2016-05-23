package com.zhlh.zeus.dto.insuranceSurrender;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * Created by wjd19 on 2016/5/6.
 */
public class InsuranceSurrenderReqDto extends BaseReqDto {
    private String channel;
    private String appType;
    private String policyNo;//保单号
    private String policyApplyReason;//退保原因说明

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyApplyReason() {
        return policyApplyReason;
    }

    public void setPolicyApplyReason(String policyApplyReason) {
        this.policyApplyReason = policyApplyReason;
    }
}
