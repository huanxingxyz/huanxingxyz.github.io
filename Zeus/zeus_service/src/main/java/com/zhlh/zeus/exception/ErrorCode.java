package com.zhlh.zeus.exception;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/21 10:36
 * @comment
 */
public interface ErrorCode {

    // 系统异常
    int SYS_ERROR = 1000;
    String SYS_ERROR_MSG = "Zeus系统异常";

    int SOA_ERROR = 1001;
    String SOA_ERROR_MSG = "REMEX系统服务调用异常";

    // 请求参数异常
    int PARAM_ERROR = 3000;
    String PARAM_ERROR_MSG = "请求参数不合法";

    int ID_NO_ERROR = 3001;
    String ID_NO_ERROR_MSG = "身份证号码不合法";

    // 业务异常
    int VEHICLE_ERROR = 2001;
    String VEHICLE_ERROR_MSG = "保险公司返回的车辆信息为空";

    int INVALID_POLICY = 2002;
    String INVALID_POLICY_MSG = "没有有效投保险种";

    int INVALID_VCI_STARTDATE = 2003;
    String INVALID_VCI_STARTDATE_MSG = "商业险投保日大于90天, 无法投保";

    int ACTUALVALUE_ERROR = 2004;
    String ACTUALVALUE_ERROR_MSG = "车辆实际价值异常";

    int INVALID_YG_VCI_STARTDATE = 2005;
    String INVALID_YG_VCI_STARTDATE_MSG = "商业险尚未到期, 不能重复投保";

}
