package com.zhlh.zeus.constant;

/**
 * Created by wjd19 on 2016/4/30.
 */
public interface  NaipConstants {
    /**
     * 合作方代码
     */
    static String PARTNER_CODE = "ZHLH";
    /**
     * 业务交易码
     */
    static String TRANSACTION_CODE = "107001";
    static String MSG_PREFIX = "LSH";
    static String TRANSNO_PREFIX = "GRZHX";
    /**
     * 用户名、密码
     */
    static String USER = "ZHLH";
    static String PRODUCT_HEAD_PWD = "UJNR/UNX9u7FiBqfaSRyoEiuOkHWbmbQQbOUOi6vNXn9B+ZeA2Z0INZlMv0DU+zGjI4GgC2UTPOOl/zJGPjpAQ==";
    static String UAT_HEAD_PWD = "123456";

    /**
     * 身份证类型
     */
    static String INSURED_CERT_TYPE = "1";
    /**
     * 赠送盗刷险投保人信息常量
     * HOLDER_NAME 投保人姓名
     * HOLDER_CERT_TYPE 投保人证件类型
     * HOLDER_CERT_CODE 投保人证件号码
     * HOLDER_EMAIL 投保人邮箱
     * HOLDER_ADDRESS 投保人地址
     */
    static String HOLDER_NAME ="北京中海联合保险经纪有限公司";
    static String HOLDER_CERT_TYPE = "4";
    static String HOLDER_CERT_CODE = "05361207-8";
    static String HOLDER_TEL = "010-51721768";
    static String HOLDER_EMAIL = "zhlh@qqbx.com.cn";
    static String HOLDER_ADDRESS = " 北京市海淀区北四环西路52号方正国际大厦1501室";
    /**
     * 短信发送标志 ELC_MSG_FLAG
     * 邮件发送标志 ELC_EML_FLAG
     */
    static String ELC_MSG_FLAG = "1";
    static String ELC_EML_FLAG = "1";

    static String TERMINALNO = "1010100";
    static String BUSINESS_CODE_PREFIX = "DDH";
    static String PLAN_CODE = "PN110678001500000019";
    static String PLC_ELC_FLAG = "0";
    static String PRODUCT_NUMBER = "1";//购买份数

    /**
     * PSNU-核保未通过
     * PSAU-核保通过
     */
    static final String  PSNU = "PSNU";
    static final String  PSAU = "PSAU";

    static String ELC_MOBILE = "";
    /**
     * 赠送价格
     */
    static String HANDSEL_AMOUNT ="10100";

    /**
     * 毫秒
     */
    static String MILLISECOND = "00";

    /**
     * 编码方式
     */
    static String UTF8 = "UTF-8";
    /**
     * 系统
     */
    static String WIN = "WIN";
    static String LINUX = "LINUX";
    /**
     * 系统管理员
     */
    static String SYSTEM = "system";
    static String TRANS_TYPE_NIA = "NIA";
    /**
     * 请求
     */
    static String REQUEST = "REQUEST";
    static String RESPONSE = "RESPONSE";
    static String INDIVIDUAL_ACCOUNT_LOSS_INSU_RSERVICE = "IndividualAccountLossInsuRService";
    /**
     * 太保返回成功核保标志
     */
    static String RESULT_SUCCESS = "000000";
    /**
     * 报文保存基础路径
     * SOA_XMLFILE_PATH_WIN -windows系统路径
     * SOA_XMLFILE_PATH_LINUX - linux系统路径
     */
    static String SOA_XMLFILE_PATH_WIN = "D:\\AppRoot\\Remex2\\SoaXmls\\";
    static String SOA_XMLFILE_PATH_LINUX = "/data/Remex2/SoaXmls";





    static String  PRODUCT_URL = "https://jttp.cpic.com.cn:8443/jttp/itxsvc/param";
    static String  UAT_URL = "https://test.cpic.com.cn/jttpitxhttps/itxsvc/param";

    /**
     * 版本标识，固定值 3
     */
    static String MESSAGEROUTER_VERSION = "3";
    /**
     * 业务协议，固定值 CPIC_ECOM
     */
    static String DOCUMENTPROTOCOL_ZHLH = "CPIC_ECOM";
    /**
     * UAT_PWD测试密码
     * PRODUCT_PWD 生产密码
     */
    static String PWD = "cpicJttp";
    static String PRODUCT_PWD = "zhlh_cpic_https";
    static String HTTPS ="https";
    static String UAT_PWD = "cpicJttp";


    static String MESSAGEROUTER = "messageRouter";
    // 业务伙伴代码
    static String TRADINGPARTNER="tradingPartner";
    // 业务协议
    static String DOCUMENTPROTOCOL="documentProtocol";
    // xml请求报文
    static String REQUESTMESSAGE = "requestMessage";

    /**
     * 已撤单
     */
    static String PSYC = "PSYC";
}
