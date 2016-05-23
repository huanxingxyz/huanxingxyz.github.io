package com.zhlh.zeus.service;

import com.zhlh.Tiny.util.CommonUtil;
import com.zhlh.Tiny.util.RegexUtil;
import com.zhlh.zeus.api.QuotePriceRService;
import com.zhlh.zeus.business.bo.quote.CoverageBo;
import com.zhlh.zeus.business.bo.quote.CustomerBo;
import com.zhlh.zeus.business.bo.quote.QuoteDataBo;
import com.zhlh.zeus.business.bo.vehicle.QueryVehicleBo;
import com.zhlh.zeus.business.bo.vehicle.VehicleInfoBo;
import com.zhlh.zeus.business.citycode.CityCodeManager;
import com.zhlh.zeus.business.puw.PUWManager;
import com.zhlh.zeus.constant.ParamsConstants;
import com.zhlh.zeus.dto.quote.CoverageDataDto;
import com.zhlh.zeus.dto.quote.CustomerDataDto;
import com.zhlh.zeus.dto.quote.QuotePriceReqDto;
import com.zhlh.zeus.dto.quote.QuotePriceResDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleReqDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.exception.ExceptionHelper;
import com.zhlh.zeus.rservice.VehicleRemoteService;
import com.zhlh.zeus.util.Profile;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 10:41
 * @comment 询价接口
 */

@Service("quotePriceRService")
public class QuotePriceRServiceImpl implements QuotePriceRService {

