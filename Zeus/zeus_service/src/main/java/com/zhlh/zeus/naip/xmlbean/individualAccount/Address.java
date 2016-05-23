package com.zhlh.zeus.naip.xmlbean.individualAccount;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * cpic请求报文体中的节点
 * 
 * @author LiuZhaohong liuzh@qqbx.com.cn
 * @version v1.0 2015-11-2
 * 
 */
@XStreamAlias("address")
public class Address {

	private String objectname; // 开户机构
	private String accounttype; // 账户类型

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

}
