package com.example.payment.controller;

import com.example.payment.dao.PaymentDao;
import com.example.payment.model.vo.PayPatternAndName;
import com.example.payment.model.vo.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="payments",produces = "application/json;charset=UTF-8")
public class PaymentController {
    @Autowired
    PaymentDao paymentDao;
    @GetMapping("states")
    public Object getAllPaymentState()
    {
        List<Payment> paymentsAll=paymentDao.getAllPayments();
        List<State> paymentstates=new ArrayList<>();
        for(Payment p:paymentsAll) {
            ResponseCode re = ResponseCode.getByCode(p.getState());
            paymentstates.add(new State(re));

        }
//        List<Test>t=new ArrayList<>();
//        t.add(new Test(1,2));
//        t.add(new Test(3,4));
        return ResponseUtil.ok(paymentstates);
    }
    @GetMapping("patterns")
    public Object getAllPatterns() {
        List<Payment> paymentsAll = paymentDao.getAllPayments();
        List<PayPatternAndName> pan = new ArrayList<>();
        for (Payment p : paymentsAll) {
            PayPatternAndName pp = new PayPatternAndName(p.getPaymentPattern().toString(), p.getPaySn());
            pan.add(pp);
        }
        return ResponseUtil.ok(pan);
    }
}