package com.zhlh.zeus;

import com.zhlh.zeus.dto.BaseResDto;
import com.zhlh.zeus.exception.ExceptionHelper;
import com.zhlh.zeus.exception.RemexServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class InsurerErrTest {
    @Test
    public void insurerErrTest(){

        BaseResDto baseResDto = new BaseResDto();
        RemexServiceException remexServiceException = new RemexServiceException("新能源车辆应当以车船税标志“M”上传");
        ExceptionHelper.transErrorMsg(remexServiceException, baseResDto);
    }
}
