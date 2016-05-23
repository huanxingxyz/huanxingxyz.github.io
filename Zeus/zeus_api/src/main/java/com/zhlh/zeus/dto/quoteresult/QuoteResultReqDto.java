package com.zhlh.zeus.dto.quoteresult;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/12
 */
public class QuoteResultReqDto extends BaseReqDto{

    private String quoteNo; // 询价订单号

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

}
