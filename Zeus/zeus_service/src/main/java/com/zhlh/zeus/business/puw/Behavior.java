package com.zhlh.zeus.business.puw;

import com.zhlh.zeus.business.bo.quote.CoverageBo;
import com.zhlh.zeus.business.bo.quote.QuoteDataBo;

public class Behavior {

    final public static String Param_ALL = "ALL";
    final public static String Param_deductible = "deductible";
    final public static String Action_Func_No_Passed = "No_Passed";

    /**
     * 格式:<Func> <Param>
     * Func:No_Passed 不通过。
     * Param:如果没有，表示当前Rule的作用范围， 如果ALL，表示所有险种。
     */
    String action;

    void doing(CoverageBo item, QuoteDataBo puw, String ruleDesc) throws Exception {
        String words[] = action.split(" ");
        if (words[0].equals(Action_Func_No_Passed)) {
            if (words.length <= 1 && item != null) {
                item.setConfirmed(0, ruleDesc);
            } else {
                if (item == null || words[1].equals(Param_ALL)) {
                    puw.setConfirmed(0, ruleDesc);
                } else if (words[1].equals(Param_deductible)) {
                    item.setDeductible(0, ruleDesc);
                } else {
                    throw new RuntimeException("Behavior.action param error:" + action);
                }
            }
        } else {
            throw new RuntimeException("Behavior.action format error:" + action);
        }

    }
}
