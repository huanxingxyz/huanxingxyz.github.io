package com.zhlh.zeus.naip.service;

import cn.remex.util.XmlHelper;
import com.zhlh.Tiny.util.BeanUtil;
import com.zhlh.zeus.api.InsuranceSurrenderRService;
import com.zhlh.zeus.constant.NaipConstants;
import com.zhlh.zeus.dto.insuranceSurrender.InsuranceSurrenderReqDto;
import com.zhlh.zeus.dto.insuranceSurrender.InsuranceSurrenderResDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.naip.util.Client;
import com.zhlh.zeus.naip.util.NaipUtils;
import com.zhlh.zeus.naip.xmlbean.common.ReqHead;
import com.zhlh.zeus.naip.xmlbean.insuranceSurrender.*;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;

/**
 * @author wangjiadong
 * @createTime 20116-05-06
 * @version 1.0
 * @comment 非车撤单接口.
 */
@Service("insuranceSurrenderRService")
public class InsuranceSurrenderRServiceImpl implements InsuranceSurrenderRService,
                                                          ErrorCode,NaipConstants {

    @Override
    public InsuranceSurrenderResDto InsuranceSurrender(InsuranceSurrenderReqDto insuranceSurrenderReqDto) {
        InsuranceSurrenderResDto insuranceSurrenderResDto = new InsuranceSurrenderResDto();
        ReqCpicCancelPolicy reqCpicCancelPolicy = new ReqCpicCancelPolicy();
        ReqCpicCancelPolicyEntity entity = new ReqCpicCancelPolicyEntity();
        ReqHead head = new ReqHead();
        ReqCpicCancelBody body = new ReqCpicCancelBody();

        String channel = insuranceSurrenderReqDto.getChannel();
        String appType = insuranceSurrenderReqDto.getAppType();
        String policyNo = insuranceSurrenderReqDto.getPolicyNo();
        String transNo = Util.createTransNo(INDIVIDUAL_ACCOUNT_LOSS_INSU_RSERVICE, InsuranceSurrenderRServiceImpl.class);

        if(Util.isEmpty(insuranceSurrenderReqDto)){
            insuranceSurrenderResDto.setErrCode(PARAM_ERROR);
            insuranceSurrenderResDto.setErrMsg(PARAM_ERROR_MSG);
            return insuranceSurrenderResDto;
        }
        if(Util.isEmpty(channel)){
            insuranceSurrenderResDto.setErrCode(PARAM_ERROR);
            insuranceSurrenderResDto.setErrMsg("渠道参数不能为空");
            return insuranceSurrenderResDto;
        }
        if(Util.isEmpty(channel)){
            insuranceSurrenderResDto.setErrCode(PARAM_ERROR);
            insuranceSurrenderResDto.setErrMsg("应用类型参数不能为空");
            return insuranceSurrenderResDto;
        }
        if(Util.isEmpty(policyNo)){
            insuranceSurrenderResDto.setErrCode(PARAM_ERROR);
            insuranceSurrenderResDto.setErrMsg("保单号不能为空");
            return insuranceSurrenderResDto;
        }
        head = NaipUtils.getReqHead(head,InsuranceSurrenderRServiceImpl.class);
        entity = getEntity(entity,policyNo);
        body.setReqCpicCancelPolicyEntity(entity);
        reqCpicCancelPolicy.setHead(head);
        reqCpicCancelPolicy.setBody(body);
        //对象序列化
        String cancelPolicyXml = XmlHelper.marshall(reqCpicCancelPolicy, UTF8);
        //保存报文
        Util.saveXMLFile(REQUEST,transNo,cancelPolicyXml,UTF8);
        //或许响应报文
        String resCancelPolicyXml = Client.sentHttpPostRequest(cancelPolicyXml);
        Util.saveXMLFile(RESPONSE,transNo,resCancelPolicyXml,UTF8);
        ResCpicCancelPolicy resCpicCancelPolicy = XmlHelper.unmarshall(ResCpicCancelPolicy.class,UTF8);
        String resultCode = resCpicCancelPolicy.getHead().getResponseCompleteMessageStatus().getMessageStatusCode();
        if (Util.isSame(resultCode,RESULT_SUCCESS)) {
            insuranceSurrenderResDto = getInsuranceSurrenderResDto(insuranceSurrenderResDto,resCpicCancelPolicy.getBody().getResCpicCancelPolicyEntity());
            insuranceSurrenderResDto.setPlcStatus(PSYC);
        }else{
            insuranceSurrenderResDto.setErrCode(4000);
            insuranceSurrenderResDto.setErrMsg(resCpicCancelPolicy.getHead().getResponseCompleteMessageStatus().getMessageStatusDescription().get(0).getMessageStatusSubDescription());
        }
        return insuranceSurrenderResDto;
    }

    /**
     * 获取退保返回对象
     * @param insuranceSurrenderResDto
     * @param resCpicCancelPolicyEntity
     * @return
     */
    private InsuranceSurrenderResDto getInsuranceSurrenderResDto(InsuranceSurrenderResDto insuranceSurrenderResDto, ResCpicCancelPolicyEntity resCpicCancelPolicyEntity) {
        if (Util.isEmpty(resCpicCancelPolicyEntity)){
            BeanUtil.quickCopy(resCpicCancelPolicyEntity, insuranceSurrenderResDto);
        }
        return insuranceSurrenderResDto;
    }

    /**
     * 构造请求entity对象
     * @param entity
     * @param policyNo
     * @return
     */
    private ReqCpicCancelPolicyEntity getEntity(ReqCpicCancelPolicyEntity entity,
                                                String policyNo) {
        entity.setPlcBusinessNo(BUSINESS_CODE_PREFIX + Util.createTransNo(TRANSNO_PREFIX, InsuranceSurrenderRServiceImpl.class));
        entity.setPlcTerminalNo(TERMINALNO);
        entity.setPlcNo(policyNo);
        entity.setPlcApplyReason("");
        return entity;
    }
}
