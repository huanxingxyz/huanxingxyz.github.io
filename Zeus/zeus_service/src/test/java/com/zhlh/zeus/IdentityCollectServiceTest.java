package com.zhlh.zeus;

import com.zhlh.zeus.api.IdentityCollectRService;
import com.zhlh.zeus.business.citycode.CityCodeManager;
import com.zhlh.zeus.dto.identityCollect.IdentityCollectReqDto;
import com.zhlh.zeus.dto.identityCollect.IdentityCollectResDto;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wjd19 on 2016/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class IdentityCollectServiceTest extends AbstractJUnit4SpringContextTests {

    protected Logger logger;

    @Autowired
    private IdentityCollectRService identityCollectRService;

    @Test
    public void Test() throws Exception {
        IdentityCollectReqDto identityCollectReqDto = new IdentityCollectReqDto();
        identityCollectReqDto.setChannel("Ancar");
        identityCollectReqDto.setInsuCom("I00002");

        identityCollectReqDto.setCertNo("310101198701016437");
        identityCollectReqDto.setName("屠辉");
        identityCollectReqDto.setMobile("18513849208");
//        identityCollectReqDto.setCheckCode("2j3lhl");
        identityCollectReqDto.setQuoteNo("SNAQ2016050319574826982108");
        identityCollectReqDto.setLicenseNo("京QZ7Q09");
//        identityCollectReqDto.setCertNo("210682198509153759");
//        identityCollectReqDto.setName("唐韬");
//        identityCollectReqDto.setMobile("18513849208");
//        identityCollectReqDto.setDriverName("唐韬");
//        identityCollectReqDto.setDriverCertNo("210682198509153759");
//        identityCollectReqDto.setCheckCode("w7mxp1");
//        identityCollectReqDto.setQuoteNo("SNAQ2016042313563628515395");

//        identityCollectReqDto.setCertNo("130682198801045712");
//        identityCollectReqDto.setName("王浩");
//        identityCollectReqDto.setMobile("18600270163");
//        identityCollectReqDto.setDriverName("薛成顺");
//        identityCollectReqDto.setFlowNo("864F7A97B3F3C11CC3A13859BFA8C52345571D127AE56A82");
//        identityCollectReqDto.setDriverCertNo("130682198801045712");
//        identityCollectReqDto.setQuoteNo("SNAQ2016050318482646611314");
//        identityCollectReqDto.setLicenseNo("京QZ7Q09");

        IdentityCollectResDto  identityCollectResDto;
        identityCollectResDto = identityCollectRService.identityCollect(identityCollectReqDto);


    }

    @Test
    public  void TestCityCode(){
        String licenseNo = "京N36B07";
        String cityCode = CityCodeManager.getCityCodeByLicense(licenseNo);
        System.out.print(cityCode);
    }
}
