package com.example.order.model.vo;

import com.example.order.model.bo.NewOrder;

import java.util.List;

/***
 * @author yansong chen
 * @time 2020-12-16 9:40
 * @description:新建订单数据对象
 */
public class NewOrderVO {
    private List<NewOrderItem> list;
    private String consignee;
    private Long regionId;
    private String address;
    private String mobile;
    private String message;
    private Long couponId;
    private Long presaleId;
    private Long grouponId;

    public NewOrder createNewOrder()
    {
        NewOrder newOrder=new NewOrder();
        newOrder.setConsignee(consignee);
        newOrder.setRegionId((long)regionId);
        newOrder.setAddress(address);
        newOrder.setMobile(mobile);
        newOrder.setMessage(message);
        newOrder.setCouponId(couponId);
        newOrder.setPresaleId(presaleId);
        newOrder.setGrouponId(grouponId);

        newOrder.setList(list);

        return newOrder;
    }
}
