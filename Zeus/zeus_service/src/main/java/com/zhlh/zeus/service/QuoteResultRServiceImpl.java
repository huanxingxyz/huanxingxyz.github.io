package com.zhlh.zeus.service;

import com.zhlh.zeus.api.QuoteResultRService;
import com.zhlh.zeus.business.bo.quoteresult.QuoteResultBo;
import com.zhlh.zeus.business.bo.quoteresult.QuoteResultCoveragesBo;
import com.zhlh.zeus.dto.quoteresult.CoveragesDto;
import com.zhlh.zeus.dto.quoteresult.QuoteResultReqDto;
import com.zhlh.zeus.dto.quoteresult.QuoteResultResDto;
import com.zhlh.zeus.exception.ExceptionHelper;
import com.zhlh.zeus.rservice.QuoteResultRemoteService;
import org.springframework.stereotype.Service;

import java.util.Vector;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/12
 */
@Service("quoteResultRService")
public class QuoteResultRServiceImpl implements QuoteResultRService {

    @Override
    public QuoteResultResDto quoteResult(QuoteResultReqDto quoteResultReqDto)  {

        QuoteResultResDto quoteResultResDto = new QuoteResultResDto();

        if (quoteResultReqDto == null|| quoteResultReqDto.getQuoteNo() == null){
            quoteResultResDto.setErrCode(-1);
            quoteResultResDto.setErrMsg("请求参数不合法");

            return quoteResultResDto;
        }

        // 请求REMEX服务
        QuoteResultBo quoteResultBo = null;
        try {
            quoteResultBo = QuoteResultRemoteService.queryRemoteQuoteReult(quoteResultReqDto);
        } catch (Exception e) {
            ExceptionHelper.transErrorMsg(e, quoteResultResDto);
            return quoteResultResDto;
        }

        // 返回arthas 对象赋值
        Vector<CoveragesDto> coverages = new Vector<CoveragesDto>();
        for (QuoteResultCoveragesBo quoteResultCoveragesBo : quoteResultBo.getCoverages()){
            CoveragesDto coveragesDto = new CoveragesDto();
            coveragesDto.setCoverageCode(quoteResultCoveragesBo.getCoverageCode());
            coveragesDto.setPremium(quoteResultCoveragesBo.getPremium());
            coverages.add(coveragesDto);
        }
        quoteResultResDto.setCoverages(coverages);
        quoteResultResDto.setPremium(quoteResultBo.getPremium());
        quoteResultResDto.setQuoteNo(quoteResultBo.getQuoteNo());
        quoteResultResDto.setSumTravelTax(quoteResultBo.getSumTravelTax());
        quoteResultResDto.setAppCertNo(quoteResultBo.getAppCertNo());
        quoteResultResDto.setAppName(quoteResultBo.getAppName());
        quoteResultResDto.setInsuCom(quoteResultBo.getInsuCom());
        quoteResultResDto.setLicenseNo(quoteResultBo.getLicenseNo());
        quoteResultResDto.setOrderNo(quoteResultBo.getOrderNo());
        quoteResultResDto.setOwnerCertNo(quoteResultBo.getOwnerCertNo());
        quoteResultResDto.setOwnerName(quoteResultBo.getOwnerName());
        quoteResultResDto.setTciPremium(quoteResultBo.getTciPremium());
        quoteResultResDto.setVciPremium(quoteResultBo.getVciPremium());

        return quoteResultResDto;
    }
}
