package com.example.order.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/***
 * @author yansong chen
 * @time 2020-12-14 12:47
 * @return
 */
@Data
public class OrderVo {
    private Long id;
    private Long customerId;
    private Long shopId;
    private String orderSn;
    private Long pid;
    private String consignee;
    private Long regionId;
    private String address;
    private String mobile;
    private String message;
    private int orderType;
    private Long freightPrice;
    private Long couponId;
    private Long couponActivityId;
    private Long discountPrice;
    private Long originPrice;
    private Long presaleId;
    private Long grouponDiscount;
    private Integer rebateNum;
    private LocalDateTime confirmTime;
    private String shipmentSn;
    private int state;
    private int substate;
    private int beDeleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Long grouponId;
}
