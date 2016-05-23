package com.zhlh.zeus;

import com.zhlh.zeus.api.LuobarPayRService;
import com.zhlh.zeus.dto.pay.LuoberPayReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wjd19 on 2016/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class LuoberPayRServiceTest {
    @Autowired
    LuobarPayRService luobarPayRService;
    @Test
    public void TestLuoberPay(){
        LuoberPayReqDto luoberPayReqDto = new LuoberPayReqDto();
        luoberPayReqDto.setChannel("Ancar");
        luoberPayReqDto.setInsuCom("I00002");
        luoberPayReqDto.setBizNo("SNPO2016042616004780400222");//productOrder表主键
        luoberPayReqDto.setBizOrderId("SNAP2016042616005415198224");//Remex平台AtinPolicy主键
        luoberPayReqDto.setChargeComCode("WX");
        luoberPayReqDto.setOrderPremium("0.01");
        luoberPayReqDto.setRcvMobile("18513849208");
        luoberPayReqDto.setRcvName("刘天元");
        luoberPayReqDto.setRcvPostAddress("方正国际大厦1501");
        luoberPayReqDto.setTradeType("JSAPI");//NATIVE
//        luoberPayReqDto.setUserId("ojW_EvjHAJDfTggbXBb7s1DYUTFY");
//        luoberPayReqDto.setUserId( "onbnBvgXyInzL2vE-aM1S0ETwwMU");
        luoberPayReqDto.setUserId( "onbnBvgXyInzL2vE-aM1S0ETwwMU");
        luoberPayReqDto.setLicenseNo("京QZ7Q09");
        luobarPayRService.luoberPay(luoberPayReqDto);
    }
}
