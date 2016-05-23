package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.individualAccount.IndividualAccountReqDto;
import com.zhlh.zeus.dto.individualAccount.IndividualAccountResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/30
 * @comment 盗刷险
 */
public interface IndividualAccountLossInsuRService {
    IndividualAccountResDto individualAccountLossInsurance(IndividualAccountReqDto individualAccountReqDto);
}
