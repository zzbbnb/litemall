package com.example.order.model.bo;

import com.example.order.model.po.OrderItem;
import lombok.Data;

import java.time.LocalDateTime;

/***
 * @author yansong chen
 * @time 2020-12-15 11:01
 * @return
 */
@Data
public class OrderItemBo {
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

    public OrderItemBo(OrderItem po)
    {
        this.id=po.getId();
        this.orderId=po.getOrderId();
        this.goodsSkuId=po.getGoodsSkuId();
        this.quantity=po.getQuantity();
        this.price=po.getPrice();
        this.discount=po.getDiscount();
        this.name=po.getName();
        this.couponActivityId=po.getCouponActivityId();
        this.gmtCreate=po.getGmtCreate();
        this.gmtModified=po.getGmtModified();
        this.beShareId=po.getBeShareId();
    }
}
