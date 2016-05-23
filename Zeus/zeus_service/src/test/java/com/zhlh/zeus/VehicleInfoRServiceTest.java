package com.zhlh.zeus;

import com.zhlh.zeus.api.QuotePriceRService;
import com.zhlh.zeus.api.VehicleInfoRService;
import com.zhlh.zeus.dto.quote.CoverageDataDto;
import com.zhlh.zeus.dto.quote.QuotePriceReqDto;
import com.zhlh.zeus.dto.quote.QuotePriceResDto;
import com.zhlh.zeus.dto.vehicle.LocalVehicleResDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleReqDto;
import com.zhlh.zeus.dto.vehicle.QueryVehicleResDto;
import com.zhlh.zeus.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/9 23:52
 * @comment
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml",
        "classpath*:spring/applicationContext-*.xml"})
public class VehicleInfoRServiceTest extends AbstractJUnit4SpringContextTests {

    protected Logger logger = LoggerFactory.getLogger(VehicleInfoRServiceTest.class);

    @Autowired
    private VehicleInfoRService vehicleInfoRService;

    @Autowired
    private QuotePriceRService quotePriceRService;

    @Test
    public void testQueryLocalVehicle() throws Exception {

        LocalVehicleResDto localVehicleResDto = vehicleInfoRService.queryLocalVehicle("Ancar", "京QZ7Q09");

        QueryVehicleReqDto queryVehicleReqDto = new QueryVehicleReqDto();
        queryVehicleReqDto.setEngineNo(localVehicleResDto.getEngineNo());
        queryVehicleReqDto.setFrameNo(localVehicleResDto.getFrameNo());
        queryVehicleReqDto.setLicenseNo(localVehicleResDto.getLicenseNo());
        queryVehicleReqDto.setBrandName(localVehicleResDto.getBrandName());
        queryVehicleReqDto.setEnrollDate(localVehicleResDto.getEnrollDate());
        queryVehicleReqDto.setInsuCom("I00002");
        queryVehicleReqDto.setOwner("11");
        queryVehicleReqDto.setChannel("Ancar");

        QueryVehicleResDto queryVehicleResDto = vehicleInfoRService.queryVehicleInfo(queryVehicleReqDto);
        localVehicleResDto.getFrameNo();
    }

    @Test
    public void testQuotePrice() throws Exception {

        QuotePriceReqDto quotePriceReqDto = new QuotePriceReqDto();
//        quotePriceReqDto.setLicenseNo("浙BXF691");
//        quotePriceReqDto.setChannel("Ancar");
//        quotePriceReqDto.setBrandName("比亚迪QCJ7201E轿车");
//        quotePriceReqDto.setEngineNo("312006611");
//        quotePriceReqDto.setEnrollDate("2012-02-24");
//        quotePriceReqDto.setFrameNo("LGXC16CG5C1018677");
//        quotePriceReqDto.setOwner("丁白英");
//        quotePriceReqDto.setPurchasePrice("58500");

        quotePriceReqDto.setInsuCom("I00019");
        quotePriceReqDto.setChannel("Ancar");

        quotePriceReqDto.setLicenseNo("京QZ7Q09");
        quotePriceReqDto.setEngineNo("H536477");
        quotePriceReqDto.setFrameNo("LFMJ34AF8F3066547");
        quotePriceReqDto.setBrandName("丰田CA64604TME5多用途乘用车");
        quotePriceReqDto.setEnrollDate("2015-02-10");
        quotePriceReqDto.setOwner("屠辉");


//        quotePriceReqDto.setLicenseNo("京N36B07");
//        quotePriceReqDto.setEngineNo("27296730638888");
//        quotePriceReqDto.setFrameNo("WDCBB86E37A277101");
//        quotePriceReqDto.setBrandName("奔驰BENZ ML350越野车");
//        quotePriceReqDto.setEnrollDate("2007-08-27");
//        quotePriceReqDto.setOwner("薛成顺");


        List<CoverageDataDto> listCoverageBo = new ArrayList<CoverageDataDto>();

        CoverageDataDto coverageBo1 = new CoverageDataDto();
        coverageBo1.setCoverageCode("A");
        coverageBo1.setIsDeductibleChoice(0);

        listCoverageBo.add(coverageBo1);

        CoverageDataDto coverageBo2 = new CoverageDataDto();
        coverageBo2.setCoverageCode("BZ");
        coverageBo2.setIsDeductibleChoice(0);

        listCoverageBo.add(coverageBo2);

        CoverageDataDto coverageBo3 = new CoverageDataDto();
        coverageBo3.setCoverageCode("B");
        coverageBo3.setIsDeductibleChoice(0);
        coverageBo3.setAmount("300000");
        listCoverageBo.add(coverageBo3);

        quotePriceReqDto.setCoverageDataDtoList(listCoverageBo);

        QuotePriceResDto quotePriceResDto = quotePriceRService.quotePrice(quotePriceReqDto);
        logger.info(Util.toGson(quotePriceResDto));

    }

    @Test
    public void testQueryVehicleInfo() throws Exception {

        QueryVehicleReqDto quotePriceReqDto = new QueryVehicleReqDto();

        quotePriceReqDto.setInsuCom("I00027");
        quotePriceReqDto.setChannel("Ancar");


//        quotePriceReqDto.setLicenseNo("京N36B07");
//        quotePriceReqDto.setEngineNo("27296730638888");
//        quotePriceReqDto.setFrameNo("WDCBB86E37A277101");

        quotePriceReqDto.setLicenseNo("京QZ7Q09");
        quotePriceReqDto.setEngineNo("h536477");
        quotePriceReqDto.setFrameNo("LFMJ34AF8F3066547");


//        quotePriceReqDto.setLicenseNo("晋A4J112");
//        quotePriceReqDto.setBrandName("大众FV7207TRATG轿车");
//        quotePriceReqDto.setEngineNo("SAK8207");
//        quotePriceReqDto.setEnrollDate("2009-01-09");
//        quotePriceReqDto.setFrameNo("JM7ER09L4B0220516");
//        quotePriceReqDto.setOwner("金");
//        quotePriceReqDto.setSeatCount("7");

        QueryVehicleResDto queryVehicleResDto = vehicleInfoRService.queryVehicleInfo
                (quotePriceReqDto);
        logger.info(queryVehicleResDto.toString());

    }

}
