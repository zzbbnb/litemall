package com.example.order.model.bo;

import cn.edu.xmu.ooad.model.VoObject;
import com.example.order.model.po.OrderItem;
import lombok.Data;

import java.time.LocalDateTime;

/***
 * @author yansong chen
 * @time 2020-12-16 21:49
 * @description:
 */
@Data
public class NewOrderItemBo implements VoObject {
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

    @Override
    public OrderItem createVo() {
        OrderItem orderItem=new OrderItem();
        orderItem.setBeShareId(beShareId);
        orderItem.setCouponActivityId(couponActivityId);
        orderItem.setDiscount(discount);
        orderItem.setGmtCreate(gmtCreate);
        orderItem.setGmtModified(gmtModified);
        orderItem.setGoodsSkuId(goodsSkuId);
        orderItem.setId(id);
        orderItem.setName(name);
        orderItem.setOrderId(orderId);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);

        return orderItem;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }


}