    @Override
    public QuotePriceResDto quotePrice(QuotePriceReqDto quotePriceReqDto) {

        QuotePriceResDto quotePriceResDto = new QuotePriceResDto();

        // 参数校验
        if (!isValidQuotaPriceReqDto(quotePriceReqDto)) {
            quotePriceResDto.setErrCode(ErrorCode.PARAM_ERROR);
            quotePriceResDto.setErrMsg(ErrorCode.PARAM_ERROR_MSG);

            return quotePriceResDto;
        }

        int isInsureConfirm = quotePriceReqDto.getIsInsureConfirm();

        String channel = quotePriceReqDto.getChannel();
        // TODO 渠道是否开通相应地区的保险公司(应用层处理，平台层也处理)

        QueryVehicleReqDto queryVehicleReqDto = new QueryVehicleReqDto();
        Util.copy(quotePriceReqDto, queryVehicleReqDto);

        try {
            // 1.从行业平台查询车辆全量信息
            QueryVehicleBo queryVehicleBo = VehicleRemoteService.queryInsurerVehicleInfo(queryVehicleReqDto);

            // 根据应用端新车购置价确定唯一车辆
            String purchasePrice = quotePriceReqDto.getPurchasePrice();
            List<VehicleInfoBo> vehicleInfoBoList = queryVehicleBo.getVehicleInfoList();
            VehicleInfoBo vehicleInfoBo = getVehicleByPurchasePrice(vehicleInfoBoList, purchasePrice);
            if (vehicleInfoBo == null) {
                quotePriceResDto.setErrCode(ErrorCode.VEHICLE_ERROR);
                quotePriceResDto.setErrMsg(ErrorCode.VEHICLE_ERROR_MSG);

                return quotePriceResDto;
            }

            QuoteDataBo quoteDataBo = new QuoteDataBo();
            Util.copy(vehicleInfoBo, quoteDataBo);
            quoteDataBo.setVehicleInfo(vehicleInfoBo);
            quoteDataBo.setFlowNo(queryVehicleBo.getFlowNo());
            quoteDataBo.setUniqueId(queryVehicleBo.getUniqueId());

            // 核保前报价，将车主，投保人，被保人信息,起保日期，实际价值赋值
            if (isInsureConfirm == 1) {
                CustomerBo ownerData = new CustomerBo();
                Util.copy(quotePriceReqDto.getOwnerData(), ownerData);
                quoteDataBo.setOwnerData(ownerData);

                CustomerBo insurantData = new CustomerBo();
                Util.copy(quotePriceReqDto.getInsurantData(), insurantData);
                quoteDataBo.setInsurantData(insurantData);

                CustomerBo policyHolderData = new CustomerBo();
                Util.copy(quotePriceReqDto.getPolicyHolderData(), policyHolderData);
                quoteDataBo.setPolicyHolderData(policyHolderData);

                quoteDataBo.setTciStartDate(quotePriceReqDto.getTciStartDate());
                quoteDataBo.setTciDefaultStartDate(quotePriceReqDto.getTciDefaultStartDate());
                quoteDataBo.setVciStartDate(quotePriceReqDto.getVciStartDate());
                quoteDataBo.setVciDefaultStartDate(quotePriceReqDto.getVciDefaultStartDate());

                quoteDataBo.setActualValue(quotePriceReqDto.getActualValue());
            }

            // 非核保前报价
            if (isInsureConfirm == 0) {
                // 2.查询上止日期，有可能查不到，默认第二天。随报价一起返回。
                queryPolicyEndDate(quotePriceReqDto, quoteDataBo);

                // 3.查询实际价值（部分保险公司接口未开， 目前全部自己算）
                QuoteDataBo actualValueData = VehicleRemoteService.queryActualValue(channel, quoteDataBo);
                String actualValue = actualValueData.getActualValue();
                if (CommonUtil.isEmpty(actualValue) || !RegexUtil.checkDecimals(actualValue) ||
                        new BigDecimal(actualValue).doubleValue() <= 0) {

                    quotePriceResDto.setErrCode(ErrorCode.ACTUALVALUE_ERROR);
                    quotePriceResDto.setErrMsg(ErrorCode.ACTUALVALUE_ERROR_MSG);

                    return quotePriceResDto;

                }

                quoteDataBo.setActualValue(actualValueData.getActualValue());

                // 车损险，车辆信息中的实际价值不对，会报错，修复之
                quoteDataBo.getVehicleInfo().setActualValue(actualValueData.getActualValue());
            }

            // 4.构造险种条款
            List<CoverageDataDto> coverageDataDtoList = quotePriceReqDto.getCoverageDataDtoList();
            quoteDataBo.setCoverageList(toCoverageData(coverageDataDtoList, quoteDataBo));

            // 5.报价前数据校验

            // TODO 做法太low,后期优化
            // 预核保时可能会null起保时间，先留存
            String tciStartDate = quoteDataBo.getTciStartDate();
            String vciStartDate = quoteDataBo.getVciStartDate();

            // 设置交强险起保日期
            if (quoteDataBo.isChoiseTCI()) {
                if (quoteDataBo.getTciStartDate() == null) {
                    quoteDataBo.setTciStartDate(quoteDataBo.getTciDefaultStartDate());
                    tciStartDate = quoteDataBo.getTciDefaultStartDate();
                }

            } else {
                quoteDataBo.setTciStartDate(null);
            }

            // 设置商业险起保日期
            String defaultVciDate = quoteDataBo.getVciDefaultStartDate(); // 系统获取的起保日期
            String userVciDate = quoteDataBo.getVciStartDate(); // 用户定义的起保日期
            if (quoteDataBo.isChoiseVCI()) {
                if (CommonUtil.isEmpty(userVciDate)) {
                    quoteDataBo.setVciStartDate(defaultVciDate);
                    vciStartDate = defaultVciDate;
                    int days = Util.distanceToToday(defaultVciDate);

                    // TODO 阳光的起保日期不能重叠，处理一下。后面放到预核保里。
                    if (Profile.INSURERCODE_YANGGUANG.equals(quoteDataBo.getInsuCom()) &&
                            days > 90) {
                        quotePriceResDto.setErrCode(ErrorCode.INVALID_YG_VCI_STARTDATE);
                        quotePriceResDto.setErrMsg(ErrorCode.INVALID_YG_VCI_STARTDATE_MSG);

                        return quotePriceResDto;
                    }

                    if (days > 90) {
                        quoteDataBo.setVciStartDate(Util.getTomorrow());
                        vciStartDate = Util.getTomorrow();
                    }

                } else {
                    int days = Util.distanceToToday(userVciDate);
                    if (days > 90) {
                        quotePriceResDto.setErrCode(ErrorCode.INVALID_VCI_STARTDATE);
                        quotePriceResDto.setErrMsg(ErrorCode.INVALID_VCI_STARTDATE_MSG);

                        return quotePriceResDto;
                    }
                }
            } else {
                quoteDataBo.setVciStartDate(null);
            }

            // 预核保
            PUWManager.compute(quoteDataBo);

            boolean isAvailableForQuote = quoteDataBo.isAvailableForQuote();
            if (!isAvailableForQuote) {
                quotePriceResDto.setErrCode(ErrorCode.INVALID_POLICY);
                quotePriceResDto.setErrMsg(ErrorCode.INVALID_POLICY_MSG);
                ofQuotePriceResDto(quoteDataBo, quotePriceResDto);

                return quotePriceResDto;
            }

            // 将未通过预核保的险种取出来一起返回
            List<CoverageBo> unAproveList = new ArrayList<CoverageBo>();
            for (CoverageBo one : quoteDataBo.getCoverageList()) {
                if (!one.isApprove()) {
                    unAproveList.add(one);
                }
            }

            // 6.请求最终报价
            quoteDataBo = VehicleRemoteService.queryQuotePrice(channel, quoteDataBo);

            if (CommonUtil.isNotEmpty(unAproveList)) {
                quoteDataBo.getCoverageList().addAll(unAproveList);
            }

            ofQuotePriceResDto(quoteDataBo, quotePriceResDto);

            quotePriceResDto.setOwner(quoteDataBo.getVehicleInfo().getOwner());
            quotePriceResDto.setVciStartDate(vciStartDate);
            quotePriceResDto.setTciStartDate(tciStartDate);

        } catch (Exception e) {
            ExceptionHelper.transErrorMsg(e, quotePriceResDto);
            return quotePriceResDto;
        }

        return quotePriceResDto;
    }

