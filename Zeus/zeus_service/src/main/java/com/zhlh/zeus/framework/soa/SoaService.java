package com.zhlh.zeus.framework.soa;

import cn.remex.bs.BsRvo;
import cn.remex.bs.Extend;
import cn.remex.bs.Head;
import cn.remex.soa.SoaException;
import cn.remex.soa.client.SoaClient;
import cn.remex.util.Judgment;
import com.zhlh.Tiny.util.JsonUtil;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.exception.InvokeServiceException;
import com.zhlh.zeus.exception.RemexServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 16:02
 * @comment
 */
public class SoaService {

    protected static final transient Logger logger = LoggerFactory.getLogger(SoaService.class);

    private static String transChannel = "Ancar";

    public static Object invokeService(String channelCode, String serviceName, String transType,
                                       Object reqBody, String flowNo) throws Exception {

        Head reqHead = new Head();
        reqHead.setTransChannel(transChannel == null ? channelCode : transChannel);
        reqHead.setChannelUser("admin");
        reqHead.setChannelPwd("admin");
        reqHead.setChannelOper("002");
        reqHead.setFlowNo(flowNo);
        reqHead.setTransType(transType);

        logger.info("SOA_" + transType + ", Request:" + JsonUtil.beanToJSON(reqBody));
        BsRvo rvo = null;
        try {
            rvo = SoaClient.invokeService("remex:soa://" + serviceName + ":execute", reqHead,
                    reqBody, null, null, 0);
        } catch (SoaException e) {
            // soa调用异常
            logger.error("SOA_Invoke_Exception_" + transType + ", errorCode_" + e.getErrorCode() +
                    ", errorMsg_" + e + ", Request:" + JsonUtil.beanToJSON(reqBody));
            throw new InvokeServiceException(e.getErrorCode(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("SOA_Exception_" + transType + ", errorMsg_" + e.getMessage()+ ", Request:" + JsonUtil.beanToJSON(reqBody));
            throw new InvokeServiceException(ErrorCode.SOA_ERROR_MSG);
        }
        logger.info("SOA_" + transType + ", Response:" + JsonUtil.beanToJSON(rvo)+ ", Request:" + JsonUtil.beanToJSON(reqBody));

        if (!rvo.getExtend().isStatus()) {
            String errorMsg = rvo.getExtend().getErrorMsg();
            String errorCode = rvo.getExtend().getErrorCode();
            logger.error("SOA_RemexServiceException_" + transType + ", errorCode_" + errorCode + ", errorMsg_" +
                    errorMsg+ ", Request:" + JsonUtil.beanToJSON(reqBody));

            // 业务异常
            throw new RemexServiceException(errorCode, errorMsg);
        }

        return rvo.getBody();
    }

    /**
     * @param serviceName
     * @param transType
     * @param reqBody
     * @param extend
     * @param transChannel 渠道编码Ancar YXP
     * @param flowNo       保险公司报价流程id
     * @param sessionId    一次比价的会话,如果没有传，则使用flowNo
     * @return
     */
    public static Object invokeService(String serviceName, String transType, Object reqBody, Extend extend, String transChannel, String userName,
                                       String flowNo, String sessionId) throws RemexServiceException, InvokeServiceException {
        BsRvo rvo = null;
        try {
            Head reqHead = new Head();
            reqHead.setTransChannel(transChannel);
            reqHead.setChannelUser("admin");
            reqHead.setChannelPwd("admin");
            reqHead.setChannelOper(userName);
            reqHead.setFlowNo(Judgment.nullOrBlank(flowNo) ? String.valueOf(System.currentTimeMillis()) : flowNo);//保险公司报价流程id
            reqHead.setSessionId(Judgment.nullOrBlank(sessionId) ? reqHead.getFlowNo() : sessionId);//一次比价的会话,如果没有传，则使用flowNo
            if (Judgment.nullOrBlank(reqHead.getCityCode())) reqHead.setCityCode("NOCityCode");
            reqHead.setTransType(transType);
            rvo = SoaClient.invokeService("remex:soa://" + serviceName + ":execute", reqHead, reqBody, extend, null, 0);
        } catch (SoaException e) {
            logger.error("SOA_Invoke_Exception_" + transType + ", errorCode_" + e.getErrorCode() +
                    ", errorMsg_" + e);
            throw new InvokeServiceException(e.getErrorCode(), e.getMessage(), e);
        }
        if (null == rvo || !rvo.getExtend().isStatus()) {
            String errorMsg = rvo.getExtend().getErrorMsg();
            String errorCode = rvo.getExtend().getErrorCode();
            logger.error("SOA_Service_Exception_" + transType + ", errorCode_" + errorCode + ", errorMsg_" +
                    errorMsg);

            // 业务异常
            throw new RemexServiceException(errorCode, errorMsg);
        }

        return rvo.getBody();
    }

}
