package com.zhlh.zeus;

import com.zhlh.zeus.api.IndividualAccountLossInsuRService;
import com.zhlh.zeus.dto.individualAccount.IndividualAccountReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

/**
 * Created by wjd19 on 2016/5/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class IndividualAccountRServiceTest {
    @Autowired
    IndividualAccountLossInsuRService individualAccountLossInsuRService;
    @Test
    public void TestIndividualAccountLossInsu(){
        IndividualAccountReqDto individualAccountReqDto = new IndividualAccountReqDto();
        individualAccountReqDto.setChannel("Ancar");
        individualAccountReqDto.setAppType("WX");
        individualAccountReqDto.setInsuredName("王嘉梁");
        individualAccountReqDto.setInsuredCertNo("230106198810070039");
        individualAccountReqDto.setInsuredMobile("18611749991");
        individualAccountReqDto.setInsureDate("2016-05-01");
        individualAccountReqDto.setAmount("10100");
        individualAccountReqDto.setPeriod(1);
        individualAccountReqDto.setPremium("0.9");
        individualAccountReqDto.setPurchaseFlag(0);

        individualAccountLossInsuRService.individualAccountLossInsurance(individualAccountReqDto);


    }

    @Test
    public void TestTime(){
         String  TransType_SN_Template ="ZHLH%1$s%2$tY%2$tm%2$td%3$09d";
         Random radomSeed = new Random();
         int i = radomSeed.nextInt(99999999);
       ;

        System.out.println(String.format(TransType_SN_Template, "req", System.currentTimeMillis(),Integer.valueOf(i)));

    }
}