    /**
     * 询价结果返回数据格式化
     */
    private void ofQuotePriceResDto(QuoteDataBo quoteDataBo, QuotePriceResDto quotePriceResDto) {
        Util.copy(quoteDataBo, quotePriceResDto);
        quotePriceResDto.setCoverageList(new ArrayList<CoverageDataDto>());
        for (CoverageBo coverageBo : quoteDataBo.getCoverageList()) {
            CoverageDataDto coverageDataDto = new CoverageDataDto();
            Util.copy(coverageBo, coverageDataDto);
            quotePriceResDto.getCoverageList().add(coverageDataDto);
        }
    }

    /**
     * 查询上止日期，有可能查不到，默认第二天。
     */
    private void queryPolicyEndDate(QuotePriceReqDto quotePriceReqDto,
                                    QuoteDataBo quoteDataBo) throws Exception {

        // 前面逻辑能保证vehicleInfoBo != null
        VehicleInfoBo vehicleInfoBo = quoteDataBo.getVehicleInfo();
        String flowNo = quoteDataBo.getFlowNo();

        QuoteDataBo policyEndDateData = VehicleRemoteService.queryPolicyEndDate(quotePriceReqDto
                .getChannel(), vehicleInfoBo, flowNo);

        // 将应用端传过来的起保日期赋值，可能为空
        quoteDataBo.setTciStartDate(quotePriceReqDto.getTciStartDate());
        quoteDataBo.setVciStartDate(quotePriceReqDto.getVciStartDate());

        quoteDataBo.setTciDefaultStartDate(getStartDate(policyEndDateData.getTciDefaultStartDate()));
        quoteDataBo.setVciDefaultStartDate(getStartDate(policyEndDateData.getVciDefaultStartDate()));

    }

    /**
     * 根据新车购置价，比较最接近的车辆
     */
    private VehicleInfoBo getVehicleByPurchasePrice(List<VehicleInfoBo> vehicleInfoBoList,
                                                    String purchasePrice) {

        if (CommonUtil.isEmpty(vehicleInfoBoList)) {
            return null;
        }

        float basePrice = -1L;
        try {
            basePrice = Float.parseFloat(purchasePrice.trim());
        } catch (Exception e) {
            basePrice = 0;
        }

        if (basePrice <= 0) {
            return vehicleInfoBoList.get(0);
        }

        // 按照用户提供的新车购置价格，比较最接近的车型
        VehicleInfoBo selected = null;
        float minDis = Float.MAX_VALUE;

        for (VehicleInfoBo vehicleInfoBo : vehicleInfoBoList) {
            float dis = Math.abs(basePrice - vehicleInfoBo.getPurchasePriceAsFloat());

            if (dis < minDis) {
                selected = vehicleInfoBo;
                minDis = dis;
            }
        }

        return selected;
    }

