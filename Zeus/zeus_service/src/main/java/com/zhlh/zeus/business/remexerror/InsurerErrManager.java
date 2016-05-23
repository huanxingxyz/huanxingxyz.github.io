package com.zhlh.zeus.business.remexerror;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/28
 */
public class InsurerErrManager {

    public static HashMap<String, ArrayList<InsurerErr>> insurerErrMap = new HashMap<String, ArrayList<InsurerErr>>();

    public static void initialize(String filePath) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

            Gson gsonMaker = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC).create();

            Type type = new TypeToken<ArrayList<InsurerErr>>() {
            }.getType();
            ArrayList<InsurerErr> list = gsonMaker.fromJson(in, type);
            for (InsurerErr one : list) {
                addInsurerErr(one);
            }
        } catch (Exception e) {
            System.err.println("load remexErrorMsg data error:" + e.getMessage());
        }

    }
    static private void addInsurerErr(InsurerErr insurerErr) {
        if (insurerErr == null) {
            return;
        }
        ArrayList<InsurerErr> insurerErrs = insurerErrMap.get(insurerErr.errorType);
        if (insurerErrs == null) {
            insurerErrs = new ArrayList<InsurerErr>();
            insurerErrMap.put(insurerErr.errorType, insurerErrs);
        }

        insurerErrs.add(insurerErr);
    }
}
