package com.zhlh.zeus;

import com.zhlh.zeus.api.InsureConfirmRService;
import com.zhlh.zeus.dto.insureConfirm.InsureConfirmReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wjd19 on 2016/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class InsureConfirmRServiceTest {

        @Autowired
        private   InsureConfirmRService insureConfirmRService;
        @Test
        public void InsureConfirmTest(){
                InsureConfirmReqDto insureConfirmReqDto = new InsureConfirmReqDto();
                insureConfirmReqDto.setChannel("Ancar");
                insureConfirmReqDto.setLicenseNo("京QZ7Q09");
                insureConfirmReqDto.setInsuCom("I00002");
                insureConfirmReqDto.setFlowNo("765AA6D8B78DF236A93C12764386B788860B8FD7EDEA33B5");
                insureConfirmReqDto.setQuoteNo("SNAQ2016042611004805084510");
                insureConfirmReqDto.setInsuComApplicationNo("B110920160426105738633");
                insureConfirmReqDto.setPostAddr("北京市海淀区中关村方正国际大厦1501室");
                insureConfirmReqDto.setPostMobile("18513849208");
                insureConfirmReqDto.setPostName("屠辉");
                insureConfirmReqDto.setVerificationCode("2j3lhl");

                insureConfirmRService.insureConfirm(insureConfirmReqDto);


        }

}


