package com.example.payment.dao;

import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.payment.mapper.PaymentPoMapper;
import com.example.payment.model.po.PaymentPo;
import com.example.payment.model.po.PaymentPoExample;
import com.example.payment.model.vo.PayPatternAndNameRetVo;
import com.example.payment.model.vo.StateRetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentDao {
    @Autowired
    PaymentPoMapper paymentPoMapper;

    public List<PaymentPo> getAllPayments()
    {
        return paymentPoMapper.selectByExample(null);
    }

    public List<PaymentPo> getPaymentsByOrderId(long orderId)
    {
        PaymentPoExample paymentExample=new PaymentPoExample();
        PaymentPoExample.Criteria criteria=paymentExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return paymentPoMapper.selectByExample(paymentExample);
    }
    public List<PaymentPo> getPaymentsByAftersaleId(long aftersaleId)
    {
        PaymentPoExample paymentExample=new PaymentPoExample();
        PaymentPoExample.Criteria criteria=paymentExample.createCriteria();
        criteria.andAftersaleIdEqualTo(aftersaleId);
        return paymentPoMapper.selectByExample(paymentExample);
    }

    public ReturnObject getAllPaymentsState()
    {
        ReturnObject returnObject = null;
        /* 手写SQL查询订单状态 */
        List<Byte> states = paymentPoMapper.getAllState();
        List<StateRetVo> stateRetVos = new ArrayList<>(states.size());
        for(Byte code:states)
        {
            stateRetVos.add(new StateRetVo(code));
        }
        returnObject = new ReturnObject(stateRetVos);
        return returnObject;
    }

    public ReturnObject getAllPaymentPatterns()
    {
        List<PayPatternAndNameRetVo> payPatternAndNameRetVos = new ArrayList<>(2);
        /* 目前只返回两种订单状态 */
        payPatternAndNameRetVos.add(new PayPatternAndNameRetVo("返点支付","001"));
        payPatternAndNameRetVos.add(new PayPatternAndNameRetVo("模拟支付","002"));
        return new ReturnObject(payPatternAndNameRetVos);
    }



}
