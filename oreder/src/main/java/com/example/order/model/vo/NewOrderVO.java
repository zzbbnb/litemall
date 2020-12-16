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
    private int regionId;
    private String address;
    private String mobile;
    private String message;
    private int couponId;
    private int presaleId;
    private int grouponId;

    public NewOrder createNewOrder()
    {
        NewOrder newOrder=new NewOrder();
        newOrder.setConsignee(consignee);
        newOrder.setRegionId((long)regionId);
        newOrder.setAddress(address);
        newOrder.setMobile(mobile);
        newOrder.setMessage(message);
        newOrder.setCouponId((long)couponId);
        newOrder.setPresaleId((long)presaleId);
        newOrder.setGrouponId((long)grouponId);

        newOrder.setList(list);

        return newOrder;
    }
}
