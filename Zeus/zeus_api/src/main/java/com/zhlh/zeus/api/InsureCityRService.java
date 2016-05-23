package com.zhlh.zeus.api;

import com.zhlh.zeus.dto.insureCity.InsurerCityResDto;

import java.util.List;

/**
 * Created by MT on 16/5/9.
 */
public interface InsureCityRService {
    InsurerCityResDto getAllInsurerCityList();

    InsurerCityResDto supportInsureList(String licenseNo);

}
