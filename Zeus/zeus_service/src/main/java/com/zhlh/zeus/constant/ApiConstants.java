package com.zhlh.zeus.constant;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/20
 * @comment 常量接口
 */
public interface ApiConstants {

    /**
     * 0 车主
     * 1 被保人
     * 2 投保人
     */
    public final String PERSONTYPE_OWNER = "0";
    public final String PERSONTYPE_INSURER = "1";
    public final String PERSONTYPE_HOLDER = "2";


    /**
     * 01 身份证
     */
    public final String CERTTYPE_ID = "01";

    /**
     * 1 个人
     */
    public final String PERSONCLASS_PERSON = "1";

    /**
     * 民族
     * 默认-汉族
     */
    public final String ETHNIC_HAN = "汉族";

    /**
     * 地址 北京市
     */
    public final String ADDRESS_BJ = "北京市";

    /**
     * 北京城市编码-110000
     */
    public final String CITYCODE_BJ = "110000";

    /**
     * 发证机关-公安局
     */
    public final String POLICE = "公安局";

    /**
     * 配送邮箱
     * 中海联合公司邮箱 zhlh@qqbx.com.cn
     */
    public final String EMAIL = "zhlh@qqbx.com.cn";

    /**
     * 配送邮编
     * 北京市邮编 100000
     */
    public final String POSTCODE = "100000";

    /**
     * 太保 I00001,英大 I00002, 国寿 I00003, 富德 I00004,中华联合 I00008,
     * 华安 I00009,阳光 I00019, 人保 I00027  安盛天平 I00028
     */
    public final String INSURECODESTRING = "I00001,I00002,I00003,I00004,I00008,I00009,I00019,I00027,I00028";

    /**
     * 交易类型
     */
    static final String TRANSTYPE_QUERYDELIVERY = "queryDelivery";//查询递送信息
    static final String TRANSTYPE_QUERYPAYURL = "queryPayUrl";//查询支付链接
    static final String TRANSTYPE_QUERYLOCALVEHICLES = "queryLocalVehicles";		    //交易类型-车辆查询
    static final String TRANSTYPE_BEGINCARQUERY = "beginCarQuery";		    //交易类型-车辆信息查询
    static final String TRANSTYPE_BEGINQUOTEPRICE = "beginQuotePrice";		//交易类型-报价
    static final String TRANSTYPE_QUERYPACKAGE = "queryPackage";		//交易类型-报价
    static final String TRANSTYPE_BEGININSURE = "beginInsure";		        //交易类型-核保
    static final String TRANSTYPE_QUERYPROPOSALCONTSTATE = "queryProposalContState"; //交易类型-报价
    static final String TRANSTYPE_QUERYLASTCONTENDDATE = "queryLastContEndDate";	//交易类型-上年止期
    static final String TRANSTYPE_QUERYCARREALPRICE = "queryCarRealPrice";	//交易类型-实际价值
    static final String TRANSTYPE_SAVEONLINECUSTOMER = "saveOnlineCustomer";	//交易类型-进线客户资料保存
    static final String TRANSTYPE_QUERYINSURANCEINFO = "queryInsuranceInfo";	//交易类型-查询保险公司列表
    static final String TRANSTYPE_CHARGENOTICE = "chargeNotice";	//交易类型-通知扣费
    static final String TRANSTYPE_AITPINSUCOMPAYBS = "aitpInsuComPay";	//交易类型-平台保单支付状态修改
    static final String TRANSTYPE_AIWCNOTICECUSTOMERBS = "NoticeCustomer";
    static final String TRANSTYPE_QPSM = "QPSM"; // 报价短信
    static final String TRANSTYPE_PLCS = "PLCS"; // 保单确认短信
    static final String TRANSTYPE_PLDS = "PLDS"; // 快递确认短信
    static final String TRANSTYPE_QUERYEXPIREPOLICY = "queryExpirePolicy";//交易类型—查询即将过期保单
    static final String TRANSTYPE_AFTERSERVICE = "AfterService"; //交易类型-报价
    static final String TRANSTYPE_IDENTITYCOLLECT = "identityConfirm"; //交易类型-身份信息采集
    static final String TRANSTYPE_QUERYSERVICEPROVIDERMAP = "QueryServiceProviderMap"; //交易类型- 服务提供商
    static final String TRANSTYPE_QUERYFRAMEENGINE = "QueryFrameEngine"; //交易类型-查询车架和发动机号
    /**
     * 支付
     */
    static final String LUOBERPAY = "WXPAY" ;

    /**
     *
     */
    static final String PRODUCTTYPE_AI = "AI";
    /**
     * 数量 1
     */
    static final String QUANTITY_ONE = "1";

    /**
     * extend对象交易渠道
     */
    static  final String  EXTEND_TRANSCHANNEL_VIP =  "MBWX";
    static  final String  EXTEND_TRANSCHANNEL_AG =  "WXAT";


    static final String  EXECUTE = "execute";
    /**
     * 身份验证错误信息
     */
    static String[] errMsgs = new String[]{"身份证号码长度应该为15位或18位",
        "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字",
        "身份证生日无效","身份证生日不在有效范围",
        "身份证地区编码错误","身份证无效，不是合法的身份证号码",
        "身份证日期无效","身份证月份无效"};
}
