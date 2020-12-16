package com.example.payment.controller;

import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.annotation.LoginUser;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.payment.dao.PaymentDao;
import com.example.payment.model.vo.PayPatternAndNameRetVo;
import com.example.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/payment/payments",produces = "application/json;charset=UTF-8")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);


    @Autowired
    PaymentService paymentService;

    /**
    * @Description: 获取支付单的所有状态
    * @Param: []
    * @return: java.lang.Object
    * @Author: alex101
    * @Date: 2020/12/16
    */
    @Audit
    @GetMapping("states")
    public Object getAllPaymentState()
    {
        logger.debug("get all paymentState");
        return paymentService.getAllPaymentState();

    }

    /** 
    * @Description: 获取所有支付方式
    * @Param: [] 
    * @return: java.lang.Object 
    * @Author: alex101
    * @Date: 2020/12/16 
    */
    @Audit
    @GetMapping("patterns")
    public Object getAllPatterns() {
        logger.debug("get all paymentPatterns");
        return paymentService.getAllPaymentPatterns();
    }
}