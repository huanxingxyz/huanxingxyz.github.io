package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.pay.LuoberPayReqDto;
import com.zhlh.zeus.dto.pay.LuoberPayResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/23
 * @comment 核保接口
 */
public interface LuobarPayRService {
    LuoberPayResDto luoberPay(LuoberPayReqDto luoberPayReqDto);
}
