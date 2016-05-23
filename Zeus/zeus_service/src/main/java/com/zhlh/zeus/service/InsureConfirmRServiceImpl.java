package com.zhlh.zeus.service;

import cn.remex.reflect.ReflectUtil;
import com.zhlh.zeus.api.InsureConfirmRService;
import com.zhlh.zeus.business.citycode.CityCodeManager;
import com.zhlh.zeus.constant.ApiConstants;
import com.zhlh.zeus.constant.RemexConstants;
import com.zhlh.zeus.dto.insureConfirm.InsureConfirmReqDto;
import com.zhlh.zeus.dto.insureConfirm.InsureConfirmResDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.framework.soa.SoaService;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;
import zhlh.anbox.aitp.aiws.xmlbeans.insuconfirm.ReqInsureConfirmInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.insuconfirm.ResInsureConfirmInfo;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/21
 * @comment 核保
 */
@Service("insureConfirmRService")
public class InsureConfirmRServiceImpl implements InsureConfirmRService, ApiConstants, RemexConstants {
    @Override
    public InsureConfirmResDto insureConfirm(InsureConfirmReqDto insureConfirmReqDto) {
        String channel = insureConfirmReqDto.getChannel();
        String insuCom = insureConfirmReqDto.getInsuCom();
        String flow = insureConfirmReqDto.getFlowNo();
        String verificationCode = insureConfirmReqDto.getVerificationCode();
        String licenseNo = insureConfirmReqDto.getLicenseNo();
        String cityCode = CityCodeManager.getCityCodeByLicense(licenseNo);

        ReqInsureConfirmInfo reqInsureConfirmInfo = new ReqInsureConfirmInfo();
        InsureConfirmResDto resInCon = new InsureConfirmResDto();

        if (Util.isEmpty(channel)) {
            resInCon.setErrCode(ErrorCode.PARAM_ERROR);
            resInCon.setErrMsg("渠道代码不能为空");
            return resInCon;
        }
        if (Util.isEmpty(insuCom)) {
            resInCon.setErrCode(ErrorCode.PARAM_ERROR);
            resInCon.setErrMsg("保险公司代码不能为空");
            return resInCon;
        }
        if (INSURECODESTRING.indexOf(insuCom) < -1) {
            resInCon.setErrCode(ErrorCode.PARAM_ERROR);
            resInCon.setErrMsg("未找到有效的保险公司代码");
            return resInCon;
        }
        if (Util.isSame(cityCode, CITYCODE_BJ) && Util.isEmpty(verificationCode)) {
            resInCon.setErrCode(ErrorCode.PARAM_ERROR);
            resInCon.setErrMsg("北京地区手机验证码不允许为空");
            return resInCon;
        }
        ReflectUtil.copyProperties(reqInsureConfirmInfo, insureConfirmReqDto);
        reqInsureConfirmInfo = getPosterInfo(reqInsureConfirmInfo);
        reqInsureConfirmInfo.setCityCode(cityCode);
        try {
            ResInsureConfirmInfo resInfo = (ResInsureConfirmInfo) SoaService.invokeService(channel, AITP_INSURE_CONFIRMBS,
                    TRANSTYPE_BEGININSURE, reqInsureConfirmInfo, flow);
            ReflectUtil.copyProperties(resInCon, resInfo);
        } catch (Exception e) {
            String message = Util.transErrorMsg(e.getMessage());
            resInCon.setErrMsg(message);
            resInCon.setErrCode(ErrorCode.PARAM_ERROR);
            return resInCon;
        }

        return resInCon;
    }

    private static ReqInsureConfirmInfo getPosterInfo(ReqInsureConfirmInfo reqInsureConfirmInfo) {
        reqInsureConfirmInfo.setPostTel(reqInsureConfirmInfo.getPostMobile());
        reqInsureConfirmInfo.setPostEmail(EMAIL);
        reqInsureConfirmInfo.setPostCode(POSTCODE);

        return reqInsureConfirmInfo;
    }


}
