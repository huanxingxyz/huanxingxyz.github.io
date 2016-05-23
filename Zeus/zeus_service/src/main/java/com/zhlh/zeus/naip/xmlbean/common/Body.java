package com.zhlh.zeus.naip.xmlbean.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhlh.zeus.naip.xmlbean.individualAccount.ReqCpicInsureconfirmEntity;
import com.zhlh.zeus.naip.xmlbean.individualAccount.ResCpicInsureconfirmEntity;

import java.io.Serializable;

/**
 * cpic报文体body
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-10-31
 * 
 */
@XStreamAlias("body")
public class Body implements Serializable{

	private static final long serialVersionUID = 8477219249439908905L;
	@XStreamAlias("entity")
	private ReqCpicInsureconfirmEntity reqCpicInsureconfirmEntity;
	@XStreamAlias("entity")
	private ResCpicInsureconfirmEntity resCpicInsureconfirmEntity;

	public ReqCpicInsureconfirmEntity getReqCpicInsureconfirmEntity() {
		return reqCpicInsureconfirmEntity;
	}

	public void setReqCpicInsureconfirmEntity(ReqCpicInsureconfirmEntity reqCpicInsureconfirmEntity) {
		this.reqCpicInsureconfirmEntity = reqCpicInsureconfirmEntity;
	}

	public ResCpicInsureconfirmEntity getResCpicInsureconfirmEntity() {
		return resCpicInsureconfirmEntity;
	}

	public void setResCpicInsureconfirmEntity(ResCpicInsureconfirmEntity resCpicInsureconfirmEntity) {
		this.resCpicInsureconfirmEntity = resCpicInsureconfirmEntity;
	}
}
