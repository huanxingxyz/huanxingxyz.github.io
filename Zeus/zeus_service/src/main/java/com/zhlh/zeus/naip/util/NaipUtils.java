package com.zhlh.zeus.naip.util;

import com.zhlh.Tiny.util.DateUtil;
import com.zhlh.zeus.constant.NaipConstants;
import com.zhlh.zeus.naip.xmlbean.common.ReqHead;
import com.zhlh.zeus.util.Util;

/**
 * Created by wjd19 on 2016/5/6.
 */
public class NaipUtils implements NaipConstants {
    /**
     * 拼装请求头结点
     * @param head
     * @return
     */
    public static ReqHead getReqHead(ReqHead head, Class c){
        head.setPartnerCode(PARTNER_CODE);
        head.setTransactionCode(TRANSACTION_CODE);
        head.setMessageId(MSG_PREFIX+ Util.createTransNo(TRANSNO_PREFIX,c.getClass()));
        head.setTransactionEffectiveDate(Util.getTime(DateUtil.PATTERN_DATE_TIME));
        head.setUser(USER);
        head.setPassword(UAT_HEAD_PWD);
        return  head;
    }
}
