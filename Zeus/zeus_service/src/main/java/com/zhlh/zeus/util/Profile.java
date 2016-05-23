package com.zhlh.zeus.util;

public class Profile {

    final public static String All_Insurers[] = new String[]{
            Profile.INSURERCODE_YINDA,
            Profile.INSURERCODE_TAIBAO,
            Profile.INSURERCODE_FUDE,
            Profile.INSURERCODE_GUOSHOU,
            Profile.INSURERCODE_YANGGUANG,
            Profile.INSURERCODE_RENBAO
    };

    final public static String PERSON_TYPE_OWNER = "0";        // 车主
    final public static String PERSON_TYPE_INSURANT = "1";        // 被报人
    final public static String PERSON_TYPE_POLICYHOLDER = "2";    // 投保人
    final public static String PERSON_TYPE_RECIPIENT = "99";    // 快递接受人

    final static public String INSURERCODE_TAIBAO = "I00001"; // 中国太平洋财产保险股份有限公司
    final static public String INSURERCODE_YINDA = "I00002"; // 英大泰和财产保险股份有限公司
    final static public String INSURERCODE_GUOSHOU = "I00003"; // 中国人寿财产保险股份有限公司
    final static public String INSURERCODE_FUDE = "I00004"; // 中国富德财产保险股份有限公司
    final static public String INSURERCODE_YANGGUANG = "I00019"; // 阳光财产保险股份有限公司
    final static public String INSURERCODE_RENBAO = "I00027";//中国人民保险集团股份有限公司

    final public static String COVERAGEKIND_TCI = "TCI";
    final public static String COVERAGEKIND_VCI = "VCI";
    final public static String CoverageItems[][] = new String[][]{
            {"BZ", "交强险"},
            {"A", "车损险"},
            {"B", "三者险"},
            {"G1", "盗抢险"},
            {"D3", "司机责任险"},
            {"D4", "乘客责任险"},
            {"F", "玻璃险"},
            {"L", "划痕险"},
            {"Z", "自燃险"},
            {"X1", "发动机险"},
            {"M", "不计免赔"},
    };
    final public static String COVERAGE_JIAOQIANG = "BZ"; // 交强险：交强险
    final public static String COVERAGE_CHESUN = "A"; // 车损险：机动车损失险
    final public static String COVERAGE_SANZHE = "B"; // 三者险：机动车第三者责任保险
    final public static String COVERAGE_DAOQIANG = "G1"; // 盗抢险：机动车盗抢险
    final public static String COVERAGE_SIJI = "D3"; // 司机责任险：机动车车上人员责任险(司机)
    final public static String COVERAGE_CHENGKE = "D4"; // 乘客责任险：机动车车上人员责任险(乘客)
    final public static String COVERAGE_BOLI = "F"; // 玻璃险：玻璃单独破碎险
    final public static String COVERAGE_HUAHENG = "L"; // 划痕险：车身划痕险
    final public static String COVERAGE_ZIRAN = "Z"; // 自燃险：自燃损失险
    final public static String COVERAGE_FADONGJI = "X1"; // 发动机险：发动机特别损失险
    final public static String COVERAGE_BUJIMIANPEI = "M";// 不计免赔：不计免赔率特约条款

    final public static String COVERAGE_ALL = "ALL";
    final public static int COVERAGE_CODE = 0;
    final public static int COVERAGE_NAME = 1;

    static public int getCoverageOrderByCode(String code) {
        for (int i = 0; i < CoverageItems.length; i++) {
            if (CoverageItems[i][COVERAGE_CODE].equalsIgnoreCase(code)) {
                return i;
            }
        }

        return -1;
    }


