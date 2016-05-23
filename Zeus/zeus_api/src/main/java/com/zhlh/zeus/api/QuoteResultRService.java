package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.quoteresult.QuoteResultReqDto;
import com.zhlh.zeus.dto.quoteresult.QuoteResultResDto;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/12
 */
public interface QuoteResultRService {

    QuoteResultResDto quoteResult(QuoteResultReqDto quoteResultReqDto);
}
