package com.zhlh.zeus.business.puw;


import com.zhlh.zeus.business.bo.quote.CoverageBo;
import com.zhlh.zeus.business.bo.quote.QuoteDataBo;
import com.zhlh.zeus.util.Util;

public class Term {
    final public static String Operator_OR = "OR"; //和上级 '或' 操作
    final public static String Operator_AND = "AND"; //和上级 '与' 操作

    final public static String Function_Not_Exist = "Not_Exist"; //Not_Exist方法 ：判断是否不存在，某个险种，Not_Exist B

    final public static String Expression_purchasePrice = "purchasePrice";  //新车购置价
    final public static String Expression_amount = "amount";                //某个险种的保额
    final public static String Expression_vehicleAge = "vehicleAge";        //车龄

    String function;    //函数表达式: <Function> <Param>  如："Not_Exist B":没有选择 “三者险“
    String expression;    //公式表达式  purchasePrice > 2000000

    Term OR;            //相关联的下级条件, 或关系
    Term AND;            //相关联的下级条件， 与关系

    boolean compute(CoverageBo item, QuoteDataBo puw) throws Exception {
        boolean result = false;

        //compute self
        if (function != null) {
            result = computeFunction(item, puw);
        } else if (expression != null) {
            result = computeExpression(item, puw);
        } else {
            result = true;
        }


        //compile with OR / AND
        if (OR != null) {
            result = result | OR.compute(item, puw);
        } else if (AND != null) {
            result = result & AND.compute(item, puw);
        }

        return result;
    }

    private boolean computeFunction(CoverageBo item, QuoteDataBo puw) throws Exception {
        String words[] = function.split(" ");
        if (words[0].equals(Function_Not_Exist)) {
            CoverageBo target = puw.getCoverageByCode(words[1]);
            if (target == null) {
                return true;
            }

            return false;
        } else {
            throw new RuntimeException("Term.function is not defined : " + function);
        }

    }

    private boolean computeExpression(CoverageBo item, QuoteDataBo puw) throws Exception {
        String words[] = expression.split(" ");
        float valueLeft;

        if (words[0].equals(Expression_purchasePrice)) {
            valueLeft = Float.parseFloat(puw.getPurchasePrice());
        } else if (words[0].equals(Expression_amount)) {
            valueLeft = Float.parseFloat(item.getAmount());
        } else if (words[0].equals(Expression_vehicleAge)) {
            String vicStartDate = puw.getVciStartDate();
            if(vicStartDate == null){
                vicStartDate = puw.getVciDefaultStartDate();
            }
            int diff[] = Util.diffDates(Util.stringToDate(puw.getEnrollDate()), Util.stringToDate
                    (vicStartDate));
            // TODO vicStartDate为用户修改过的时间，可能为空，puw之前先处理一下
            valueLeft = diff[0];
            if (diff[1] >= 200) {
                valueLeft = valueLeft + 1;
            }
        } else {
            throw new RuntimeException("Term.expression's leftValue is not defined : " + expression);
        }

        float valueRight;
        try {
            valueRight = Float.parseFloat(words[2]);
        } catch (Exception e) {
            throw new RuntimeException("Term.expression's rightValue is not defined : " + expression);
        }

        if (words[1].equals(">")) {
            return valueLeft > valueRight;
        } else if (words[1].equals("<")) {
            return valueLeft < valueRight;
        } else if (words[1].equals("==")) {
            return valueLeft == valueRight;
        } else if (words[1].equals(">=")) {
            return valueLeft >= valueRight;
        } else if (words[1].equals("<=")) {
            return valueLeft != valueRight;
        } else if (words[1].equals("!=")) {
            return valueLeft != valueRight;
        } else {
            throw new RuntimeException("Term.expression's operator is not defined : " + expression);
        }

        //return false;
    }

}



/* 
term{
	operator:NONE
	condition:Not_Exist B
	expression:purchasePrice > 2000000
	OR{
		expression:B.amount < 1000000	
	}
}
*/