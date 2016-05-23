package com.zhlh.zeus.business.idcard;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class IdCardManager {

    static private HashMap<String, ArrayList<IdCardArea>> idAreas = new HashMap<String, ArrayList<IdCardArea>>();

    public static void initialize() {
        initialize("id-area-code.csv");
    }

    public static void initialize(String filepath) {
        try {
            loadFromCSV(filepath);
        } catch (Exception e) {
            System.err.println("load idAreaCode data from CSV error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    static private void loadFromCSV(String filepath) throws IOException {

        File csvData = new File(filepath);
        CSVFormat fmt = CSVFormat.EXCEL.withDelimiter(';');
        CSVParser parser = CSVParser.parse(csvData, Charset.forName("GBK"), fmt);

        int sumCount = 0;

        for (CSVRecord csvRecord : parser) {
            sumCount++;
            try {
                IdCardArea area = new IdCardArea(csvRecord.get(1), csvRecord.get(2), csvRecord.get(3), csvRecord.get(4));
                add(area);
            } catch (Exception e) {
                System.err.println(csvRecord.get(0) + " : " + e.getMessage());
                continue;
            }
        }

    }

    static private void add(IdCardArea area) {
        ArrayList<IdCardArea> list = idAreas.get(area.areaCode[0]);
        if (list == null) {
            list = new ArrayList<IdCardArea>();
            list.add(area);
            idAreas.put(area.areaCode[0], list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (area.compareTo(list.get(i)) < 0) {
                    list.add(i, area);
                    return;
                }
            }
            list.add(area);
        }
    }

    public static IdCardArea getAreaById(String id) {
        String codes[] = new String[]{id.substring(0, 2),
                id.substring(2, 4),
                id.substring(4, 6)
        };
        ArrayList<IdCardArea> list = idAreas.get(codes[0]);
        if (list == null) {
            return null;
        }
        for (IdCardArea one : list) {
            int result = one.compareTo(codes);
            if (result == 0) {
                return one;
            }
        }

        return list.get(0);

    }

    // TODO 后期重构工具类
    public static String getSexFlag(String id) {

        int sexBit = 1;

        if (id != null && id.length() == 18) {
            sexBit = Integer.parseInt(id.substring(16, 17));
        } else if (id != null && id.length() == 15) {
            sexBit = Integer.parseInt(id.substring(14));
        }

        return sexBit % 2 == 0 ? "2" : "1";
    }


    // 身份证号码中的出生日期的格式
    final static String BIRTH_DATE_FORMAT = "yyyyMMdd";
    // 身份证的最小出生日期,1900年1月1日
    final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L);
    final static int NEW_CARD_NUMBER_LENGTH = 18;
    final static int OLD_CARD_NUMBER_LENGTH = 15;
    /**
     * 18位身份证中最后一位校验码
     */
    final static char[] VERIFY_CODE = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    /**
     * 18位身份证中，各个数字的生成校验码时的权值
     */
    final static int[] VERIFY_CODE_WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * <li>校验码（第十八位数）：<br/>
     * <ul>
     * <li>十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0...16 ，先对前17位数字的权求和；
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * 2；</li>
     * <li>计算模 Y = mod(S, 11)</li>
     * <li>通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2</li>
     * </ul>
     *
     * @param cardNumber
     * @return
     */
    static char calculateVerifyCode(CharSequence cardNumber) {
        int sum = 0;
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
            char ch = cardNumber.charAt(i);
            sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
        }
        return VERIFY_CODE[sum % 11];
    }

    /**
     * 把15位身份证号码转换到18位身份证号码<br>
     * 15位身份证号码与18位身份证号码的区别为：<br>
     * 1、15位身份证号码中，"出生年份"字段是2位，转换时需要补入"19"，表示20世纪<br>
     * 2、15位身份证无最后一位校验码。18位身份证中，校验码根据根据前17位生成
     *
     * @param cardNumber
     * @return
     */
    static String contertToNewCardNumber(String oldCardNumber) {
        StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
        buf.append(oldCardNumber.substring(0, 6));
        buf.append("19");
        buf.append(oldCardNumber.substring(6));
        buf.append(calculateVerifyCode(buf));
        return buf.toString();
    }

}
