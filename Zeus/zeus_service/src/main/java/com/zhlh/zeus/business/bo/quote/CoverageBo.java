package com.zhlh.zeus.business.bo.quote;

import com.zhlh.zeus.util.Util;

public class CoverageBo {
    private String kindCode; // TCI/VCI
    private String coverageCode;

    private String amount; // 保额 amount = unitAmount * quantity
    private String quantity; // 数量
    private String unitAmount; // 单位保额
    private String modelCode; // 险种模式代码： 玻璃险 1:国产，2:进口
    private Integer isDeductibleChoice = 0; // 不计免赔，是否选择

    // for quota
    private String benchmarkPremium; // 折前保费
    private String premium; // 折后保费
    private String discount; // 折扣

    // 预核保
    private Integer isApprove = 1; // 核保是否通过
    private String reason; // 核保失败原因

    public CoverageBo() {

    }

    public CoverageBo(zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ResCoverageInfo quota) {
        isApprove = 1;
        isDeductibleChoice = 0;
        kindCode = quota.getPrdtKindCode();
        coverageCode = quota.getCoverageCode();
        try {
            if (quota.getDeductibleFlag().equalsIgnoreCase("Y")) {
                isDeductibleChoice = 1;
            }
            if ("M".equalsIgnoreCase(quota.getCoverageCode())) {
                isDeductibleChoice = 1;
            }
        } catch (Exception e1) {
            isDeductibleChoice = 0;
        }

        modelCode = Util.checkValue(quota.getModeCode(), "");
        unitAmount = Util.checkValue(quota.getUnitAmount(), "");
        quantity = Util.checkValue(quota.getQuantity(), "");
        amount = Util.checkValue(quota.getAmount(), "");
        benchmarkPremium = Util.checkValue(quota.getBenchmarkPremium(), "");
        premium = Util.checkValue(quota.getPremium(), "");
        discount = Util.checkValue(quota.getDiscount(), "");

    }

    /**
     * 是否通过预核保
     */
    public boolean isApprove() {
        if (isApprove == null || isApprove == 0) {
            return false;
        }
        return true;
    }

    public zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCoverageInfo toReqCoverageInfo() {
        if (!isApprove()) {
            return null;
        }

        zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCoverageInfo info = new zhlh.anbox.aitp.aiws.xmlbeans.quoteprice.ReqCoverageInfo();
        info.setPrdtKindCode(kindCode);
        info.setCoverageCode(coverageCode);
        info.setDeductibleFlag((isDeductibleChoice != null && isDeductibleChoice == 1) ? "Y" : "N");

        if (amount != null) {
            info.setAmount(amount);
        }

        if (quantity != null) {
            info.setQuantity(quantity);
        }

        if (unitAmount != null) {
            info.setUnitAmount(unitAmount);
        }

        if (modelCode != null) {
            info.setModeCode(modelCode);
        }

        return info;
    }


    public void setConfirmed(Integer isApprove, String reason) {
        this.isApprove = isApprove;

        if (reason != null) {
            this.reason = reason;
        }
    }

    public void setDeductible(Integer isDeductibleChoice, String msg) {
        this.isDeductibleChoice = isDeductibleChoice;

        if (msg != null) {
            reason = msg;
        }
    }

    public String getKindCode() {
        return kindCode;
    }

    public void setKindCode(String kindCode) {
        this.kindCode = kindCode;
    }

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