    static public String convertQuoteErrorMessage(String srcMsg) {
        try {
            if (Util.isEmpty(srcMsg)) {
                return null;
            }

            String target = null;

            if (srcMsg.contains("System_Exception")) {
                target = "报价失败(错误-1)";
            } else if (srcMsg.contains("此渠道没有开通:")) {
                target = "报价失败(错误-2)";
            } else if (srcMsg.contains("没有找到相应的车辆信息")) {
                target = "车辆信息错误(错误-3)";
            } else if (srcMsg.contains("没有选择任何险种，无法计算保费")) {
                target = "请修改适当的险种(错误-4)";
            } else if (srcMsg.contains("您今天的算价次数已达到上限")) {
                target = "保费查询当日次数过多(错误-5)";
            } else if (srcMsg.contains("java.lang.NullPointerException")) {
                target = "报价失败(错误-6)";
            } else if (srcMsg.contains("起保日期必须在当前日期的")) {
                //[I00002|QTP]系统信息：10000401:平台返回错误信息起保日期必须在当前日期的规定时间范围内。
                target = "请修改适当的起保日期(错误-7)";
            } else if (srcMsg.contains("机动车损失险不可投保|机动车损失险不计免赔不可投保")) {
                target = "车辆原因，车损险不可投保(错误-8)";
            } else if (srcMsg.contains("该车已经在其他公司投保了同类型的险种，保险期限是")) {
                //[I00004|QTP]系统提示：交强险平台返回：该车已经在其他公司投保了同类型的险种，保险期限是[20150625-20160624],请核对并确认在我公司投保
                target = "车辆不在有效的90天投保期限内(错误-9)";
            } else if (srcMsg.contains("车身划痕险不可投保|车身划痕险不计免赔不可投保")) {
                target = "车辆原因，划痕险不可投保(错误-10)";
            } else if (srcMsg.contains("当前用户当日投保查询次数已经超过")) {
                //[I00004|QTP]系统提示：当前用户当日投保查询次数已经超过500次，禁止进行计算保费连接平台操作！
                target = "保费查询当日次数过多(错误-11)";
            } else if (srcMsg.contains("系统异常")) {
                target = "报价失败(错误-12)";
            } else if (srcMsg.contains("返回状态异常")) {
                target = "报价失败(错误-13)";
            } else if (srcMsg.contains("机动车盗抢险不可投保") || srcMsg.contains("机动车盗抢险不计免赔不可投保")) {
                target = "车辆原因，盗抢险不可投保(错误-14)";
            } else if (srcMsg.contains("玻璃单独破碎险不可投保")) {
                target = "车辆原因，玻璃险不可投保(错误-15)";
            } else if (srcMsg.contains("发动机特别损失险不可投保")) {
                target = "车辆原因，发动机险不可投保(错误-16)";
            } else if (srcMsg.contains("by zero")) {
                target = "报价失败(错误-17)";
            } else if (srcMsg.contains("Server returned HTTP response code: 504")) {
                //504: java.io.IOException: Server returned HTTP response code: 504 for URL: http://61.149.143.222/remex_stage/hs/SoaService
                target = "报价失败(错误-18)";
            } else if (srcMsg.contains("返回报文签名校验失败")) {
                target = "报价失败(错误-19)";
            } else if (srcMsg.contains("调用国寿后台接口发生异常")) {
                //调用国寿后台接口发生异常，异常原因：cn.remex.exception.NetException: HttpPostXml失败，url为：http://www.chinalife.com.cn/tbcx/thirdPartyCar/carProposalthirdPartyCarQueryInfo.do
                target = "报价失败(错误-20)";
            } else if (srcMsg.contains("DBConnPoolImpl")) {
                //DBConnPoolImpl,Default.:创建连接失败!
                target = "报价失败(错误-21)";
            } else if (srcMsg.contains("数据库保存错误")) {
                //车险CBS4ZH数据库保存错误
                target = "报价失败(错误-22)";
            } else if (srcMsg.contains("BeanFactory not initialized")) {
                //车险CBS4ZH数据库保存错误
                target = "报价失败(错误-23)";
            } else if (srcMsg.contains("未查询到相关车辆车型信息")) {
                target = "车辆信息错误(错误-24)";
            } else if (srcMsg.contains("系统未知错误")) {
                //系统信息：10000005:系统未知错误，请联系接口平台人员
                target = "报价失败(错误-25)";
            } else if (srcMsg.contains("Could not execute JDBC batch update")) {
                target = "报价失败(错误-26)";
            } else if (srcMsg.contains("查询参数不正确（核定载客）")) {
                //交强险平台返回：查询参数不正确（核定载客）！--当是本地车时，载客量应该与交警库中的载客量一致!
                target = "报价失败(错误-27)";
            } else if (srcMsg.contains("您的车辆交强险未到投保期")) {
                //抱歉，您的车辆交强险未到投保期，还不能进行投保；请您核对后再来投保。
                target = "车辆交强险未到投保期(错误-28)";
            } else if (srcMsg.contains("未找到费用信息")) {
                //[I00002|QTP]系统信息：10000762:未找到费用信息，请到销管系统配置
                target = "报价失败(错误-29)";
            } else if (srcMsg.contains("java.net.SocketTimeoutException")) {
                //500: java.net.SocketTimeoutException: Read timed out
                target = "报价失败(错误-30)";
            } else if (srcMsg.contains("Server returned HTTP response code: 502")) {
                //502: java.io.IOException: Server returned HTTP response code: 502 for URL: http://61.149.143.222/remex_stage/hs/SoaService
                target = "报价失败(错误-31)";
            } else if (srcMsg.contains("全车盗抢险的保额不能大于车辆实际价格")) {
                //[I00004|QTP]系统提示：全车盗抢险的保额不能大于车辆实际价格！
                target = "报价失败(错误-32)";
            }

            return target;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
