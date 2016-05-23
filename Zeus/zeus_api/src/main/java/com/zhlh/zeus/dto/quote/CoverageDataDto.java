package com.zhlh.zeus.dto.quote;

import com.zhlh.zeus.dto.BaseReqDto;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/9 01:18
 * @comment
 */
public class CoverageDataDto extends BaseReqDto {

    private String coverageCode; // 险种代码，应用层传值

    /**
     * 商改地区车损险保额为实际价值，非商改地区车损险保额为购置价。盗抢险，自燃险保额均为实际价值。Zeus层处理。
     * 三者，司机，乘客，划痕，由应用端传保额
     */
    private String amount; // 保额 amount = unitAmount * quantity
    private String quantity; // 数量。司机，乘客责任险需要，具体值由Zeus根据座位数计算
    private String unitAmount; // 单位保额。司机，乘客责任险需要，应用端传值
    private String modelCode; // 险种模式代码： 玻璃险 1:国产，2:进口。应用端传值
    private Integer isDeductibleChoice = 0; // 不计免赔，是否选择。应用端传值。若选择不计免赔险，则所有商业险此属性都为选中。

    private String kindCode; // TCI/VCI Zeus层自己处理

    // 核保
    private Integer isApprove = 1; // 核保是否通过
    private String reason; // 核保失败原因

    // 报价结果
    private String benchmarkPremium; // 折前保费
    private String premium; // 折后保费
    private String discount; // 折扣


    public String getCoverageCode() {
        return coverageCode;
    }

    public void setCoverageCode(String coverageCode) {
        this.coverageCode = coverageCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(String unitAmount) {
        this.unitAmount = unitAmount;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Integer getIsDeductibleChoice() {
        return isDeductibleChoice;
    }

    public void setIsDeductibleChoice(Integer isDeductibleChoice) {
        this.isDeductibleChoice = isDeductibleChoice;
    }

    public String getKindCode() {
        return kindCode;
    }

    public void setKindCode(String kindCode) {
        this.kindCode = kindCode;
    }

    public String getBenchmarkPremium() {
        return benchmarkPremium;
    }

    public void setBenchmarkPremium(String benchmarkPremium) {
        this.benchmarkPremium = benchmarkPremium;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
