package com.zhlh.zeus.business.bo.quote;

import com.zhlh.zeus.business.bo.vehicle.VehicleInfoBo;
import com.zhlh.zeus.util.Profile;

import java.util.ArrayList;
import java.util.List;

public class QuoteDataBo {

    private String licenseNo; // 车牌号
    private String frameNo; // 车架号
    private String engineNo; // 发动机号
    private String enrollDate; // 车辆初始登记日期
    private String brandName; // 厂牌型号

    private VehicleInfoBo vehicleInfo;

    private String tciDefaultStartDate; // 默认交强险上年止期, 从Remex SOA获得
    private String vciDefaultStartDate; // 默认商业险上年止期, 从Remex SOA获取
    private String tciStartDate; // 为查询实际价值的参数，可能是用户修改过的时间
    private String vciStartDate; // 为查询实际价值的参数，可能是用户修改过的时间

    private String actualValue; // 实际价值, 从Remex SOA获取
    private String purchasePrice; // 新车购置价

    // for quote request
    private CustomerBo ownerData; // 车主信息
    private CustomerBo insurantData; // 被保人信息
    private CustomerBo policyHolderData; // 投保人信息

    private String cityCode; // 投保城市

    private List<CoverageBo> coverageList = new ArrayList<CoverageBo>();

    // for quota
    private String quoteNo; // 询价单号
    private String insuCom; // 投保公司
    private String execCom; // 保险公司
    private String benchmarkPremium; // 折前保费
    private String premium; // 折后保费
    private String tciPremium; // 交强险折后保费
    private String vciPremium; // 商业险折后保费
    private String discount; // 折扣
    private String sumTravelTax; // 总车船税

    // 人保保费计算
    private String uniqueId;

    // 阳光，富德
    private  String flowNo;

    // 核保
    private String insuComQuoteNo; // 保险公司询价单号


    public boolean isAvailableForQuote() {
        boolean haveSelected = false;
        for (CoverageBo one : coverageList) {
            if (one.getIsApprove() == 1) {
                return true;
            }
        }

        return haveSelected;
    }

    public boolean isChoiseTCI() {
        for (CoverageBo item : coverageList) {
            if (Profile.COVERAGE_JIAOQIANG.equals(item.getCoverageCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean isChoiseVCI() {
        for (CoverageBo item : coverageList) {
            if (!Profile.COVERAGE_JIAOQIANG.equals(item.getCoverageCode())) {
                return true;
            }
        }

        return false;
    }

    public CoverageBo getCoverageByCode(String code) {
        for (CoverageBo coverageBo : coverageList) {
            if (coverageBo.getCoverageCode().equals(code)) {
                return coverageBo;
            }
        }
        return null;
    }

    public void setConfirmed(Integer flag, String msg) {
        for (CoverageBo item : coverageList) {
            item.setConfirmed(flag, msg);
        }
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTciDefaultStartDate() {
        return tciDefaultStartDate;
    }

    public void setTciDefaultStartDate(String tciDefaultStartDate) {
        this.tciDefaultStartDate = tciDefaultStartDate;
    }

    public String getVciDefaultStartDate() {
        return vciDefaultStartDate;
    }

    public void setVciDefaultStartDate(String vciDefaultStartDate) {
        this.vciDefaultStartDate = vciDefaultStartDate;
    }

    public String getTciStartDate() {
        return tciStartDate;
    }

    public void setTciStartDate(String tciStartDate) {
        this.tciStartDate = tciStartDate;
    }

    public String getVciStartDate() {
        return vciStartDate;
    }

    public void setVciStartDate(String vciStartDate) {
        this.vciStartDate = vciStartDate;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public CustomerBo getOwnerData() {
        return ownerData;
    }

    public void setOwnerData(CustomerBo ownerData) {
        this.ownerData = ownerData;
    }

    public CustomerBo getInsurantData() {
        return insurantData;
    }

    public void setInsurantData(CustomerBo insurantData) {
        this.insurantData = insurantData;
    }

    public CustomerBo getPolicyHolderData() {
        return policyHolderData;
    }

    public void setPolicyHolderData(CustomerBo policyHolderData) {
        this.policyHolderData = policyHolderData;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<CoverageBo> getCoverageList() {
        return coverageList;
    }

    public void setCoverageList(List<CoverageBo> coverageList) {
        this.coverageList = coverageList;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public String getInsuCom() {
        return insuCom;
    }

    public void setInsuCom(String insuCom) {
        this.insuCom = insuCom;
    }

    public String getExecCom() {
        return execCom;
    }

    public void setExecCom(String execCom) {
        this.execCom = execCom;
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

    public String getTciPremium() {
        return tciPremium;
    }

    public void setTciPremium(String tciPremium) {
        this.tciPremium = tciPremium;
    }

    public String getVciPremium() {
        return vciPremium;
    }

    public void setVciPremium(String vciPremium) {
        this.vciPremium = vciPremium;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSumTravelTax() {
        return sumTravelTax;
    }

    public void setSumTravelTax(String sumTravelTax) {
        this.sumTravelTax = sumTravelTax;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public VehicleInfoBo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfoBo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getInsuComQuoteNo() {
        return insuComQuoteNo;
    }

    public void setInsuComQuoteNo(String insuComQuoteNo) {
        this.insuComQuoteNo = insuComQuoteNo;
    }
}
