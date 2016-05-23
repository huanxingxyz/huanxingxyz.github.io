package com.zhlh.zeus.service;

import cn.remex.reflect.ReflectUtil;
import cn.remex.util.Arith;
import com.zhlh.zeus.api.LuobarPayRService;
import com.zhlh.zeus.constant.ApiConstants;
import com.zhlh.zeus.constant.RemexConstants;
import com.zhlh.zeus.dto.pay.LuoberPayReqDto;
import com.zhlh.zeus.dto.pay.LuoberPayResDto;
import com.zhlh.zeus.exception.ErrorCode;
import com.zhlh.zeus.framework.soa.SoaService;
import com.zhlh.zeus.util.Util;
import org.springframework.stereotype.Service;
import zhlh.anbox.cpsp.chargews.xmlbeans.businessextend.ReqBusinessExtend;
import zhlh.anbox.cpsp.chargews.xmlbeans.unifyplaceorder.ReqUnifyOrderProduct;
import zhlh.anbox.cpsp.chargews.xmlbeans.unifyplaceorder.ReqUnifyPlaceOrder;
import zhlh.anbox.cpsp.chargews.xmlbeans.unifyplaceorder.ResUnifyPlaceOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjiadong
 * @version 1.0
 * @date 创建时间：16/4/23
 * @comment 支付服务
 */
@Service("luoberPayRService")
public class LuoberPayRServiceImpl implements LuobarPayRService,ApiConstants,RemexConstants {

    @Override
    public LuoberPayResDto luoberPay(LuoberPayReqDto luoberPayReqDto) {
        LuoberPayResDto luoberPayResDto = new LuoberPayResDto();
        ReqUnifyPlaceOrder reqUnifyPlaceOrder = new ReqUnifyPlaceOrder();

        //获取调用支付服务校验参数
        String channel = luoberPayReqDto.getChannel();
        String insuCom = luoberPayReqDto.getInsuCom();
        String bizOrderId = luoberPayReqDto.getBizOrderId();//业务订单号
        String bizNo = luoberPayReqDto.getBizNo();//业务流水号
        String chargeComCode = luoberPayReqDto.getChargeComCode();//支付商代码
        String userId = luoberPayReqDto.getUserId();//账号,用户openId
        String tradeType = luoberPayReqDto.getTradeType();//支付类型，
        String flowNo = String.valueOf(System.currentTimeMillis());
        //数据校验
        if (Util.isEmpty(channel)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("渠道代码不能为空");
            return luoberPayResDto;
        }
        if (Util.isEmpty(insuCom)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("保险公司代码不能为空");
            return luoberPayResDto;
        }
        if ( INSURECODESTRING.indexOf(insuCom) < -1 ) {
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("未找到有效的保险公司代码");
            return luoberPayResDto;
        }
        if (Util.isEmpty(tradeType)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("支付方式不能为空");
            return luoberPayResDto;
        }
        if (Util.isEmpty(bizOrderId)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("业务订单号不能为空");
            return luoberPayResDto;
        }

        if(Util.isEmpty(bizNo)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("业务流水号不能为空");
            return luoberPayResDto;
        }

        if (Util.isEmpty(chargeComCode)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("支付商代码不能为空");
            return luoberPayResDto;
        }
        if(Util.isEmpty(userId)){
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            luoberPayResDto.setErrMsg("用户账号不能为空");
            return luoberPayResDto;
        }

        ReflectUtil.copyProperties(reqUnifyPlaceOrder,luoberPayReqDto);

        if(Util.isEmpty(reqUnifyPlaceOrder.getName())){
           reqUnifyPlaceOrder.setName("");
        }
        //构造产品信息
        reqUnifyPlaceOrder =  constructReqUnifyPlaceOrder(reqUnifyPlaceOrder,luoberPayReqDto);
        ResUnifyPlaceOrder resUnifyPlaceOrder = null;
        try {
            ReqBusinessExtend extend = new ReqBusinessExtend(true, "");
            extend.setTransChannel(channel);
            resUnifyPlaceOrder = (ResUnifyPlaceOrder) SoaService.invokeService(AITP_CPSP_UNIFYPLACEORDERBS, EXECUTE,
                                  reqUnifyPlaceOrder,extend, channel,channel,flowNo,flowNo);
            ReflectUtil.copyProperties(luoberPayResDto,resUnifyPlaceOrder);
        } catch (Exception e) {
            String message = Util.transErrorMsg(e.getMessage());
            luoberPayResDto.setErrMsg(message);
            luoberPayResDto.setErrCode(ErrorCode.PARAM_ERROR);
            return  luoberPayResDto;
        }


        return luoberPayResDto;
    }

    /*太保 I00001,英大 I00002, 国寿 I00003, 富德 I00004,中华联合 I00008,
    * 华安 I00009,阳光 I00019, 人保 I00027  安盛天平 I00028*/
    static  Map<String, String> insuComMap = new HashMap<String, String>();
    static{
        insuComMap.put("I00001", "太保财险");
        insuComMap.put("I00002", "英大财险");
        insuComMap.put("I00003", "国寿财险");
        insuComMap.put("I00004", "富德财险");
        insuComMap.put("I00008", "中华联合");
        insuComMap.put("I00009", "华安财险");
        insuComMap.put("I00019", "阳光财险");
        insuComMap.put("I00027", "人保财险");
        insuComMap.put("I00028", "安盛天平");
    }
    private String obtainInsuComNameCn(String insuCom){
        return insuComMap .get(insuCom);
    }
    private ReqUnifyPlaceOrder constructReqUnifyPlaceOrder(ReqUnifyPlaceOrder reqUnifyPlaceOrder,LuoberPayReqDto luoberPayReqDto){

        //产品明细
        List<ReqUnifyOrderProduct> reqUnifyOrderProducts = new ArrayList<ReqUnifyOrderProduct>();
        ReqUnifyOrderProduct reqUnifyOrderProduct = new ReqUnifyOrderProduct();
        reqUnifyOrderProduct.setPrdtNo("");
        reqUnifyOrderProduct.setPrdtType(PRODUCTTYPE_AI);//车险
        reqUnifyOrderProduct.setPrdtName(obtainInsuComNameCn(luoberPayReqDto.getInsuCom()) + "保单");
        reqUnifyOrderProduct.setPrdtQuantity(QUANTITY_ONE);//数量为 1
		reqUnifyOrderProduct.setPrdtAmount(Arith.mul(luoberPayReqDto.getOrderPremium(), 1)+"");//订单金额应该从数据中中读取
        reqUnifyOrderProducts.add(reqUnifyOrderProduct);

        //添加到顶节点
        reqUnifyPlaceOrder.setUnifyOrderProducts(reqUnifyOrderProducts);
        reqUnifyPlaceOrder.setRcvTel(luoberPayReqDto.getRcvMobile());
        reqUnifyPlaceOrder.setBizDesc(luoberPayReqDto.getLicenseNo()+"_"+reqUnifyOrderProduct.getPrdtName());
        reqUnifyPlaceOrder.setSuppllierCode(luoberPayReqDto.getInsuCom());
        reqUnifyPlaceOrder.setSuppllierName(obtainInsuComNameCn(luoberPayReqDto.getInsuCom()));

        return reqUnifyPlaceOrder;
    }
}
