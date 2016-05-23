package com.zhlh.zeus.service;

import com.zhlh.Tiny.util.CommonUtil;
import com.zhlh.Tiny.util.JsonUtil;
import com.zhlh.zeus.api.InsureCityRService;
import com.zhlh.zeus.dto.insureCity.InsurerCity;
import com.zhlh.zeus.dto.insureCity.InsurerCityResDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MT on 16/5/9.
 */
@Service("insureCityRService")
public class InsureCityRServiceImpl implements InsureCityRService {
    public static String allInsureCityJsonStr = "[{\"cityCode\":\"110000\",\"cityName\":\"北京\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"京\",\"type\":\"Traditional\"},{\"cityCode\":\"440100\",\"cityName\":\"广东\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"粤\",\"type\":\"Traditional\"},{\"cityCode\":\"210100\",\"cityName\":\"辽宁\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"辽\",\"type\":\"Traditional\"},{\"cityCode\":\"420100\",\"cityName\":\"湖北\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"鄂\",\"type\":\"Traditional\"},{\"cityCode\":\"410100\",\"cityName\":\"河南\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"豫\",\"type\":\"Traditional\"},{\"cityCode\":\"520100\",\"cityName\":\"四川\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"川\",\"type\":\"Traditional\"},{\"cityCode\":\"220100\",\"cityName\":\"吉林\",\"insuCom\":\"I00001\",\"shortLicenseNo\":\"吉\",\"type\":\"Traditional\"},{\"cityCode\":\"110000\",\"cityName\":\"北京\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"京\",\"type\":\"Traditional\"},{\"cityCode\":\"130100\",\"cityName\":\"河北\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"冀\",\"type\":\"Traditional\"},{\"cityCode\":\"440100\",\"cityName\":\"广东\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"粤\",\"type\":\"Traditional\"},{\"cityCode\":\"440300\",\"cityName\":\"深圳\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"粤B\",\"type\":\"Traditional\"},{\"cityCode\":\"310100\",\"cityName\":\"陕西\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"陕\",\"type\":\"Traditional\"},{\"cityCode\":\"370100\",\"cityName\":\"山东\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"鲁\",\"type\":\"Traditional\"},{\"cityCode\":\"210100\",\"cityName\":\"辽宁\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"辽\",\"type\":\"Traditional\"},{\"cityCode\":\"420100\",\"cityName\":\"湖北\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"鄂\",\"type\":\"Traditional\"},{\"cityCode\":\"140100\",\"cityName\":\"山西\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"晋\",\"type\":\"Traditional\"},{\"cityCode\":\"410100\",\"cityName\":\"河南\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"豫\",\"type\":\"Traditional\"},{\"cityCode\":\"150100\",\"cityName\":\"内蒙古\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"蒙\",\"type\":\"Traditional\"},{\"cityCode\":\"430100\",\"cityName\":\"湖南\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"湘\",\"type\":\"Traditional\"},{\"cityCode\":\"370200\",\"cityName\":\"青岛\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"鲁B\",\"type\":\"Traditional\"},{\"cityCode\":\"350200\",\"cityName\":\"厦门\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"闽D\",\"type\":\"Traditional\"},{\"cityCode\":\"350100\",\"cityName\":\"福建\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"闵\",\"type\":\"Traditional\"},{\"cityCode\":\"310000\",\"cityName\":\"上海\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"沪\",\"type\":\"Traditional\"},{\"cityCode\":\"520100\",\"cityName\":\"四川\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"川\",\"type\":\"Traditional\"},{\"cityCode\":\"230100\",\"cityName\":\"黑龙江\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"黑\",\"type\":\"Traditional\"},{\"cityCode\":\"330100\",\"cityName\":\"浙江\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"浙\",\"type\":\"Traditional\"},{\"cityCode\":\"330206\",\"cityName\":\"宁波\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"浙B\",\"type\":\"Traditional\"},{\"cityCode\":\"330302\",\"cityName\":\"温州\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"温\",\"type\":\"Traditional\"},{\"cityCode\":\"340100\",\"cityName\":\"安徽\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"皖\",\"type\":\"Traditional\"},{\"cityCode\":\"320100\",\"cityName\":\"江苏\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"苏\",\"type\":\"Traditional\"},{\"cityCode\":\"320502\",\"cityName\":\"苏州\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"苏E\",\"type\":\"Traditional\"},{\"cityCode\":\"210200\",\"cityName\":\"大连\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"辽B\",\"type\":\"Traditional\"},{\"cityCode\":\"530100\",\"cityName\":\"云南\",\"insuCom\":\"I00002\",\"shortLicenseNo\":\"云\",\"type\":\"Traditional\"},{\"cityCode\":\"110000\",\"cityName\":\"北京\",\"insuCom\":\"I00003\",\"shortLicenseNo\":\"京\",\"type\":\"NetSales\"},{\"cityCode\":\"110000\",\"cityName\":\"北京\",\"insuCom\":\"I00004\",\"shortLicenseNo\":\"京\",\"type\":\"Traditional\"},{\"cityCode\":\"110000\",\"cityName\":\"北京\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"京\",\"type\":\"NetSales\"},{\"cityCode\":\"130100\",\"cityName\":\"河北\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"冀\",\"type\":\"NetSales\"},{\"cityCode\":\"440100\",\"cityName\":\"广东\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"粤\",\"type\":\"NetSales\"},{\"cityCode\":\"440300\",\"cityName\":\"深圳\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"粤B\",\"type\":\"NetSales\"},{\"cityCode\":\"310100\",\"cityName\":\"陕西\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"陕\",\"type\":\"NetSales\"},{\"cityCode\":\"370100\",\"cityName\":\"山东\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"鲁\",\"type\":\"NetSales\"},{\"cityCode\":\"210100\",\"cityName\":\"辽宁\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"辽\",\"type\":\"NetSales\"},{\"cityCode\":\"420100\",\"cityName\":\"湖北\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"鄂\",\"type\":\"NetSales\"},{\"cityCode\":\"140100\",\"cityName\":\"山西\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"晋\",\"type\":\"NetSales\"},{\"cityCode\":\"410100\",\"cityName\":\"河南\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"豫\",\"type\":\"NetSales\"},{\"cityCode\":\"150100\",\"cityName\":\"内蒙古\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"蒙\",\"type\":\"NetSales\"},{\"cityCode\":\"430100\",\"cityName\":\"湖南\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"湘\",\"type\":\"NetSales\"},{\"cityCode\":\"370200\",\"cityName\":\"青岛\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"鲁B\",\"type\":\"NetSales\"},{\"cityCode\":\"350200\",\"cityName\":\"厦门\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"闽D\",\"type\":\"NetSales\"},{\"cityCode\":\"350100\",\"cityName\":\"福建\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"闵\",\"type\":\"NetSales\"},{\"cityCode\":\"310000\",\"cityName\":\"上海\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"沪\",\"type\":\"NetSales\"},{\"cityCode\":\"520100\",\"cityName\":\"四川\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"川\",\"type\":\"NetSales\"},{\"cityCode\":\"230100\",\"cityName\":\"黑龙江\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"黑\",\"type\":\"NetSales\"},{\"cityCode\":\"330100\",\"cityName\":\"浙江\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"浙\",\"type\":\"NetSales\"},{\"cityCode\":\"330206\",\"cityName\":\"宁波\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"浙B\",\"type\":\"NetSales\"},{\"cityCode\":\"330302\",\"cityName\":\"温州\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"温\",\"type\":\"NetSales\"},{\"cityCode\":\"340100\",\"cityName\":\"安徽\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"皖\",\"type\":\"NetSales\"},{\"cityCode\":\"320100\",\"cityName\":\"江苏\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"苏\",\"type\":\"NetSales\"},{\"cityCode\":\"320502\",\"cityName\":\"苏州\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"苏E\",\"type\":\"NetSales\"},{\"cityCode\":\"210200\",\"cityName\":\"大连\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"辽B\",\"type\":\"NetSales\"},{\"cityCode\":\"530100\",\"cityName\":\"云南\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"云\",\"type\":\"NetSales\"},{\"cityCode\":\"120000\",\"cityName\":\"天津\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"津\",\"type\":\"NetSales\"},{\"cityCode\":\"450100\",\"cityName\":\"广西\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"桂\",\"type\":\"NetSales\"},{\"cityCode\":\"220100\",\"cityName\":\"吉林\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"吉\",\"type\":\"NetSales\"},{\"cityCode\":\"500100\",\"cityName\":\"重庆\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"渝\",\"type\":\"NetSales\"},{\"cityCode\":\"360100\",\"cityName\":\"江西\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"赣\",\"type\":\"NetSales\"},{\"cityCode\":\"460100\",\"cityName\":\"海南\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"琼\",\"type\":\"NetSales\"},{\"cityCode\":\"520000\",\"cityName\":\"贵州\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"贵\",\"type\":\"NetSales\"},{\"cityCode\":\"620100\",\"cityName\":\"甘肃\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"甘\",\"type\":\"NetSales\"},{\"cityCode\":\"540100\",\"cityName\":\"西藏\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"藏\",\"type\":\"NetSales\"},{\"cityCode\":\"650100\",\"cityName\":\"新疆\",\"insuCom\":\"I00019\",\"shortLicenseNo\":\"新\",\"type\":\"NetSales\"},{\"cityCode\":\"110000\",\"cityName\":\"北京\",\"insuCom\":\"I00027\",\"shortLicenseNo\":\"京\",\"type\":\"NetSales\"}]";


