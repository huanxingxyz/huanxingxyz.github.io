package com.zhlh.zeus.naip.xmlbean.insuranceSurrender;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by wjd19 on 2016/5/13.
 */
public class ResCpicCancelBody {
    @XStreamAlias("entity")
    private ResCpicCancelPolicyEntity resCpicCancelPolicyEntity;

    public ResCpicCancelPolicyEntity getResCpicCancelPolicyEntity() {
        return resCpicCancelPolicyEntity;
    }

    public void setResCpicCancelPolicyEntity(ResCpicCancelPolicyEntity resCpicCancelPolicyEntity) {
        this.resCpicCancelPolicyEntity = resCpicCancelPolicyEntity;
    }
}
