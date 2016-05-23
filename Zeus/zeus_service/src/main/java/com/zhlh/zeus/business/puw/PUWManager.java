package com.zhlh.zeus.business.puw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zhlh.zeus.business.bo.quote.CoverageBo;
import com.zhlh.zeus.business.bo.quote.QuoteDataBo;
import com.zhlh.zeus.util.Profile;
import com.zhlh.zeus.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class PUWManager {

    protected static final transient Logger logger = LoggerFactory.getLogger(PUWManager.class);


    private static HashMap<String, ArrayList<Rule>> puwRules = new HashMap<String, ArrayList<Rule>>();

    public static void initialize() throws Exception {
        initialize("puw-rules.json");
    }

    public static void initialize(String filePath) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

            Gson gsonMaker = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC).create();

            Type type = new TypeToken<ArrayList<Rule>>() {
            }.getType();
            ArrayList<Rule> list = gsonMaker.fromJson(in, type);
            for (Rule one : list) {
                addRule(one);
            }
        } catch (Exception e) {
            System.err.println("load puw_rules data error:" + e.getMessage());
        }

    }

    static private void addRule(Rule rule) {
        if (rule == null) {
            return;
        }
        ArrayList<Rule> rules = puwRules.get(rule.insurer);
        if (rules == null) {
            rules = new ArrayList<Rule>();
            puwRules.put(rule.insurer, rules);
        }

        rules.add(rule);
    }

    public static boolean compute(QuoteDataBo quoteDataBo) throws Exception {

        // 交强险投保限制
        CoverageBo jqItem = quoteDataBo.getCoverageByCode(Profile.COVERAGE_JIAOQIANG);
        if (jqItem != null) {
            boolean error = false;
            String errorDate = null;
            String reason = null;

            String defaultTciDate = quoteDataBo.getTciDefaultStartDate(); // 系统获取的起保日期
            String userTciDate = quoteDataBo.getTciStartDate(); // 用户定义的起保日期

            int days = Util.distanceToToday(defaultTciDate);
            if (days < 0 || days > 90) {
                error = true;
                errorDate = defaultTciDate;
                reason = "交强险投保日距离保单起保日大于90天, 无法投保。";
            } else {
                days = Util.distanceToToday(userTciDate);
                if (days < 0 || days > 90) {
                    error = true;
                    errorDate = userTciDate;
                    reason = "交强险投保日距离保单起保日大于90天, 无法投保。";
                } else {
                    if (Util.stringToDate(userTciDate).before(Util.stringToDate(defaultTciDate))) {
                        error = true;
                        errorDate = userTciDate;
                        reason = "交强险投保日早于上年止期, 无法投保。";
                    }
                }
            }

            if (error) {
                jqItem.setConfirmed(0, reason);

                // 保险公司的接口如此弱智，不投交强险时如果交强险起保日期不为空，则依旧报错。兼容一把。
                quoteDataBo.setTciStartDate(null);
                logger.info("PUW:" + reason + ":" + errorDate + "," + Util.toGson(quoteDataBo));
            }
        } else {
            quoteDataBo.setTciStartDate(null);
        }


        String insuCom = quoteDataBo.getInsuCom();

        ArrayList<Rule> rules = puwRules.get(insuCom);

        if (rules == null) {
            logger.info("PUW: can NOT be found any PUW rules for :" + insuCom + "," + Util.toGson
                    (quoteDataBo));
            return false;
        }


        boolean isHandled = false;
        for (Rule rule : rules) {
            try {
                if (rule.compute(quoteDataBo)) {
                    logger.info("PUW: TRUE\t" + rule + " " + rule.desc + ". " +
                            Util.toGson(quoteDataBo));
                    isHandled = true;
                }
            } catch (Exception e) {
                System.err.println(rule.toString() + " : " + e.getMessage());
                throw e;
            }
        }


        // 不计免赔预核保
        CoverageBo mpItem = quoteDataBo.getCoverageByCode(Profile.COVERAGE_BUJIMIANPEI);
        if (mpItem != null && quoteDataBo.getCoverageList().size() == 1) {
            mpItem.setConfirmed(0, "不计免赔不可单独购买，无有效主险。");
        }

        return isHandled;
    }


}