    @Override
    public InsurerCityResDto getAllInsurerCityList() {
        List<InsurerCity> allInsurerCityList = JsonUtil.jsonToBeanList(allInsureCityJsonStr, InsurerCity.class);
        InsurerCityResDto insurerCityResDto = new InsurerCityResDto();
        insurerCityResDto.setInsurerCityList(allInsurerCityList);
        return insurerCityResDto;
    }

    @Override
    public InsurerCityResDto supportInsureList(String licenseNo) {
        List<InsurerCity> allInsurerCityList = JsonUtil.jsonToBeanList(allInsureCityJsonStr, InsurerCity.class);

        // TODO: 16/5/9 数据源更改
        Map<String, List<InsurerCity>> insuComCityListMap = new HashMap<>();
        for (InsurerCity insuComCity : allInsurerCityList) {
            List<InsurerCity> insuComCityList = insuComCityListMap.get(insuComCity.getShortLicenseNo());
            if (CommonUtil.isEmpty(insuComCityList)) {
                insuComCityList = new ArrayList<>();
            }
            insuComCityList.add(insuComCity);
            insuComCityListMap.put(insuComCity.getShortLicenseNo(), insuComCityList);
        }

        String shortLicenseNo = licenseNo.substring(0, 2);
        List<InsurerCity> insuComCityList = insuComCityListMap.get(shortLicenseNo);
        if (CommonUtil.isEmpty(insuComCityList)) {
            shortLicenseNo = licenseNo.substring(0, 1);
            insuComCityList = insuComCityListMap.get(shortLicenseNo);
        }

        InsurerCityResDto insurerCityResDto = new InsurerCityResDto();
        insurerCityResDto.setInsurerCityList(insuComCityList);
        return insurerCityResDto;
    }
}

