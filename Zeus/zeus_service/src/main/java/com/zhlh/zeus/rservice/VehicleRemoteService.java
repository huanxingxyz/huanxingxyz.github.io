package com.zhlh.zeus.rservice;


import com.zhlh.Tiny.util.CommonUtil;
import com.zhlh.zeus.business.citycode.CityCodeManager;
import com.zhlh.zeus.business.bo.quote.CoverageBo;
import com.zhlh.zeus.business.bo.quote.CustomerBo;
import com.zhlh.zeus.business.bo.quote.QuoteDataBo;
import com.zhlh.zeus.business.bo.vehicle.QueryVehicleBo;
import com.zhlh.zeus.business.bo.vehicle.VehicleInfoBo;
import com.zhlh.zeus.constant.ApiConstants;
import com.zhlh.zeus.constant.ParamsConstants;
import com.zhlh.zeus.constant.RemexConstants;
import com.zhlh.zeus.dto.vehicle.QueryVehicleReqDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.exception.ServiceException;
import com.zhlh.zeus.framework.soa.SoaService;
import com.zhlh.zeus.util.IdGenerator;
import com.zhlh.zeus.util.Util;
import zhlh.anbox.aitp.aiws.xmlbeans.actualvalue.ReqActualValue;
import zhlh.anbox.aitp.aiws.xmlbeans.actualvalue.ResActualValue;
import zhlh.anbox.aitp.aiws.xmlbeans.localvehicles.ReqLocalVehicleQuery;
import zhlh.anbox.aitp.aiws.xmlbeans.localvehicles.ResLocalVehicleQuery;
import zhlh.anbox.aitp.aiws.xmlbeans.localvehicles.ResVehicleInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqCustomerInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqPolicyEndDate;
import zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ResPolicyEndDate;
import zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCoverageInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqQuotePriceInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ResCoverageInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ResQuotePriceInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.vehicle.ReqVehicleInfo;
import zhlh.anbox.aitp.aiws.xmlbeans.vehicle.ResVehicleModelData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 15:51
 * @comment
 */
public class VehicleRemoteService {

    /**
     * 查询本地车辆信息
     *
     * @param channelCode
     * @param licenseNo
     * @return
     */
    public static ResVehicleInfo queryRemexLocalVehicle(String channelCode, String licenseNo) throws Exception {

        ReqLocalVehicleQuery reqLocalVehicleInfo = new ReqLocalVehicleQuery();
        reqLocalVehicleInfo.setLicenseNo(licenseNo);

        ResLocalVehicleQuery rst = (ResLocalVehicleQuery) SoaService.invokeService(
                channelCode, RemexConstants.AITP_LOCALVEHICLES,
                ApiConstants.TRANSTYPE_QUERYLOCALVEHICLES, reqLocalVehicleInfo,
                String.valueOf(System.currentTimeMillis()));

        if (rst != null && rst.getVehicleInfos().size() > 0) {
            ResVehicleInfo info = rst.getVehicleInfos().get(0);
            return info;
        }

        return null;
    }

    /**
     * 通过保险公司，从行业平台查询车辆全量信息。
     * 北京地区：通过licenseNo，frameNo，engineNo查，基本可以唯一确定一辆车
     * 外地：通过brandName查（目前需要用户手动输入，行驶证上有），会返回多条数据，包含购置价格，但不包含以上信息。
     * 通过实际价值，用户选择自己的车辆类型，进而唯一确定一辆车。选择无法做校验。
     *
     * @param queryVehicleReqDto
     * @return
     * @throws ServiceException
     */
    public static QueryVehicleBo queryInsurerVehicleInfo(QueryVehicleReqDto queryVehicleReqDto)
            throws Exception {
        if (queryVehicleReqDto == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, ErrorCode.PARAM_ERROR_MSG);
        }

        // 构造SOA返回参数
        QueryVehicleBo queryVehicleBo = new QueryVehicleBo();

        // 构造SOA请求参数
        ReqVehicleInfo reqVehicleInfo = new ReqVehicleInfo();

