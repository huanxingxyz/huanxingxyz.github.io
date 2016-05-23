package com.zhlh.zeus.business.puw;

import com.zhlh.zeus.business.bo.quote.CoverageBo;
import com.zhlh.zeus.business.bo.quote.QuoteDataBo;
import com.zhlh.zeus.util.Profile;

public class Rule {
    Integer ruleId;
    String desc;            //描述
    String insurer;            //保险公司
    String coverageCode;    //相对的险种代码，如果为：ALL, 表示所有
    Boolean exclusive;        //如果满足排他性exclusive=true, 当此rule满足，其他的rule就可以不用考虑了。

    Term term;                //判断条件
    Behavior behavior;        //满足判断条件后，执行的行为

    public String toString() {
        return "Rule(" + insurer + "." + ruleId + ")";
    }

    boolean compute(QuoteDataBo quoteDataBo) throws Exception {
        boolean isMatched = false;
        CoverageBo item = null;

        if (coverageCode.equals(Profile.COVERAGE_ALL)) {
            isMatched = term.compute(null, quoteDataBo);
        } else {
            item = quoteDataBo.getCoverageByCode(coverageCode);
            if (item == null) {
//				throw new RuntimeException( toString() + ".coverageCode is error: " + coverageCode );
                return false;
            }
            isMatched = term.compute(item, quoteDataBo);

        }

        if (isMatched) {
            behavior.doing(item, quoteDataBo, desc);
            return true;
        }

        return false; //not handle
    }

}
