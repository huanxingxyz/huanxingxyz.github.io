package com.zhlh.zeus.dto.quoteresult;

import java.io.Serializable;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/12
 */
public class CoveragesDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
