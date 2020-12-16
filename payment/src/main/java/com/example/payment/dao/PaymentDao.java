package com.example.payment.dao;

import com.example.payment.mapper.PaymentPoMapper;
import com.example.payment.model.po.PaymentPo;
import com.example.payment.model.po.PaymentPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
