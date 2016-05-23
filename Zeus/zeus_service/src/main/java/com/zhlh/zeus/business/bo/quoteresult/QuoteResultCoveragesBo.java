package com.zhlh.zeus.business.bo.quoteresult;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/13
 */
public class QuoteResultCoveragesBo {

    private String coverageCode; // 险种编号
    private String premium; // 保费

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getCoverageCode() {
        return coverageCode;
    }

    public void setCoverageCode(String coverageCode) {
        this.coverageCode = coverageCode;
    }
}
