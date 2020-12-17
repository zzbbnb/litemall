package com.example.payment.controller;

import cn.edu.xmu.ooad.annotation.Audit;
import com.example.payment.dao.PaymentDao;
import com.example.payment.dao.RefundDao;
import com.example.payment.model.vo.PaymentInfoVo;
import com.example.payment.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/payment/aftersales",produces = "application/json;charset=UTF-8")
public class PaymentAftersaleController {

    @Autowired
    RefundService refundService;

    @PostMapping("{id}/payments")
    @Audit
    public Object createPaymentsForRefund(@PathVariable Long id, @RequestBody PaymentInfoVo vo)
    {
        return refundService.createPayment(id,vo);
    }

//    @GetMapping("{id}/payments")
//    public Object getAftersaleByAftersaleId(@PathVariable("id")long aftersaleId)
//    {
//        return ResponseUtil.ok(paymentDao.getPaymentsByAftersaleId(aftersaleId));
//    }
//    @GetMapping("{id}/refunds")
//    //在payment表中通过aftersaleid找id（paymentid），然后在refund表通过paymentid找refund
//    public Object getRefundsByAftersaleId(@PathVariable("id")long aftersaleId)
//    {
//        List<Payment>paymentList=paymentDao.getPaymentsByAftersaleId(aftersaleId);
//        List<Refund>refundList=new ArrayList<>();
//        for(Payment p:paymentList)
//        {
//            List<Refund> temp=refundDao.getRefundsByPaymentId(p.getId());
//            refundList.addAll(temp);
//        }
//        return ResponseUtil.ok(refundList);
//    }
}
