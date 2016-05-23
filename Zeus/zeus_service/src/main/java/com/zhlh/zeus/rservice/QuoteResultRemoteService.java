package com.zhlh.zeus.rservice;

import com.zhlh.zeus.business.bo.quoteresult.QuoteResultBo;
import com.zhlh.zeus.business.bo.quoteresult.QuoteResultCoveragesBo;
import com.zhlh.zeus.constant.RemexConstants;
import com.zhlh.zeus.dto.quoteresult.QuoteResultReqDto;
import com.zhlh.zeus.exception.ServiceException;
import com.zhlh.zeus.framework.soa.SoaService;
import zhlh.anbox.aitp.aiws.xmlbeans.queryquoteresult.Coverages;
import zhlh.anbox.aitp.aiws.xmlbeans.queryquoteresult.ReqQueryQuoteResult;
import zhlh.anbox.aitp.aiws.xmlbeans.queryquoteresult.ResQueryQuoteResult;

import java.util.Vector;

/**
 * @author LiuZhaohong    liuzh@qqbx.com.cn
 * @version v1.0    2016/4/12
 */
public class QuoteResultRemoteService {

    /**
     * 查询报价结果
     *
     * @param quoteResultReqDto
     * @return
     * @throws Exception
     */
    public static QuoteResultBo queryRemoteQuoteReult(QuoteResultReqDto quoteResultReqDto) throws
            Exception {
        if (quoteResultReqDto == null) {
            throw new ServiceException("非法参数");
        }

        //构造SOA请求参数
        ReqQueryQuoteResult reqQueryQuoteResult = new ReqQueryQuoteResult();
        reqQueryQuoteResult.setQuoteNo(quoteResultReqDto.getQuoteNo());

        //SOA请求REMEX
        ResQueryQuoteResult resQueryQuoteResult = (ResQueryQuoteResult) SoaService.invokeService("Ancar",
                RemexConstants.AITP_QUERYQUOTERESULT, "queryQuoteResult", reqQueryQuoteResult, "001");

        if (resQueryQuoteResult == null) {
            throw new ServiceException("没有查询到询价结果");
        }

        //构造SOA返回参数
        QuoteResultBo quoteResultBo = new QuoteResultBo();
        Vector<QuoteResultCoveragesBo> coverages = new Vector<QuoteResultCoveragesBo>();
        for (Coverages coverage : resQueryQuoteResult.getCoverages()) {
            QuoteResultCoveragesBo quoteResultCoveragesBo = new QuoteResultCoveragesBo();
            quoteResultCoveragesBo.setCoverageCode(coverage.getCoverageCode());
            quoteResultCoveragesBo.setPremium(coverage.getPremium());
            coverages.add(quoteResultCoveragesBo);
        }
        quoteResultBo.setCoverages(coverages);
        quoteResultBo.setPremium(resQueryQuoteResult.getPremium());
        quoteResultBo.setQuoteNo(resQueryQuoteResult.getQuoteNo());
        quoteResultBo.setSumTravelTax(resQueryQuoteResult.getSumTravelTax());
        quoteResultBo.setAppCertNo(resQueryQuoteResult.getAppCertNo());
        quoteResultBo.setAppName(resQueryQuoteResult.getAppName());
        quoteResultBo.setInsuCom(resQueryQuoteResult.getInsuCom());
        quoteResultBo.setLicenseNo(resQueryQuoteResult.getLicenseNo());
        quoteResultBo.setOrderNo(resQueryQuoteResult.getOrderNo());
        quoteResultBo.setOwnerCertNo(resQueryQuoteResult.getOwnerCertNo());
        quoteResultBo.setOwnerName(resQueryQuoteResult.getOwnerName());
        quoteResultBo.setTciPremium(resQueryQuoteResult.getTciPremium());
        quoteResultBo.setVciPremium(resQueryQuoteResult.getVciPremium());

        return quoteResultBo;
    }
}
