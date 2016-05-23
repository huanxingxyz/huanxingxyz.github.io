package com.zhlh.zeus.util;

import cn.remex.util.DateHelper;
import cn.remex.util.FileHelper;
import cn.remex.util.Judgment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhlh.zeus.constant.NaipConstants;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    public static boolean isEnLetterOrDigit(char ch) {
        return (ch >= '0' && ch <= '9') ||
                (ch >= 'a' && ch <= 'z') ||
                (ch >= 'A' && ch <= 'Z');
    }

    public static boolean isBeijingVehicle(String license) {
        try {
            return license.startsWith("京");
        } catch (Exception e) {
            return false;
        }
    }

    public static int distanceToToday(Date date) throws Exception {
        if (date == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long diff = date.getTime() - cal.getTimeInMillis();
        diff = diff / (24 * 60 * 60 * 1000);

        return (int) diff;
    }

    public static int distanceToToday(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        return distanceToToday(date);
    }

    public static boolean isSame(String one, String another) {
        if (one == null && another == null) {
            return true;
        }

        if (one != null) {
            return one.equals(another);
        }

        return false;
    }

    public static boolean isTodayOrBefore(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);

            return date.compareTo(new Date()) <= 0;
        } catch (Exception e) {
            return true;
        }
    }

    public static String getTomorrow() {
        Date date = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();

        return Util.dateToString(date);
    }

    public static String matchLastDate(Vector<String> dates) {

        try {
            if (dates.size() <= 1) {
                return dates.firstElement();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date lastDate = null;
            String selectedOne = null;

            for (String one : dates) {
                Date cur = sdf.parse(one);
                if (lastDate == null || lastDate.compareTo(cur) < 0) {
                    lastDate = cur;
                    selectedOne = one;
                }
            }

            return selectedOne;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isSameDay(long datetime1, long datetime2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(datetime1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(datetime2);

        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static float floatScale(float value, int scale) {
        return (float) doubleScale((double) value, scale);
    }

    public static double doubleScale(double value, int scale) {
        int roundingMode = 4;// 表示四舍五入
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        return bd.doubleValue();
    }

    public static String checkValue(String value, String defaultValue) {
        if (value == null || value.length() <= 0) {
            return defaultValue;
        }
        return value;
    }

    public static String toGson(Object obj) {
        try {
            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT,
                    Modifier.STATIC).create();
            return gson.toJson(obj);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static void copy(Object fromObj, Object toObj) {

        Class fromClazz = fromObj.getClass();
        Class toClazz = toObj.getClass();

        Field[] fields = fromClazz.getDeclaredFields();

        for (Field fromField : fields) {
            try {
                if (Modifier.isStatic(fromField.getModifiers())) {
                    continue;
                }
                Annotation ann = fromField.getAnnotation(ObjectCopy_NotCopy.class);
                if (ann != null) {
                    continue;
                }

                fromField.setAccessible(true);
                Object value = fromField.get(fromObj);
                if (value == null) {
                    continue;
                }

                try {
                    Field toField = toClazz.getDeclaredField(fromField.getName());
                    ann = toField.getAnnotation(ObjectCopy_NotCopy.class);
                    if (ann != null) {
                        continue;
                    }

                    toField.setAccessible(true);
                    toField.set(toObj, value);
                } catch (NoSuchFieldException e) {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }

        }

    }

    public static int[] diffDates(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffDay = endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR);
        diffDay = Math.abs(diffDay);

        return new int[]{diffYear, diffDay};
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    public static int whereChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return i;
            }
        }
        return -1;
    }

    public static int compareString(String one, String two) {
        if (one == null || two == null) {
            if (one == null && two == null) {
                return 0;
            }
            if (one == null && two != null) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return one.compareTo(two);
        }
    }

    static private int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    static private int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    static public float getSimilarityRatio(String str, String target) {
        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
    }


    /**
     * 判断保险公司是否为人保
     *
     * @param insureCode
     * @return
     */
    public static boolean checkIsRenBao(String insureCode) {
        return insureCode.equals(Profile.INSURERCODE_RENBAO);
    }

    /**
     * 获取性别
     * 1-男，2-女
     *
     * @param value
     * @return
     */
    public static String getGender(String value) {
        String tIdNo = value.trim();
        if (tIdNo.length() != 15 && tIdNo.length() != 18) {
            return "";
        }
        String sex = "";
        if (tIdNo.length() == 15) {
            sex = tIdNo.substring(14, 15);
        } else {
            sex = tIdNo.substring(16, 17);
        }
        try {
            int iSex = Integer.parseInt(sex);
//            iSex = iSex % 2;
            iSex %= 2;
            if (iSex == 0) {
                return "2";
            }
            if (iSex == 1) {
                return "1";
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }

    /**
     * 根据身份账号计算年龄
     *
     * @param IDCardNum
     * @return
     */
    static int getAge(String IDCardNum) {
        int year, month, day, idLength = IDCardNum.length();
        Calendar cal1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        if (idLength == 18) {
            year = Integer.parseInt(IDCardNum.substring(6, 10));
            month = Integer.parseInt(IDCardNum.substring(10, 12));
            day = Integer.parseInt(IDCardNum.substring(12, 14));
        } else if (idLength == 15) {
            year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900;
            month = Integer.parseInt(IDCardNum.substring(8, 10));
            day = Integer.parseInt(IDCardNum.substring(10, 12));
        } else {
            System.out.println("This ID card number is invalid!");
            return -1;
        }
        cal1.set(year, month, day);
        return getYearDiff(today, cal1);
    }

    static int getYearDiff(Calendar cal, Calendar cal1) {
        int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
        int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
        return (y * 12 + m) / 12;
    }

    /**
     * 获取生日
     *
     * @param birthday
     * @return
     */
    public static String getBirthday(String birthday) {
        if (birthday.length() == 18) {
            return birthday.substring(6, 14);
        }
        if (birthday.length() == 15) {
            return birthday.substring(6, 12);
        }
        return "";
    }

    /**
     * 系统拦截到的异常信息的转换
     *
     * @param em 异常信息
     * @return 转换后的信息
     */
    public static String transErrorMsg(String em) {
        String msg;
        if (null == em) {
            return "程序猿已累趴，抢救中···";
        }
        if (em.indexOf("未查到有效的执行机构映射关系") >= 0 || em.indexOf("map为空!，检索为") >= 0 || em.indexOf("未开通此地区服务") >= 0) {
//			msg = "正在一步一步往前爬···\r\n<br>(我们长期招聘开发工程师)";
            msg = "保险公司又任性了！您可以换一个保险条款或直接换家保险公司试试";
        } else if (em.indexOf("保险起期") >= 0 || em.indexOf("交强险投保日距离") >= 0 || em.indexOf("生效时间必须晚于系") >= 0 || em.indexOf("投保日期不能早于当") >= 0
                || em.indexOf("起保日期") >= 0 || em.indexOf("当前日期") >= 0 || em.indexOf("时间范围") >= 0
                ) {
//			msg = "未到规定投保日期。";
            msg = "您近期是不是投保过，请重新确认您的车险开始日期";
        } else if (em.indexOf("最少投保其中一个基本险") >= 0
                || em.indexOf("险种选择不计免赔率") >= 0
                || em.indexOf("不计免赔特约条款必须投保相应的主险") >= 0
                ) {
//			msg = "条款选择有误";
            msg = "您选择的某个险种被承保公司拒保，请重新选择";
        } else if (em.indexOf("已经在其他公司投保了同类型") >= 0 || em.indexOf("该车已经本公司相关渠道确认投保，目前尚未出单") >= 0 || em.indexOf("重复提交投保") >= 0) {
//			msg = "该车已经投保";
            msg = "您近期是不是投保过，请重新确认您的车险开始日期";
        } else if (em.indexOf("太保链接异常") >= 0 || em.indexOf("调用后端服务失败") >= 0 || em.indexOf("系统异常，请联系技术人员") >= 0) {
//			msg = "保险公司报价服务超时";
            msg = "承保公司系统繁忙，请稍后再试";
        } else if (em.indexOf("投保查询次数") >= 0) {
//			msg = "保险公司报价服务次数限制";
            msg = "承保公司系统繁忙，请稍后再试";
        } else if (em.indexOf("请在平台返回的车型列表中选择对应的车型") >= 0) {
//			msg = "车型信息不符合该保险公司的复核要求，如需投保请联系客服订制投保方案。";
            msg = "您的车型信息不符合承保公司的审核要求，请修改后重试";
        } else if (em.indexOf("投保单状态:核保通过 投保单系统来源代码:第三方平台 经") >= 0) {
//			msg = "该车已经本公司相关渠道确认投保，目前尚未出单。如是您本人重复提交投保请联系客服进行撤单。";
            msg = "您近期是不是投保过，请重新确认您的车险开始日期";
        } else if (em.indexOf("手机绑定数量已超过最大值，无法上传") >= 0 || em.indexOf("手机号") >= 0 && em.indexOf("人员绑定数量已超过最大值") >= 0) {
//			msg = "手机绑定数量已超过最大值，无法上传";
            msg = "您的手机号绑定了过多的车辆，请换个手机号试试";
        } else if (em.indexOf("该车已经本公司相关渠道确认投保，目前尚未出单") >= 0 || em.indexOf("重复提交投保") >= 0) {
//			msg = "该车已经投保";
            msg = "您近期是不是投保过，请重新确认您的车险开始日期";
        } else if (em.indexOf("提供服务主机状态均不正常") >= 0 || em.indexOf("Cpsp") >= 0) {
//			msg = "支付系统服务异常!";
            msg = "承保公司系统繁忙，请稍后重试";
        } else if (em.indexOf("被保险人姓名不能为空") >= 0) {
            msg = "被保险人姓名不能为空!";
        } else if (em.indexOf("投保人姓名不能为空") >= 0) {
            msg = "投保人姓名不能为空!";
        } else if (em.indexOf("存在重复险种") >= 0) {//平台返回错误信息险种列表中不允许存在重复险种
            msg = "险种列表中不能有重复险种!";
        } else if (em.indexOf("投保人的证号码已存在") >= 0) {
            msg = "投保人姓名与身份证不匹配，请重新确认";
        } else if (em.indexOf("最少投保其中一个基本险") >= 0 || em.indexOf("至少选择一个险种才能计算保费") >= 0) {
            msg = "请选择险种再报价";
        } else if (em.indexOf("被保人的证号码已存在") >= 0) {
            msg = "被保人姓名与身份证不匹配，请重新确认";
        } else if (em.indexOf("调用后端服务超时") >= 0 || em.indexOf("Connection refused") >= 0) {
            msg = "系统繁忙，请稍后重试";
        } else if (em.indexOf("交警库不存在该车辆数据") >= 0 || em.indexOf("请核对号牌号码、车架号、发动机号是否与行驶证录入一致") >= 0 || em.indexOf("没有车辆相关记录") >= 0) {
            msg = "交警库未查到您的车哦，请检查车牌号、车架号、发动机号与行驶证是否一致";
        } else {
            msg = em;
        }
//        else{
//            int idx = em.lastIndexOf("Exception:");
//            final String msg1 = em.substring(idx+1);
//            new Thread(new Runnable() {
//                public void run() {
//                    ReqEmail reqEmail = new ReqEmail();
//                    reqEmail.setTos(new String[]{"liuhy@qqbx.com.cn"});
//                    reqEmail.setContent("7个萝卜服务提示，异常信息:"+msg1+"没有经过转换，请确定该异常信息的显示方式与内容后与7个萝卜开发人员联系！");
//                    reqEmail.setTitle("7个萝卜服务提示：发现没有转换的异常信息");
//                    try{
//                        SoaClient.invokeService("remex:soa://EmailBs:execute", reqEmail, null);
//                    }catch (Exception e) {
//                        logger.error("提示异常信息没有转换的邮件发送服务异常！",e);
//                    }
//                }
//            }).start();
//
//            msg=em;
//        }
//        logger.info("转换异常信息："+em+" ->"+msg);
        return msg;
    }

    public static Hashtable<String, String> GetAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京市");
        hashtable.put("12", "天津市");
        hashtable.put("13", "河北省");
        hashtable.put("14", "山西省");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁省");
        hashtable.put("22", "吉林省");
        hashtable.put("23", "黑龙江省");
        hashtable.put("31", "上海市");
        hashtable.put("32", "江苏省");
        hashtable.put("33", "浙江省");
        hashtable.put("34", "安徽省");
        hashtable.put("35", "福建省");
        hashtable.put("36", "江西省");
        hashtable.put("37", "山东省");
        hashtable.put("41", "河南省");
        hashtable.put("42", "湖北省");
        hashtable.put("43", "湖南省");
        hashtable.put("44", "广东省");
        hashtable.put("45", "广西省");
        hashtable.put("46", "海南省");
        hashtable.put("50", "重庆市");
        hashtable.put("51", "四川省");
        hashtable.put("52", "贵州省");
        hashtable.put("53", "云南省");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西省");
        hashtable.put("62", "甘肃省");
        hashtable.put("63", "青海省");
        hashtable.put("64", "宁夏省");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾省");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    public static Map<String, String> ygVehicleMap = new HashMap<String, String>();

    public static Map<String, String> CRCityMap = new HashMap<String, String>();
    public static Map<String, String> cityMap = new HashMap<String, String>();
    public static final String CR = "";

    static {
        CRCityMap.put("370100", CR);
        CRCityMap.put("370200", CR);
        CRCityMap.put("450100", CR);
        CRCityMap.put("610100", CR);
        CRCityMap.put("230100", CR);
        CRCityMap.put("500100", CR);
        CRCityMap.put("150100", CR);
        CRCityMap.put("410100", CR);
        CRCityMap.put("340100", CR);
        CRCityMap.put("420100", CR);
        CRCityMap.put("430100", CR);
        CRCityMap.put("440100", CR);
        CRCityMap.put("510100", CR);
        CRCityMap.put("220100", CR);

        cityMap.put("110000", CR);
        cityMap.put("120000", CR);
        cityMap.put("310000", CR);
        cityMap.put("710000", CR);
        cityMap.put("440300", CR);
        cityMap.put("370200", CR);
        cityMap.put("210200", CR);
        cityMap.put("330200", CR);
        cityMap.put("320500", CR);
        cityMap.put("350200", CR);
        cityMap.put("500100", CR);

        ygVehicleMap.put("云", CR);
        ygVehicleMap.put("津", CR);
        ygVehicleMap.put("桂", CR);
        ygVehicleMap.put("吉", CR);
        ygVehicleMap.put("渝", CR);
        ygVehicleMap.put("赣", CR);
        ygVehicleMap.put("琼", CR);
        ygVehicleMap.put("贵", CR);
        ygVehicleMap.put("甘", CR);
        ygVehicleMap.put("藏", CR);
        ygVehicleMap.put("新", CR);

    }

    // 是否商改地区
    public static boolean isYGVehicleCity(String licenseNo) {

        String shortName = licenseNo.substring(0, 1);

        if (ygVehicleMap.containsKey(shortName)) {
            return true;
        }
        return false;
    }

    // 是否商改地区
    public static boolean isCRCity(String cityCode) {

        String curCityCode = cityMap.containsKey(cityCode) ? cityCode : cityCode.substring(0, 2) + "0100";

        if (CRCityMap.containsKey(curCityCode)) {
            return true;
        }
        return false;
    }

    /**
     * 序列号格式
     * %1：第一个参数，代表太保交易类型代码
     * %2：系统当前毫秒数
     * %3：序列号
     */
    public static final String TransType_SN_Template = "ZHLH%1$s%2$tY%2$tm%2$td%3$09d";

    /**
     * @param transType 交易类型
     * @param beanClass 根据数据库中的类创建序列号
     *                  {@link Util#TransType_SN_Template}
     * @return
     * @see Util#TransType_SN_Template
     */
    public static String createTransNo(String transType, Class<?> beanClass) {
        Random radomSeed = new Random();
        int randomNum = radomSeed.nextInt(999999999);
        return String.format(TransType_SN_Template, transType, System.currentTimeMillis(),
                Integer.valueOf(randomNum));
    }

    public static String getTime(final String formatPattent) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattent);
        return format.format(new Date());
    }

    /**
     * 保存xml报文至本地资源库
     *
     * @param transDirection 交易方向：请求或响应
     * @param transNo        交易流水号
     * @param XMLObject      要保存的对象String switchDirection, String switchType,
     *                       String comCode, String curOperator, Object XMLObject,
     */
    public static void saveXMLFile(String transDirection, String transNo, Object XMLObject, String charset) {
        final String curSystem = System.getProperty("os.name");
        String baseXmlFilePath = "";
        if (curSystem.toUpperCase().startsWith(NaipConstants.WIN)) {
            baseXmlFilePath = NaipConstants.SOA_XMLFILE_PATH_WIN;
        }
        // LINUX 无盘符定义文件层级关系
        else if (curSystem.toUpperCase().startsWith(NaipConstants.LINUX)) {
            baseXmlFilePath = NaipConstants.SOA_XMLFILE_PATH_LINUX;
        }
        //String insuComName = AitpConfig.obtainConfigValue(STR_ConfClass_Aitp, STR_InsuComXmlPath, aitpCommParam.getInsuCom());
        String insuComName = "cpic";
        String xmlFilePath = baseXmlFilePath + File.separatorChar
                + insuComName + File.separatorChar                                //保险公司
                + getTime("yyyyMMdd") + File.separatorChar                                //管理机构日期
                + NaipConstants.SYSTEM + "-"                            //系统操作员
                + NaipConstants.TRANS_TYPE_NIA + "-"                                //业务交易类型
                + transDirection + "-"                        //交易方向：request、response
                + (Judgment.nullOrBlank(transNo) ? "TransNoMissing" : transNo)    //15位交易码
                + ".xml";
        // 创建报文要保存的文件夹及保存文件
        FileHelper.saveFileContent(xmlFilePath, XMLObject, charset);
    }

    /**
     * 根据指定的年数和天数返回指定的日期
     *
     * @param date
     * @param year
     * @param day
     * @return 指定的日期{@link DateHelper}
     */
    public static String obtainTargetDate(String date, int year, int day) {
        Date curDate = DateHelper.parseDate(date);
        Date tempDate = DateHelper.getDateDiffYear(curDate, year);
        Date finalDate = DateHelper.getDate(tempDate, day);
        return DateHelper.formatDate(finalDate);
    }

    /**
     * 根据指定的月数回指定的日期
     *
     * @param date
     * @param
     * @return 指定的日期{@link DateHelper}
     */
    public static String obtainTargetDate(String date, int month) {
        Date curDate = DateHelper.parseDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.MONTH, month);
        return DateHelper.formatDate(calendar.getTime());
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }
}
