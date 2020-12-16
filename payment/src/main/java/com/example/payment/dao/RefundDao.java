package com.example.payment.dao;

import com.example.payment.mapper.PaymentPoMapper;
import com.example.payment.mapper.RefundPoMapper;
import com.example.payment.model.po.RefundPo;
import com.example.payment.model.po.RefundPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RefundDao {
    @Autowired
    RefundPoMapper refundPoMapper;
    @Autowired
    PaymentPoMapper paymentPoMapper;
    public List<RefundPo> getRefundsByPaymentId(long paymentId)
    {
        RefundPoExample refundExample=new RefundPoExample();
        RefundPoExample.Criteria criteria=refundExample.createCriteria();
        criteria.andPaymentIdEqualTo(paymentId);
        return refundPoMapper.selectByExample(refundExample);
    }
}
