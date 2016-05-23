package com.zhlh.zeus.naip.service;

import cn.remex.util.XmlHelper;
import com.zhlh.Tiny.util.CommonUtil;
import com.zhlh.Tiny.util.RegexUtil;
import com.zhlh.zeus.api.IndividualAccountLossInsuRService;
import com.zhlh.zeus.constant.ApiConstants;
import com.zhlh.zeus.constant.NaipConstants;
import com.zhlh.zeus.constant.RemexConstants;
import com.zhlh.zeus.dto.individualAccount.IndividualAccountReqDto;
import com.zhlh.zeus.dto.individualAccount.IndividualAccountResDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.naip.util.Client;
import com.zhlh.zeus.naip.util.NaipUtils;
import com.zhlh.zeus.naip.xmlbean.common.Body;
import com.zhlh.zeus.naip.xmlbean.common.ReqHead;
import com.zhlh.zeus.naip.xmlbean.individualAccount.*;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/30
 * @comment 盗刷险
 */
@Service("individualAccountLossInsuRService")
public class IndividualAccountLossInsuRServiceImpl implements IndividualAccountLossInsuRService, ApiConstants, RemexConstants, ErrorCode, NaipConstants {
    @Override
    public IndividualAccountResDto individualAccountLossInsurance(IndividualAccountReqDto individualAccountReqDto) {
        IndividualAccountResDto individualAccountResDto = new IndividualAccountResDto();
        ReqCpicInsureConfirm reqCpicInsureConfirm = new ReqCpicInsureConfirm();
        Applicant applicant = new Applicant();
        ReqHead head = new ReqHead();
        Insured insured = new Insured();
        ElcPolicy elcPolicy = new ElcPolicy();
        PlcBase base = new PlcBase();
        Body body = new Body();
        ReqCpicInsureconfirmEntity entity = new ReqCpicInsureconfirmEntity();

        String channel = individualAccountReqDto.getChannel();
        String appType = individualAccountReqDto.getAppType();
        String transNo = Util.createTransNo(INDIVIDUAL_ACCOUNT_LOSS_INSU_RSERVICE, IndividualAccountLossInsuRServiceImpl.class);
        String insuredCertNo = individualAccountReqDto.getInsuredCertNo();
        String insuredName = individualAccountReqDto.getInsuredName();
        String insuredMobile = individualAccountReqDto.getInsuredMobile();
        int purchaseFlag = individualAccountReqDto.getPurchaseFlag();
        int period = individualAccountReqDto.getPeriod();
        String insureDate = individualAccountReqDto.getInsureDate();
        String amount = individualAccountReqDto.getAmount();
        String premium = individualAccountReqDto.getPremium();
        String startDate = individualAccountReqDto.getStartDate();
        String endDate = individualAccountReqDto.getEndDate();


        if (Util.isEmpty(channel)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("渠道代码不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(appType)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("应用代码不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(insuredName)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("被保人姓名不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(insuredCertNo)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("被保人身份证号码不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(insuredMobile)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("被保人手机号不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(insureDate)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("投保日期不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(insuredMobile)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("被保人手机号不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(amount)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("保额不能为空");
            return individualAccountResDto;
        }
        if (Util.isEmpty(premium)) {
            individualAccountResDto.setErrCode(PARAM_ERROR);
            individualAccountResDto.setErrMsg("保费不能为空");
            return individualAccountResDto;
        }
        if (purchaseFlag == 1) {
            if (Util.isEmpty(startDate)) {
                individualAccountResDto.setErrCode(PARAM_ERROR);
                individualAccountResDto.setErrMsg("投保起期不能为空");
                return individualAccountResDto;
            }
            if (Util.isEmpty(endDate)) {
                individualAccountResDto.setErrCode(PARAM_ERROR);
                individualAccountResDto.setErrMsg("投保止期不能为空");
                return individualAccountResDto;
            }
        }

        String errorMsg = RegexUtil.checkIDCard(insuredCertNo);
        //投保人、被保人、车主身份证验证
        for (int i = 0; i < errMsgs.length; i++) {
            if (CommonUtil.isNotEmpty(errorMsg)) {
                if (Util.isSame(errMsgs[i], errorMsg)) {
                    individualAccountResDto.setErrCode(ErrorCode.PARAM_ERROR);
                    individualAccountResDto.setErrMsg("被保人" + errorMsg);
                    return individualAccountResDto;
                }
            }
        }
        //构造请求head对象
        head = NaipUtils.getReqHead(head, IndividualAccountLossInsuRServiceImpl.class);
        //投保人
        applicant = constructApplicant(applicant);
        insured = getInsured(insured, individualAccountReqDto);
        //验证方式
        elcPolicy = getElcPolicy(elcPolicy, insuredMobile);
        //投保信息基本
        base = getPlcBase(base, individualAccountReqDto);

        entity = getEntity(entity, applicant, elcPolicy, base, insured);

        body.setReqCpicInsureconfirmEntity(entity);
        reqCpicInsureConfirm.setHead(head);
        reqCpicInsureConfirm.setBody(body);
        //对象序列化
        String reqIndividualAccountXml = XmlHelper.marshall(reqCpicInsureConfirm, UTF8);
        //保存报文
        Util.saveXMLFile(REQUEST, transNo, reqIndividualAccountXml, UTF8);
        //或许响应报文
        String resIndividualAccountXml = Client.sentHttpPostRequest(reqIndividualAccountXml);
        Util.saveXMLFile(RESPONSE, transNo, resIndividualAccountXml, UTF8);
        //反序列化
        ResCpicInsureConfirm resCpicInsureConfirm = XmlHelper.unmarshall(ResCpicInsureConfirm.class, resIndividualAccountXml, UTF8);
        String resultCode = resCpicInsureConfirm.getHead().getResponseCompleteMessageStatus().getMessageStatusCode();
        if (Util.isSame(resultCode, RESULT_SUCCESS)) {
            individualAccountResDto.setPolicyStatus(PSAU);
            individualAccountResDto.setPolicyMsg("保单通过审核");
            individualAccountResDto.setProposalNo(resCpicInsureConfirm.getBody().getResCpicInsureconfirmEntity().getPlcApplyNo());
            individualAccountResDto.setPolicyNo(resCpicInsureConfirm.getBody().getResCpicInsureconfirmEntity().getPlcNo());
            individualAccountResDto.setStartDate(base.getPlcStartDate());//返回真是投保日期
            individualAccountResDto.setEndDate(base.getPlcEndDate());//投保止期
        } else {
            individualAccountResDto.setErrCode(4000);
            individualAccountResDto.setErrMsg(resCpicInsureConfirm.getHead().getResponseCompleteMessageStatus().getMessageStatusDescription().get(0).getMessageStatusSubDescription());
        }
        return individualAccountResDto;
    }

    /**
     * 获取被保人对象信息
     *
     * @param insured
     * @param individualAccountReqDto
     * @return
     */
    private Insured getInsured(Insured insured, IndividualAccountReqDto individualAccountReqDto) {
        insured.setIsrdName(individualAccountReqDto.getInsuredName());
        insured.setIsrdCretCode(individualAccountReqDto.getInsuredCertNo());
        insured.setIsrdCretType(INSURED_CERT_TYPE);
        insured.setIsrdTelephone(HOLDER_TEL);
        insured.setIsrdMobile(individualAccountReqDto.getInsuredMobile());
        insured.setIsrdAddress(HOLDER_ADDRESS);
        return insured;
    }

    /**
     * 构造entity对象
     *
     * @param entity
     * @param applicant
     * @param elcPolicy
     * @param base
     * @param insured
     * @return
     */
    private ReqCpicInsureconfirmEntity getEntity(ReqCpicInsureconfirmEntity entity, Applicant applicant, ElcPolicy elcPolicy, PlcBase base, Insured insured) {
        List<Address> addressList = new ArrayList<Address>();
        List<Insured> insuredList = new ArrayList<Insured>();

        insuredList.add(insured);
        entity.setAddressList(addressList);
        entity.setInsuredList(insuredList);
        entity.setApplicant(applicant);
        entity.setElcPolicy(elcPolicy);
        entity.setPlcBase(base);
        return entity;
    }

    /**
     * 构造投保人对象
     *
     * @return applicant
     */
    private Applicant constructApplicant(Applicant applicant) {
        applicant.setApltName(HOLDER_NAME);
        applicant.setApltCretType(HOLDER_CERT_TYPE);
        applicant.setApltCretCode(HOLDER_CERT_CODE);
        applicant.setApltTelephone(HOLDER_TEL);
        applicant.setApltEmail(HOLDER_EMAIL);
        applicant.setApltAddress(HOLDER_ADDRESS);
        return applicant;
    }

    private ElcPolicy getElcPolicy(ElcPolicy elcPolicy, String insureMobile) {
        elcPolicy.setElcMsgFlag(ELC_MSG_FLAG);
        elcPolicy.setElcEmlFlag(ELC_EML_FLAG);
        elcPolicy.setElcMobile(insureMobile);
        elcPolicy.setElcEmail(HOLDER_EMAIL);
        return elcPolicy;
    }


    /**
     * 构造PlcBase对象
     *
     * @param base
     * @param individualAccountReqDto
     * @return
     */
    private PlcBase getPlcBase(PlcBase base, IndividualAccountReqDto individualAccountReqDto) {
        int purchaseFlag = individualAccountReqDto.getPurchaseFlag();
        int period = individualAccountReqDto.getPeriod();

        String insureDate = individualAccountReqDto.getInsureDate();//投保日期
        String startDate = "";
        String endDate = "";

        base.setPlcTerminalNo(TERMINALNO);
        base.setPlcBusinessNo(BUSINESS_CODE_PREFIX + Util.createTransNo(TRANSNO_PREFIX, IndividualAccountLossInsuRServiceImpl.class));
        base.setPlcPlanCode(PLAN_CODE);
        if (purchaseFlag == 0) {// 0 - 赠送
            startDate = Util.obtainTargetDate(insureDate, 0, 4);
            endDate = Util.obtainTargetDate(startDate, period);
            base.setPlcAmount(HANDSEL_AMOUNT);
            if (!Util.isEmpty(startDate)) {
                base.setPlcStartDate(startDate.substring(0, 4) + startDate.substring(5, 7) + startDate.substring(8, 10) + MILLISECOND);
            }
            if (!Util.isEmpty(endDate)) {
                base.setPlcEndDate(endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8, 10) + MILLISECOND);
            }
        } else {
            startDate = individualAccountReqDto.getStartDate();
            endDate = individualAccountReqDto.getEndDate();
            startDate = Util.obtainTargetDate(startDate, 0, 4);
            endDate = Util.obtainTargetDate(endDate, period);
            base.setPlcAmount(individualAccountReqDto.getAmount());
            if (!Util.isEmpty(startDate)) {
                base.setPlcStartDate(startDate.substring(0, 4) + startDate.substring(5, 7) + startDate.substring(8, 10) + MILLISECOND);
            }
            if (!Util.isEmpty(endDate)) {
                base.setPlcEndDate(endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8, 10) + MILLISECOND);
            }
        }
        base.setPlcCopies(PRODUCT_NUMBER);
        base.setPlcElcFlag(PLC_ELC_FLAG);

        return base;
    }
}
