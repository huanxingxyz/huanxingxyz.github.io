package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.insureConfirm.InsureConfirmReqDto;
import com.zhlh.zeus.dto.insureConfirm.InsureConfirmResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/12 14:02
 * @comment 核保接口
 */
public interface InsureConfirmRService {
    InsureConfirmResDto insureConfirm(InsureConfirmReqDto insureConfirmReqDto);
}
