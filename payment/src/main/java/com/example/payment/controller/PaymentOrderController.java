package com.example.payment.controller;

import com.example.payment.dao.PaymentDao;
import com.example.payment.dao.RefundDao;
import com.example.payment.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "orders",produces = "application/json;charset=UTF-8")
public class PaymentOrderController {
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    RefundDao refundDao;
    @GetMapping("{id}/payments")
    public Object getPaymentByOrderId(@PathVariable("id") long id)
    {
        return ResponseUtil.ok(paymentDao.getPaymentsByOrderId(id));
    }
    @GetMapping("{id}/refunds")
    public Object getRefundByOrderId(@PathVariable("id") long orderId)
    {
        List<Payment>paymentList=paymentDao.getPaymentsByOrderId(orderId);
        List<Refund>refundList=new ArrayList<>();
        for(Payment p:paymentList)
        {
            List<Refund> temp=refundDao.getRefundsByPaymentId(p.getId());
            refundList.addAll(temp);
        }
        return ResponseUtil.ok(refundList);
    }
}
