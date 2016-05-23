package com.zhlh.zeus.naip.xmlbean.individualAccount;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.common.Body;
import com.zhlh.zeus.naip.xmlbean.common.ReqHead;


/**
 * 中海联合个人账户损失险请求报文
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-10-31
 * 
 */
@XStreamAlias("request")
public class ReqCpicInsureConfirm {
	@XStreamAlias("head")
	private ReqHead head; // 报文头
	@XStreamAlias("body")
	private Body body; // 报文体

	public ReqHead getHead() {
		return head;
	}

	public void setHead(ReqHead head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
