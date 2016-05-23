package com.zhlh.zeus.business.citycode;

public class CityCode {
    String vehicleLicenseHeader;
    String code;
    String name;

    CityCode(String name, String code, String header) {
        if (code == null || header == null || code.trim().length() <= 0 || header.trim().length() <= 0) {
            throw new RuntimeException("CityCode format error");
        }

        this.name = name;
        this.code = code.trim();
        this.vehicleLicenseHeader = header.trim();


    }

}
