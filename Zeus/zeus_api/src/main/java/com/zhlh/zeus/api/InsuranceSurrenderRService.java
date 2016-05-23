package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.insuranceSurrender.InsuranceSurrenderReqDto;
import com.zhlh.zeus.dto.insuranceSurrender.InsuranceSurrenderResDto;

/**
 * @author wangjiadong
 * @createTime 20116-05-06
 * @version 1.0
 * @comment 非车撤单接口.
 */
public interface InsuranceSurrenderRService {
    InsuranceSurrenderResDto InsuranceSurrender(InsuranceSurrenderReqDto insuranceSurrenderReqDto);
}
