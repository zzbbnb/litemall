package com.example.payment.model.vo;

import com.example.payment.model.bo.PaymentBo;
import lombok.Data;

import java.util.Date;

/**
 * @program: core
 * @description: 支付单返回对象
 * @author: alex101
 * @create: 2020-12-16 10:14
 **/
@Data
public class PaymentRetVo {
    private Long id;

    private Long orderId;

    private Long aftersaleId;

    private Long amount;

    private Long actualAmount;

    private Date payTime;

    private String paymentPattern;

    private Byte state;

    private Date beginTime;

    private Date endTime;

    private Date gmtCreate;

    private Date gmtModified;

    public PaymentRetVo(PaymentBo bo) {
        id = bo.getId();
        orderId = bo.getOrderId();
        aftersaleId = bo.getAftersaleId();
        amount = bo.getAmount();
        actualAmount = bo.getActualAmount();
        payTime = bo.getPayTime();
        paymentPattern = bo.getPaymentPattern();
        state = bo.getState();
        beginTime = bo.getBeginTime();
        endTime = bo.getEndTime();
        gmtCreate = bo.getGmtCreate();
        gmtModified = bo.getGmtModified();
    }
}
