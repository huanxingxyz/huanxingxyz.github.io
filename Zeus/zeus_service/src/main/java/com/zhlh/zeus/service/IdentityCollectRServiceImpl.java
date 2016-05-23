package com.zhlh.zeus.service;

import cn.remex.reflect.ReflectUtil;
import com.zhlh.Tiny.util.CommonUtil;
import com.zhlh.Tiny.util.RegexUtil;
import com.zhlh.zeus.api.IdentityCollectRService;
import com.zhlh.zeus.business.citycode.CityCodeManager;
import com.zhlh.zeus.business.idcard.IdCard;
import com.zhlh.zeus.constant.ApiConstants;
import com.zhlh.zeus.constant.RemexConstants;
import com.zhlh.zeus.dto.identityCollect.IdentityCollectReqDto;
import com.zhlh.zeus.dto.identityCollect.IdentityCollectResDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.framework.soa.SoaService;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;
import zhlh.anbox.aitp.aiws.xmlbeans.identify.ReqCustomerInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.identify.ReqIdentityCollection;
import zhlh.anbox.aitp.aiws.xmlbeans.identify.ResIdentityCollection;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/12
 * @comment 身份采集
 */
@Service("identityCollectRService")
public class IdentityCollectRServiceImpl implements IdentityCollectRService, ApiConstants, RemexConstants {
    @Override
    public IdentityCollectResDto identityCollect(IdentityCollectReqDto idCollet) {

        ReqIdentityCollection reqIdentityCollection = new ReqIdentityCollection();
        IdentityCollectResDto resDto = new IdentityCollectResDto();

        String channel = idCollet.getChannel();
        String insuCom = idCollet.getInsuCom();
        String licenseNo = idCollet.getLicenseNo();
        String cityCode = CityCodeManager.getCityCodeByLicense(licenseNo);
        String flow = idCollet.getFlowNo();
        String certNo = idCollet.getCertNo();

        String errorMsg = RegexUtil.checkIDCard(certNo);

        if (Util.isEmpty(channel)) {
            resDto.setErrCode(ErrorCode.PARAM_ERROR);
            resDto.setErrMsg("渠道代码不能为空");
            return resDto;
        }
        if (Util.isEmpty(insuCom)) {
            resDto.setErrCode(ErrorCode.PARAM_ERROR);
            resDto.setErrMsg("保险公司代码不能为空");
            return resDto;
        }
        if (INSURECODESTRING.indexOf(insuCom) < -1) {
            resDto.setErrCode(ErrorCode.PARAM_ERROR);
            resDto.setErrMsg("未找到有效的保险公司代码");
            return resDto;
        }
        //投保人、被保人、车主身份证验证
        for (int i = 0; i < errMsgs.length; i++) {
            if (CommonUtil.isNotEmpty(errorMsg)) {
                resDto.setErrCode(ErrorCode.PARAM_ERROR);
                resDto.setErrMsg("投保人或被保人身份证号码不能为空!" );
                return resDto;
            }else{
                if (Util.isSame(errMsgs[i], errorMsg) ) {
                    resDto.setErrCode(ErrorCode.PARAM_ERROR);
                    resDto.setErrMsg("投保人或被保人" + errorMsg );
                    return resDto;
                }
            }
        }

        if (Util.isEmpty(cityCode)) {
            resDto.setErrCode(ErrorCode.PARAM_ERROR);
            resDto.setErrMsg("城市代码不能为空");
            return resDto;
        }
        if (!Util.isSame(cityCode, CITYCODE_BJ)) {
            resDto.setErrCode(ErrorCode.PARAM_ERROR);
            resDto.setErrMsg("非北京地区不用身份采集!");
            return resDto;
        }

        Util.copy(idCollet, reqIdentityCollection);
        List<ReqCustomerInfo> customerInfos = new ArrayList<ReqCustomerInfo>();
        try {
            getCustinerInfos(idCollet, customerInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqIdentityCollection.setCustomerInfos(customerInfos);
        reqIdentityCollection.setCityCode(cityCode);
        try {
            ResIdentityCollection resDto1 = (ResIdentityCollection) SoaService.invokeService(channel, AITP_IDENTITY_COLLECTBS, TRANSTYPE_IDENTITYCOLLECT, reqIdentityCollection, flow);
        } catch (Exception e) {
            String message = Util.transErrorMsg(e.getMessage());
            resDto.setErrMsg(message);
            resDto.setErrCode(ErrorCode.PARAM_ERROR);
            return resDto;
        }
        return resDto;
    }

    /**
     * 构造投保人、被保人、车主信息
     *
     * @param idCollet
     * @param customerInfos
     * @return
     */
    private static List<ReqCustomerInfo> getCustinerInfos(IdentityCollectReqDto idCollet,
                                                          List<ReqCustomerInfo> customerInfos) throws Exception{
        String certNo = idCollet.getCertNo();//投保人身份证号码

        IdCard idCard = new IdCard(certNo);

        //投保人被保人
        ReqCustomerInfo holder = new ReqCustomerInfo();
        holder.setPersonType(PERSONTYPE_INSURER);//被保人
        holder.setName(idCollet.getName());
        String[] dates = idCard.guessPeriodOfValidity();
        holder = constructCustomer(holder, dates, certNo,idCard);
        holder.setMobile(idCollet.getMobile());
        customerInfos.add(holder);

        ReqCustomerInfo insure = new ReqCustomerInfo();
        insure.setPersonType(PERSONTYPE_HOLDER);//投保人
        insure.setName(idCollet.getName());
        insure.setMobile(idCollet.getMobile());
        insure.setSex(Util.getGender(certNo));
        insure = constructCustomer(insure, dates, certNo,idCard);
        customerInfos.add(insure);

        return customerInfos;
    }

    /**
     * 构造投保人、被保人信息
     * @param customerInfo
     * @param dates
     * @param certNo
     * @return
     */
    static ReqCustomerInfo constructCustomer(ReqCustomerInfo customerInfo, String[] dates,
                                             String certNo,IdCard idCard) throws  Exception{
        if(certNo.length() == 15){
            String tempCertNo = idCard.getEighteenIDCard(certNo);
            customerInfo.setBirthday(Util.getBirthday(tempCertNo));
        }
        customerInfo.setCertStartDate(dates[0]);
        customerInfo.setCertEndDate(dates[1]);
        customerInfo.setCertType(CERTTYPE_ID);
        customerInfo.setPersonClass(PERSONCLASS_PERSON);
        customerInfo.setEthnic(ETHNIC_HAN);//民族
        customerInfo.setCertNo(certNo);
        customerInfo.setBirthday(Util.getBirthday(certNo));
        customerInfo.setSex(Util.getGender(certNo));//车主性别
        customerInfo.setAddress(ADDRESS_BJ);
        Hashtable<String, String> areaMap;
        areaMap = Util.GetAreaCode();
        String area = areaMap.get(certNo.substring(0, 2));
        customerInfo.setIssuer(area + POLICE);

        return customerInfo;
    }

}
