package com.zhlh.zeus.exception;

import com.zhlh.zeus.business.remexerror.InsurerErr;
import com.zhlh.zeus.business.remexerror.InsurerErrManager;
import com.zhlh.zeus.dto.BaseResDto;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/22 09:33
 * @comment 异常转译
 */
public class ExceptionHelper {
    public static void transErrorMsg(Exception e, BaseResDto resDto) {

        if (e instanceof InvokeServiceException) {

            // SOA调用异常
            resDto.setErrCode(ErrorCode.SOA_ERROR);
            resDto.setErrMsg(ErrorCode.SOA_ERROR_MSG);

        } else if (e instanceof ServiceException) {

            // Zeus层异常
            ServiceException se = (ServiceException) e;
            resDto.setErrCode(se.getCode());
            resDto.setErrMsg(se.getMessage());

        } else if (e instanceof RemexServiceException) {

            // remex层业务异常转译
            RemexServiceException rse = (RemexServiceException) e;
            transError(rse, resDto);

        } else {

            // 系统异常
            resDto.setErrCode(ErrorCode.SYS_ERROR);
            resDto.setErrMsg(ErrorCode.SYS_ERROR_MSG);
        }
    }

    // TODO
    private static void transError(RemexServiceException e, BaseResDto resDto) {

        for (Map.Entry<String,ArrayList<InsurerErr>> entry : InsurerErrManager.insurerErrMap.entrySet()){
            for (InsurerErr insurerErr : entry.getValue()){
                if(insurerErr.errorMsg.contains(e.getMessage())){
                    resDto.setErrCode(Integer.parseInt(insurerErr.changeCode));
                    resDto.setErrMsg(insurerErr.changeErrMsg);
                    return;
                }

            }
        }
        resDto.setErrCode(ErrorCode.SYS_ERROR);
        resDto.setErrMsg(e.getCode() + e.getMessage());
    }

}
