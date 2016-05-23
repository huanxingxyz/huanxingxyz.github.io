package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.quote.QuotePriceReqDto;
import com.zhlh.zeus.dto.quote.QuotePriceResDto;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/3/29 10:03
 * @comment 报价相关接口
 */
public interface QuotePriceRService {

    QuotePriceResDto quotePrice(QuotePriceReqDto quotePriceReqDto);

}
