package com.example.payment.service;

import com.example.payment.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {


    @Autowired
    PaymentDao paymentDao;

//    public List<Refund> getRefundByOrderId(long orderId)//这个是通过orderid找payment，然后通过paymentid找renfund
//    {
//
//    }

}
