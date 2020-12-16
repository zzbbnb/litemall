package com.example.order.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/***
 * @author yansong chen
 * @time 2020-12-15 17:18
 * @return
 */
@Data
public class OrderItemVO {
    private Long id;
    private Long orderId;
    private Long goodsSkuId;
    private Integer quantity;
    private Long price;
    private Long discount;
    private String name;
    private Long couponActivityId;
    private Long beShareId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
