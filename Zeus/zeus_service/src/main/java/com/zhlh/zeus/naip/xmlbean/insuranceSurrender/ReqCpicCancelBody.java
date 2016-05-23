package com.zhlh.zeus.naip.xmlbean.insuranceSurrender;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by wjd19 on 2016/5/13.
 */
@XStreamAlias("body")
public class ReqCpicCancelBody {
    @XStreamAlias("entity")
    private ReqCpicCancelPolicyEntity reqCpicCancelPolicyEntity;

    public ReqCpicCancelPolicyEntity getReqCpicCancelPolicyEntity() {
        return reqCpicCancelPolicyEntity;
    }

    public void setReqCpicCancelPolicyEntity(ReqCpicCancelPolicyEntity reqCpicCancelPolicyEntity) {
        this.reqCpicCancelPolicyEntity = reqCpicCancelPolicyEntity;
    }
}
