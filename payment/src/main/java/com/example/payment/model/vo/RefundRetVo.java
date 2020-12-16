package com.example.payment.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: core
 * @description: 退款信息对象
 * @author: alex101
 * @create: 2020-12-16 10:20
 **/
@Data
public class RefundRetVo {
    private Long id;

    private Long paymentId;

    private Long amount;

    private Byte state;

    private Date gmtCreate;

    private Date gmtModified;

    private Long orderId;

    private Long aftersaleId;

}
