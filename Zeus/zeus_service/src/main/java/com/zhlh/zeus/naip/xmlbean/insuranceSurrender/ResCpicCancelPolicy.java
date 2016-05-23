package com.zhlh.zeus.naip.xmlbean.insuranceSurrender;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.common.ResHead;

/**
 * @author wangjiadong
 * @creatTime 2016-05-06
 * @version
 * @comment 撤单响应报文
 */
@XStreamAlias("response")
public class ResCpicCancelPolicy {
    private ResHead head; // 报文头
    private ResCpicCancelBody body; // 报文体

    public ResHead getHead() {
        return head;
    }

    public void setHead(ResHead head) {
        this.head = head;
    }

    public ResCpicCancelBody getBody() {
        return body;
    }

    public void setBody(ResCpicCancelBody body) {
        this.body = body;
    }
}
