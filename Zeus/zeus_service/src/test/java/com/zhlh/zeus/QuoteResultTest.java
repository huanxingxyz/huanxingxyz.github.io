package com.zhlh.zeus;

import com.zhlh.zeus.api.QuoteResultRService;
import com.zhlh.zeus.dto.quoteresult.QuoteResultReqDto;
import com.zhlh.zeus.dto.quoteresult.QuoteResultResDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class QuoteResultTest {

    @Autowired
    private QuoteResultRService quoteResultRService;

    @Test
    public void TestQuoteResult(){

        QuoteResultReqDto quoteResultReqDto = new QuoteResultReqDto();
        quoteResultReqDto.setQuoteNo("SNAQ2016011517495626524749");
        QuoteResultResDto quoteResultResDto = new QuoteResultResDto();
        try {
            quoteResultResDto = quoteResultRService.quoteResult(quoteResultReqDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(quoteResultResDto.getQuoteNo());
    }
}
