package com.zhlh.zeus.dto.insureCity;

import com.zhlh.zeus.dto.BaseResDto;

import java.util.List;

/**
 * Created by MT on 16/5/9.
 */
public class InsurerCityResDto extends BaseResDto{
    private List<InsurerCity> insurerCityList;

    public List<InsurerCity> getInsurerCityList() {
        return insurerCityList;
    }

    public void setInsurerCityList(List<InsurerCity> insurerCityList) {
        this.insurerCityList = insurerCityList;
    }
}
