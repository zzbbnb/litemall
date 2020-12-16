package com.example.payment.controller;

import com.example.payment.dao.PaymentDao;
import com.example.payment.dao.RefundDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "aftersales",produces = "application/json;charset=UTF-8")
public class PaymentAftersaleController {

    @Autowired
    PaymentDao paymentDao;
    @Autowired
    RefundDao refundDao;
    @GetMapping("{id}/payments")
    public Object getAftersaleByAftersaleId(@PathVariable("id")long aftersaleId)
    {
        return ResponseUtil.ok(paymentDao.getPaymentsByAftersaleId(aftersaleId));
    }
    @GetMapping("{id}/refunds")
    //在payment表中通过aftersaleid找id（paymentid），然后在refund表通过paymentid找refund
    public Object getRefundsByAftersaleId(@PathVariable("id")long aftersaleId)
    {
        List<Payment>paymentList=paymentDao.getPaymentsByAftersaleId(aftersaleId);
        List<Refund>refundList=new ArrayList<>();
        for(Payment p:paymentList)
        {
            List<Refund> temp=refundDao.getRefundsByPaymentId(p.getId());
            refundList.addAll(temp);
        }
        return ResponseUtil.ok(refundList);
    }
}
