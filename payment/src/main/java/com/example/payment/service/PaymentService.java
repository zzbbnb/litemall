package com.example.payment.service;

import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.payment.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {


    @Autowired
    PaymentDao paymentDao;


    public ReturnObject getAllPaymentState() {
        return paymentDao.getAllPaymentsState();
    }

//    public List<Refund> getRefundByOrderId(long orderId)//这个是通过orderid找payment，然后通过paymentid找renfund
//    {
//
//    }

    public ReturnObject getAllPaymentPatterns()
    {
        return paymentDao.getAllPaymentPatterns();
    }

}
