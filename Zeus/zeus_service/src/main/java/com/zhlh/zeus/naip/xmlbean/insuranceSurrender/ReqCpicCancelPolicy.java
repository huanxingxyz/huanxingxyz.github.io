package com.zhlh.zeus.naip.xmlbean.insuranceSurrender;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.common.ReqHead;

/**
 * @author wangjiadong
 * @creatTime 2016-05-06
 * @version
 * @comment 撤单请求报文
 */
@XStreamAlias("request")
public class ReqCpicCancelPolicy {
    @XStreamAlias("head")
    private ReqHead head; // 报文头
    @XStreamAlias("body")
    private ReqCpicCancelBody body; // 报文体

    public ReqHead getHead() {
        return head;
    }

    public void setHead(ReqHead head) {
        this.head = head;
    }

    public ReqCpicCancelBody getBody() {
        return body;
    }

    public void setBody(ReqCpicCancelBody body) {
        this.body = body;
    }
}
