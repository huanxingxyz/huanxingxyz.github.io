package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.identityCollect.IdentityCollectReqDto;
import com.zhlh.zeus.dto.identityCollect.IdentityCollectResDto;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/12
 * @comment 身份采集
 */
public interface IdentityCollectRService {
    IdentityCollectResDto identityCollect(IdentityCollectReqDto idCollet);
}
