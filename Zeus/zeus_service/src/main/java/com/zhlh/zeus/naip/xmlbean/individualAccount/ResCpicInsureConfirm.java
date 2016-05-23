package com.zhlh.zeus.naip.xmlbean.individualAccount;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.common.Body;
import com.zhlh.zeus.naip.xmlbean.common.ResHead;

/**
 * 中海联合个人账户损失险响应报文
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-10-31
 * 
 */
@XStreamAlias("response")
public class ResCpicInsureConfirm implements Serializable{

	private static final long serialVersionUID = 171145082496272429L;
	private ResHead head; // 报文头
	private Body body; // 报文体


	public ResHead getHead() {
		return head;
	}

	public void setHead(ResHead head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
