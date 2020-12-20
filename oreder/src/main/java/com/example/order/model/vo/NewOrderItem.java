package com.example.order.model.vo;

import com.example.order.model.bo.NewOrderItemBo;
import lombok.Data;

/***
 * @author yansong chen
 * @time 2020-12-16 9:41
 * @description:新建订单数据对象item
 */
@Data
public class NewOrderItem {
    private Long skuId;
    private int quantity;
    private Long couponActId;

    public NewOrderItemBo createNewOrderItemBo()
    {
        NewOrderItemBo newOrderItemBo =new NewOrderItemBo();
        newOrderItemBo.setGoodsSkuId(skuId);
        newOrderItemBo.setQuantity(quantity);
        newOrderItemBo.setCouponActivityId(couponActId);

        return  newOrderItemBo;
    }

}
