package com.zhlh.zeus.service;

import com.zhlh.Tiny.util.CommonUtil;
import com.zhlh.zeus.api.VehicleInfoRService;
import com.zhlh.zeus.business.bo.vehicle.QueryVehicleBo;
import com.zhlh.zeus.business.bo.vehicle.VehicleInfoBo;
import com.zhlh.zeus.dto.vehicle.LocalVehicleResDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleReqDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleResDto;
import com.zhlh.zeus.dto.vehicle.VehicleDesInfoDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.exception.ExceptionHelper;
import com.zhlh.zeus.rservice.VehicleRemoteService;
import com.zhlh.zeus.util.Profile;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;
import zhlh.anbox.aitp.aiws.xmlbeans.localvehicles.ResVehicleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 15:57
 * @comment
 */

@Service("vehicleInfoRService")
public class VehicleInfoRServiceImpl implements VehicleInfoRService {

    /**
     * 此接口，车辆查询统一调英大I00002的接口
     * <p/>
     * 从行业平台查询车辆全量信息，重要信息如下：
     * 车型代码rBCode（不同保险公司可能不一样）
     * 购置价purchasePrice（按照此值筛选车辆）
     * 座位数，排量，载重 等下一步报价需要
     *
     * @param queryVehicleReqDto
     * @return
     */
    @Override
    public QueryVehicleResDto queryVehicleInfo(QueryVehicleReqDto queryVehicleReqDto) {

        QueryVehicleResDto queryVehicleResDto = new QueryVehicleResDto();

        if (!isValidQueryVehicleReqDto(queryVehicleReqDto)) {
            queryVehicleResDto.setErrCode(ErrorCode.PARAM_ERROR);
            queryVehicleResDto.setErrMsg(ErrorCode.PARAM_ERROR_MSG);

            return queryVehicleResDto;
        }

        // 车辆查询统一调英大I00002的接口，英大未开通地区用阳光
        queryVehicleReqDto.setInsuCom(Profile.INSURERCODE_YINDA);

        if(Util.isYGVehicleCity(queryVehicleReqDto.getLicenseNo())){
            queryVehicleReqDto.setInsuCom(Profile.INSURERCODE_YANGGUANG);
        }

        QueryVehicleBo queryVehicleBo = null;

        try {
            queryVehicleBo = VehicleRemoteService.queryInsurerVehicleInfo(queryVehicleReqDto);

        } catch (Exception e) {

            ExceptionHelper.transErrorMsg(e, queryVehicleResDto);
            return queryVehicleResDto;
        }

        List<VehicleDesInfoDto> vehicleDesInfoDtoList = new ArrayList<VehicleDesInfoDto>();
        for (VehicleInfoBo vehicleInfoBo : queryVehicleBo.getVehicleInfoList()) {

            VehicleDesInfoDto vehicleDesInfoDto = new VehicleDesInfoDto();
            vehicleDesInfoDto.setPurchasePrice(vehicleInfoBo.getPurchasePrice());
            vehicleDesInfoDto.setMarketDate(vehicleInfoBo.getMarketDate());
            vehicleDesInfoDto.setVehicleTypeDesc(vehicleInfoBo.getVehicleTypeDesc());
            vehicleDesInfoDto.setrBCode(vehicleInfoBo.getrBCode());
            vehicleDesInfoDto.setExhaustCapacity(vehicleInfoBo.getExhaustCapacity());

            vehicleDesInfoDtoList.add(vehicleDesInfoDto);

            // 北京地区，可以获取到真实车主姓名
            queryVehicleResDto.setOwner(vehicleInfoBo.getOwner());
            queryVehicleResDto.setEnrollDate(vehicleInfoBo.getEnrollDate());
            queryVehicleResDto.setBrandName(vehicleInfoBo.getBrandName());
        }

        queryVehicleResDto.setVehicleDesInfoDtoList(vehicleDesInfoDtoList);
        queryVehicleResDto.setInsuCom(queryVehicleReqDto.getInsuCom());
        queryVehicleResDto.setEngineNo(queryVehicleReqDto.getEngineNo());
        queryVehicleResDto.setFrameNo(queryVehicleReqDto.getFrameNo());
        queryVehicleResDto.setLicenseNo(queryVehicleReqDto.getLicenseNo());

        return queryVehicleResDto;
    }


    /**
     * 根据车牌号从平台查询车辆基本信息(废弃)
     *
     * @param channelCode
     * @param licenseNo
     * @return
     */
    public LocalVehicleResDto queryLocalVehicle(String channelCode, String licenseNo) {

        LocalVehicleResDto localVehicleResDto = new LocalVehicleResDto();
        ResVehicleInfo resVehicleInfo = null;
        try {
            resVehicleInfo = VehicleRemoteService.queryRemexLocalVehicle(channelCode,
                    licenseNo);
        } catch (Exception e) {
            ExceptionHelper.transErrorMsg(e, localVehicleResDto);
            return localVehicleResDto;
        }

        if (resVehicleInfo == null) {
            return null;
        }


        localVehicleResDto.setBrandName(resVehicleInfo.getBrandName());
        localVehicleResDto.setEngineNo(resVehicleInfo.getEngineNo());
        localVehicleResDto.setEnrollDate(resVehicleInfo.getEnrollDate());
        localVehicleResDto.setFrameNo(resVehicleInfo.getFrameNo());
        localVehicleResDto.setLicenseNo(resVehicleInfo.getLicenseNo());

        return localVehicleResDto;
    }


    /**
     * 参数校验
     */
    private boolean isValidQueryVehicleReqDto(QueryVehicleReqDto queryVehicleReqDto) {

        if (queryVehicleReqDto == null || CommonUtil.isEmpty(queryVehicleReqDto.getLicenseNo()) ||
                CommonUtil.isEmpty(queryVehicleReqDto.getChannel())) {

            return false;
        } else {

            if (Util.isBeijingVehicle(queryVehicleReqDto.getLicenseNo())) {

                if (CommonUtil.isEmpty(queryVehicleReqDto.getFrameNo()) ||
                        CommonUtil.isEmpty(queryVehicleReqDto.getEngineNo())) {
                    return false;
                }

            } else { // 非北京地区
                if (CommonUtil.isEmpty(queryVehicleReqDto.getFrameNo()) ||
                        CommonUtil.isEmpty(queryVehicleReqDto.getEngineNo()) ||
                        CommonUtil.isEmpty(queryVehicleReqDto.getEnrollDate()) ||
                        CommonUtil.isEmpty(queryVehicleReqDto.getBrandName()) ||
                        CommonUtil.isEmpty(queryVehicleReqDto.getOwner())) {
                    return false;
                }
            }
        }

        return true;
    }
}
