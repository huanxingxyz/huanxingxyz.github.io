package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.vehicle.LocalVehicleResDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleReqDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleResDto;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 15:53
 * @comment 车辆信息查询接口
 */
public interface VehicleInfoRService {

    /**
     * 从行业平台查询车辆全量信息
     * @param queryVehicleReqDto
     * @return
     */
    QueryVehicleResDto queryVehicleInfo(QueryVehicleReqDto queryVehicleReqDto);


    /**
     * 根据车牌号从平台查询车辆基本信息(废弃)
     * @param channelCode
     * @param licenseNo
     * @return
     */
    LocalVehicleResDto queryLocalVehicle(String channelCode, String licenseNo);
}