    /**
     * 商改地区车损险保额为实际价值，非商改地区车损险保额为购置价。盗抢险，自燃险保额均为实际价值。Zeus层处理。
     * 三者，司机，乘客，划痕，由应用端传保额。
     * coverageCode // 险种代码，应用层传值
     * amount // 保额 amount = unitAmount * quantity
     * quantity // 数量。司机，乘客责任险需要，具体值由Zeus根据座位数计算
     * unitAmount // 单位保额。司机，乘客责任险需要，应用端传值
     * modelCode // 险种模式代码： 玻璃险 1:国产，2:进口。应用端传值
     * isDeductibleChoice // 不计免赔，是否选择。应用端传值。若选择不计免赔险，则所有商业险此属性都为选中。
     * kindCode // TCI/VCI Zeus层自己处理
     * <p/>
     * <p/>
     * 构造险种，计算相应保额
     */
    private List<CoverageBo> toCoverageData(List<CoverageDataDto> coverageDataDtoList,
                                            QuoteDataBo quoteDataBo) {

        // 是否商改地区
        String cityCode = CityCodeManager.getCityCodeByLicense(quoteDataBo.getLicenseNo());
        boolean isCRCity = Util.isCRCity(cityCode);

        String chenSunAmount = quoteDataBo.getActualValue();
        if (!isCRCity) {
            chenSunAmount = quoteDataBo.getPurchasePrice();
        }


        int seatCount = stringToInt(quoteDataBo.getVehicleInfo().getSeatCount());
        String seatCountStr = "";
        if (seatCount <= 0) {
            seatCount = 4;
            seatCountStr = ParamsConstants.SEATCOUNT_CK;
        } else {
            seatCount = seatCount - 1;
            seatCountStr = String.valueOf(seatCount);
        }

        List<CoverageBo> vector = new ArrayList<CoverageBo>();

        for (CoverageDataDto coverageDto : coverageDataDtoList) {
            CoverageBo data = new CoverageBo();
            String coverageCode = coverageDto.getCoverageCode();

            data.setKindCode(Profile.COVERAGEKIND_VCI); // 商业险
            data.setCoverageCode(coverageDto.getCoverageCode());
            data.setIsDeductibleChoice(coverageDto.getIsDeductibleChoice());

            switch (coverageCode) {

                case Profile.COVERAGE_JIAOQIANG: // 交强险：交强险
                    data.setKindCode(Profile.COVERAGEKIND_TCI);
                    break;

                case Profile.COVERAGE_CHESUN: // 车损险：机动车损失险
                    data.setAmount(chenSunAmount);
                    break;

                case Profile.COVERAGE_SANZHE: // 三者险：机动车第三者责任保险
                    data.setAmount(coverageDto.getAmount());
                    break;

                case Profile.COVERAGE_DAOQIANG: // 盗抢险：机动车盗抢险,
                    data.setAmount(quoteDataBo.getActualValue()); // 自动设置：实际价值
                    break;

                case Profile.COVERAGE_SIJI: // 司机责任险：机动车车上人员责任险(司机)
                    data.setQuantity(ParamsConstants.SEATCOUNT_SJ);
                    data.setUnitAmount(coverageDto.getUnitAmount());
                    data.setAmount(coverageDto.getUnitAmount());
                    break;

                case Profile.COVERAGE_CHENGKE: // 乘客责任险：机动车车上人员责任险(乘客)
                    data.setQuantity(seatCountStr);
                    data.setUnitAmount(coverageDto.getUnitAmount());
                    int unitAmountCK = stringToInt(coverageDto.getUnitAmount());
                    data.setAmount(String.valueOf(seatCount * unitAmountCK));
                    break;

                case Profile.COVERAGE_BOLI: // 玻璃险：玻璃单独破碎险
                    data.setModelCode(coverageDto.getModelCode());
                    break;

                case Profile.COVERAGE_HUAHENG: // 划痕险：车身划痕险
                    data.setAmount(coverageDto.getAmount());
                    break;
                case Profile.COVERAGE_ZIRAN: // 自燃险：自燃损失险
                    data.setAmount(quoteDataBo.getActualValue()); // 自动设置：实际价值
                    break;

                case Profile.COVERAGE_FADONGJI: // 发动机涉水险：发动机特别损失险
                    break;

                case Profile.COVERAGE_BUJIMIANPEI: // 不计免赔：不计免赔率特约条款
                    data.setIsDeductibleChoice(1);
                    break;

            }
            vector.add(data);
        }

        return vector;
    }