        String channel = queryVehicleReqDto.getChannel();
        String insuCom = queryVehicleReqDto.getInsuCom();
        String licenseNo = queryVehicleReqDto.getLicenseNo();
        String engineNo = queryVehicleReqDto.getEngineNo();
        String frameNo = queryVehicleReqDto.getFrameNo();

        String brandName = queryVehicleReqDto.getBrandName();
        String enrollDate = queryVehicleReqDto.getEnrollDate();
        String owner = queryVehicleReqDto.getOwner();

        reqVehicleInfo.setInsuCom(insuCom);
        reqVehicleInfo.setLicenseNo(licenseNo);
        reqVehicleInfo.setEngineNo(engineNo);
        reqVehicleInfo.setFrameNo(frameNo);

        reqVehicleInfo.setBrandName(Util.checkValue(brandName, ""));
        reqVehicleInfo.setEnrollDate(Util.checkValue(enrollDate, ""));
        reqVehicleInfo.setOwner(Util.checkValue(owner, "张玉"));

        // TODO  1.确定哪些参数必传 2.消除硬编码
        reqVehicleInfo.setVehicleBrand(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setRBCode(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setNewVehicleFlag(ParamsConstants.NEWVEHICLEFLAG);
        reqVehicleInfo.setEcdemicVehicleFlag(ParamsConstants.ECDEMICVEHICLEFLAG);
        reqVehicleInfo.setLicenseColor(ParamsConstants.LICENSECOLOR);
        reqVehicleInfo.setLicenseType(ParamsConstants.LICENSETYPE);
        reqVehicleInfo.setUseType(ParamsConstants.USETYPE);
        reqVehicleInfo.setVehicleType(ParamsConstants.VEHICLETYPE);
        reqVehicleInfo.setVehicleTypeDesc(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setVehicleKind(ParamsConstants.VEHICLEKIND);
        reqVehicleInfo.setSeatCount(ParamsConstants.SEATCOUNT);
        reqVehicleInfo.setOwnerCertType(ParamsConstants.OWNERCERTTYPE);
        reqVehicleInfo.setOwnerCertNo(ParamsConstants.OWNERCERTNO);
        reqVehicleInfo.setVehicleTonnage(ParamsConstants.VEHICLETONNAGE);
        reqVehicleInfo.setExhaustCapacity(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setVehicleWeight(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setOwnerMobile(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setVehiOriginCertType(ParamsConstants.VEHIORIGINCERTTYPE);
        reqVehicleInfo.setVehiOriginCertNo(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setVehiOriginCertDate(ParamsConstants.NULLSTRING);
        reqVehicleInfo.setLocalSearchFlag(ParamsConstants.LOCALSEARCHFLAG);
        reqVehicleInfo.setSourceFlag(ParamsConstants.SOURCEFLAG);

        String cityCode = CityCodeManager.getCityCodeByLicense(queryVehicleReqDto.getLicenseNo());
        reqVehicleInfo.setCityCode(cityCode);

        String fuelType = Util.isBeijingVehicle(queryVehicleReqDto.getLicenseNo()) ? "A" : "0";
        reqVehicleInfo.setFuelType(fuelType);

        String flowNo = IdGenerator.generate(24);

        ResVehicleModelData resVehicleModelData = (ResVehicleModelData) SoaService.invokeService(
                channel, RemexConstants.AITP_QUERYVEHICLEINFO,
                ApiConstants.TRANSTYPE_BEGINCARQUERY, reqVehicleInfo, flowNo);

        List<zhlh.anbox.aitp.aiws.xmlbeans.vehicle.ResVehicleInfo> resVehicleInfoList =
                resVehicleModelData.getVehicleInfos();
        if (resVehicleInfoList == null || resVehicleInfoList.size() <= 0) {
            throw new ServiceException(ErrorCode.VEHICLE_ERROR, ErrorCode.VEHICLE_ERROR_MSG);
        }

        queryVehicleBo.setInsuCom(insuCom);
        queryVehicleBo.setFrameNo(frameNo);
        queryVehicleBo.setEngineNo(engineNo);
        queryVehicleBo.setLicenseNo(licenseNo);
        queryVehicleBo.setFlowNo(flowNo);

        if (Util.checkIsRenBao(insuCom)) {
            queryVehicleBo.setUniqueId(resVehicleModelData.getUniqueId());
        }

        for (zhlh.anbox.aitp.aiws.xmlbeans.vehicle.ResVehicleInfo resVehicleInfo : resVehicleInfoList) {
            VehicleInfoBo vehicleInfoBo = new VehicleInfoBo();
            Util.copy(resVehicleInfo, vehicleInfoBo);

            // 返回车辆信息中没有保险公司信息，后续要用，补充之
            vehicleInfoBo.setInsuCom(insuCom);

            // TODO 华安计算实际价值接口返回值与remex计算的实际价值相差较大，后期建议更新算法，或者全部走接口
            // 坑啊，太保不返回使用性质，导致计算实际价值时连环坑，临时修复
            if (CommonUtil.isEmpty(vehicleInfoBo.getUseType())) {
                vehicleInfoBo.setUseType(ParamsConstants.USETYPE);
            }
            queryVehicleBo.getVehicleInfoList().add(vehicleInfoBo);

            queryVehicleBo.setBrandName(vehicleInfoBo.getBrandName());
            queryVehicleBo.setEnrollDate(vehicleInfoBo.getEnrollDate());
        }

        return queryVehicleBo;
    }

    /**
     * 查询上年止期
     *
     * @param channelCode
     * @param vehicleInfoBo
     * @return
     * @throws Exception
     */
    public static QuoteDataBo queryPolicyEndDate(String channelCode, VehicleInfoBo vehicleInfoBo,
                                                 String flowNo)
            throws Exception {

        if (vehicleInfoBo == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, ErrorCode.PARAM_ERROR_MSG);
        }

        // 构造SOA返回参数
        QuoteDataBo response = new QuoteDataBo();

        // 构造SOA请求参数
        ReqPolicyEndDate reqPolicyEndDate = new ReqPolicyEndDate();

        // 1.客户信息
        List<ReqCustomerInfo> reqCustomerInfos = new ArrayList<ReqCustomerInfo>();
        reqCustomerInfos.add(CustomerBo.getDefaultReqCustomerForEndDate(ApiConstants
                .PERSONTYPE_OWNER, vehicleInfoBo.getOwner(), vehicleInfoBo.getOwnerCertNo()));
        reqCustomerInfos.add(CustomerBo.getDefaultReqCustomerForEndDate(ApiConstants
                .PERSONTYPE_INSURER, vehicleInfoBo.getOwner(), vehicleInfoBo.getOwnerCertNo()));
        reqCustomerInfos.add(CustomerBo.getDefaultReqCustomerForEndDate(ApiConstants
                .PERSONTYPE_HOLDER, vehicleInfoBo.getOwner(), vehicleInfoBo.getOwnerCertNo()));

        reqPolicyEndDate.setAtinCustomers(reqCustomerInfos);

        // 2.车辆信息
        zhlh.anbox.aitp.aiws.xmlbeans.policyenddate.ReqVehicleInfo reqVehicleInfo =
                vehicleInfoBo.getRequestVehicleInfoForPolicyEndDateQuery();
        reqPolicyEndDate.setVehicleInfo(reqVehicleInfo);

        // 3.保险公司
        reqPolicyEndDate.setInsuCom(vehicleInfoBo.getInsuCom());
        reqPolicyEndDate.setCityCode(vehicleInfoBo.getCityCode());
        reqPolicyEndDate.setTaxStatus("0");

        ResPolicyEndDate resPolicyEndDate = (ResPolicyEndDate) SoaService.invokeService(
                channelCode, RemexConstants.AITP_QUERYPOLICYENDDATE,
                ApiConstants.TRANSTYPE_QUERYLASTCONTENDDATE, reqPolicyEndDate, flowNo);

        if (resPolicyEndDate != null) {
            response.setTciDefaultStartDate(resPolicyEndDate.getTciLastEndDate()); // 交强险上年止期
            response.setVciDefaultStartDate(resPolicyEndDate.getVciLastEndDate()); // 商业险上年止期
        }

        return response;
    }

    /**
     * 查询车辆实际价值
     *
     * @param channelCode
     * @param request
     * @return
     * @throws Exception
     */
    public static QuoteDataBo queryActualValue(String channelCode, QuoteDataBo request) throws Exception {

        if (request == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, ErrorCode.PARAM_ERROR_MSG);
        }

        // 构造SOA返回参数
        QuoteDataBo response = new QuoteDataBo();

        // 构造SOA请求参数
        ReqActualValue reqActualValue = new ReqActualValue();

        String vciStartDate = Util.checkValue(request.getVciStartDate(),
                request.getVciDefaultStartDate());
        reqActualValue.setVciStartDate(vciStartDate); // 商业险起保日期, 优先用客户选择的日期
        reqActualValue.setInsuCom(request.getInsuCom()); // 保险公司

        VehicleInfoBo requestInfo = request.getVehicleInfo();
        reqActualValue.setCityCode(requestInfo.getCityCode());
        reqActualValue.setPurchasePrice(requestInfo.getPurchasePrice());
        reqActualValue.setEnrollDate(requestInfo.getEnrollDate());
        reqActualValue.setUseType(requestInfo.getUseType());
        reqActualValue.setVehicleKind(requestInfo.getVehicleKind());
        reqActualValue.setSeatCount(requestInfo.getSeatCount());
        reqActualValue.setVehicleTonnage(requestInfo.getVehicleTonnage());

        ResActualValue resActualValue = (ResActualValue) SoaService.invokeService(channelCode,
                RemexConstants.AITP_ACTUALVALUE, ApiConstants.TRANSTYPE_QUERYCARREALPRICE,
                reqActualValue, request.getFlowNo());
        if (resActualValue != null) {
            response.setActualValue(resActualValue.getActualValue());
        }

        return response;
    }

    /**
     * 最终报价
     *
     * @param channelCode
     * @param request
     * @return
     * @throws Exception
     */
    public static QuoteDataBo queryQuotePrice(String channelCode, QuoteDataBo request)
            throws Exception {

        if (request == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, ErrorCode.PARAM_ERROR_MSG);
        }

        // 构造SOA请求参数
        ReqQuotePriceInfo reqQuotePriceInfo = new ReqQuotePriceInfo();

        reqQuotePriceInfo.setInsuCom(request.getInsuCom());

        // 车辆信息
        VehicleInfoBo vehicleInfoBo = request.getVehicleInfo();
        setBasic(request, reqQuotePriceInfo);
        reqQuotePriceInfo.setVehicleInfo(vehicleInfoBo.getReqVehicleInfo4Quoteprice());

        // 条款信息
        reqQuotePriceInfo.setAtinCoverages(getCoverages(request));

        // 客户信息
        reqQuotePriceInfo.setAtinCustomers(getCustomers(request));

        // 人保uniqueId
        if (Util.checkIsRenBao(request.getInsuCom())) {
            reqQuotePriceInfo.setUniqueId(request.getUniqueId());
        }

        ResQuotePriceInfo resQuote = (ResQuotePriceInfo) SoaService.invokeService(channelCode,
                RemexConstants.AITP_QUOTEPRICE, ApiConstants.TRANSTYPE_BEGINQUOTEPRICE,
                reqQuotePriceInfo, request.getFlowNo());

        if (resQuote != null) {
            request.setQuoteNo(resQuote.getQuoteNo());
            request.setInsuCom(resQuote.getInsuCom());
            request.setExecCom(resQuote.getExecCom());
            request.setBenchmarkPremium(resQuote.getBenchmarkPremium());
            request.setPremium(resQuote.getPremium());
            request.setTciPremium(resQuote.getTciPremium());
            request.setVciPremium(resQuote.getVciPremium());
            request.setDiscount(resQuote.getDiscount());
            request.setSumTravelTax(resQuote.getSumTravelTax());
            request.setInsuComQuoteNo(resQuote.getInsuComQuoteNo());

            // 处理险种报价结果
            request.setCoverageList(new ArrayList<CoverageBo>());
            for (ResCoverageInfo coverage : resQuote.getAtinCoverages()) {
                request.getCoverageList().add(new CoverageBo(coverage));
            }
        }

        return request;
    }

    private static void setBasic(QuoteDataBo data, ReqQuotePriceInfo reqQuotePriceInfo) {

        VehicleInfoBo info = data.getVehicleInfo();

        // cityCode 不能为空，且必须跟车牌号匹配。方法调用者保证info不为空且cityCode有值
        reqQuotePriceInfo.setCityCode(info.getCityCode());
        if (data.getTciStartDate() == null) {
            reqQuotePriceInfo.setTciFlag("0");
            reqQuotePriceInfo.setTravelTaxFlag("N");
        } else {
            reqQuotePriceInfo.setTciFlag("1");
            reqQuotePriceInfo.setTravelTaxFlag("Y");
        }
        reqQuotePriceInfo.setTaxStatus("0");

        // 燃料类型，北京为A，外地车为0.目前新能源车接口不支持。
        reqQuotePriceInfo.setFuelType(Util.checkValue(info.getFuelType(), "A"));
        reqQuotePriceInfo.setVehiOriginCertType("");
        reqQuotePriceInfo.setVehiOriginCertNo(""); // 车辆来历凭证编号
        reqQuotePriceInfo.setVehiOriginCertDate("");
        reqQuotePriceInfo.setInsuCom(Util.checkValue(data.getInsuCom(), ""));

        // 已经处理好的起保日期了
        reqQuotePriceInfo.setTciStartDate(data.getTciStartDate());
        reqQuotePriceInfo.setVciStartDate(data.getVciStartDate());
        reqQuotePriceInfo.setAgntComTransNo("" + System.currentTimeMillis());
    }

    private static List<zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCustomerInfo> getCustomers(
            QuoteDataBo request) {

        VehicleInfoBo info = request.getVehicleInfo();

        CustomerBo ownerData = request.getOwnerData();
        CustomerBo insurantData = request.getInsurantData();
        CustomerBo policyHolderData = request.getPolicyHolderData();

        List<zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCustomerInfo> reqCustomerInfos = new
                ArrayList<zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCustomerInfo>();

        if (ownerData == null) {

            reqCustomerInfos.add(CustomerBo.getDefaultReqCustomerForQuote(
                    ApiConstants.PERSONTYPE_OWNER, info.getOwner(), info.getOwnerCertNo()));
        } else {
            reqCustomerInfos.add(ownerData.getReqCustomerForQuote());
        }

        if (insurantData == null) {
            reqCustomerInfos.add(CustomerBo.getDefaultReqCustomerForQuote(
                    ApiConstants.PERSONTYPE_INSURER, info.getOwner(), info.getOwnerCertNo()));

        } else {
            reqCustomerInfos.add(insurantData.getReqCustomerForQuote());
        }

        if (policyHolderData == null) {
            reqCustomerInfos.add(CustomerBo.getDefaultReqCustomerForQuote(
                    ApiConstants.PERSONTYPE_HOLDER, info.getOwner(), info.getOwnerCertNo()));
        } else {
            reqCustomerInfos.add(policyHolderData.getReqCustomerForQuote());
        }

        return reqCustomerInfos;
    }

    private static List<ReqCoverageInfo> getCoverages(QuoteDataBo data) {
        List<ReqCoverageInfo> reqCoverageInfos = new ArrayList<ReqCoverageInfo>();
        for (CoverageBo one : data.getCoverageList()) {
            ReqCoverageInfo item = one.toReqCoverageInfo();
            if (item != null) {
                reqCoverageInfos.add(item);
            }
        }
        return reqCoverageInfos;
    }

}