    // TODO 放入工具类
    private int stringToInt(String str) {

        int intValue;
        try {
            if(str.indexOf(".") > 0){
                str = str.substring(0, str.indexOf("."));
            }
            intValue = Integer.parseInt(str);
        } catch (Exception e) {
            intValue = 0;
        }

        return intValue;
    }


    /**
     * TODO 放入工具类
     * 计算默认起保日期
     * 1.如果查询到的上止日期不为空，则加一天
     * 2.若果为空，或者为当天以前日期，则设为第二天
     */
    private String getStartDate(String startDate) {
        if (startDate == null) {
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            startDate = Util.dateToString(date);
        } else {
            Date date = Util.stringToDate(startDate);
            if (date == null) {
                date = new Date();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            startDate = Util.dateToString(date);
        }

        if (Util.isTodayOrBefore(startDate)) {
            startDate = Util.getTomorrow();
        }
        return startDate;
    }

    /**
     * 参数校验
     */
    private boolean isValidQuotaPriceReqDto(QuotePriceReqDto quotePriceReqDto) {

        if (quotePriceReqDto == null ||
                CommonUtil.isEmpty(quotePriceReqDto.getLicenseNo()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getInsuCom()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getChannel()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getEngineNo()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getFrameNo()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getBrandName()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getEnrollDate()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getOwner()) ||
                CommonUtil.isEmpty(quotePriceReqDto.getCoverageDataDtoList())) {

            return false;
        } else {
            if (!Util.isBeijingVehicle(quotePriceReqDto.getLicenseNo())) { // 非北京地区
                if (CommonUtil.isEmpty(quotePriceReqDto.getPurchasePrice())) {
                    return false;
                }

            }
        }

        // 核保前报价
        if (quotePriceReqDto.getIsInsureConfirm() != null &&
                quotePriceReqDto.getIsInsureConfirm() == 1) {
            if (CommonUtil.isEmpty(quotePriceReqDto.getActualValue()) ||
                    CommonUtil.isEmpty(quotePriceReqDto.getTciDefaultStartDate()) ||
                    CommonUtil.isEmpty(quotePriceReqDto.getVciDefaultStartDate()) ||
                    quotePriceReqDto.getInsurantData() == null ||
                    quotePriceReqDto.getOwnerData() == null ||
                    quotePriceReqDto.getPolicyHolderData() == null) {

                return false;
            }

            // 车主手机号不能为空，举少不给传，这里给个被保人的 TODO 放在这里不合适
            if (quotePriceReqDto.getInsurantData() != null &&
                    quotePriceReqDto.getOwnerData() != null) {

                String insuMobile = quotePriceReqDto.getInsurantData().getMobile();
                quotePriceReqDto.getOwnerData().setMobile(insuMobile);
            }

            boolean isValidInsurant = isValidCustomer(quotePriceReqDto.getInsurantData());
            boolean isValidHolder = isValidCustomer(quotePriceReqDto.getPolicyHolderData());
            boolean isValidOwner = isValidCustomer(quotePriceReqDto.getOwnerData());

            return isValidHolder && isValidInsurant && isValidOwner;
        }

        return true;
    }

    private boolean isValidCustomer(CustomerDataDto customerDataDto) {

        if (customerDataDto == null ||
                CommonUtil.isEmpty(customerDataDto.getCertNo()) ||
                CommonUtil.isEmpty(customerDataDto.getName()) ||
                CommonUtil.isEmpty(customerDataDto.getPersonType()) ||
                CommonUtil.isEmpty(customerDataDto.getMobile())) {
            return false;
        }

        return true;

    }
}

