package com.example.order.model.vo;

import com.example.order.model.po.Orders;

import java.time.LocalDateTime;

/***
 * @author yansong chen
 * @time 2020-12-17 11:18
 * @description:
 */
public class SimpleOrderInfo {
    private Long id;
    private Long customerId;
    //母单id
    private Long pid;
    private Byte orderType;
    private Byte state;
    private LocalDateTime gmtCreate;
    private Long originPrice;
    private Long discountPrice;
    private Long freightPrice;
    private Long grouponId;
    private Long presaleId;
    private String shipmentSn;

    public SimpleOrderInfo(Orders orders)
    {
        this.id=orders.getId();
        this.customerId=orders.getCustomerId();
        this.pid=orders.getPid();
        this.orderType=orders.getOrderType();
        this.state=orders.getState();
        this.gmtCreate=orders.getGmtCreate();
        this.originPrice=orders.getOriginPrice();
        this.discountPrice=orders.getDiscountPrice();
        this.freightPrice=orders.getFreightPrice();
        this.grouponId=orders.getGrouponId();
        this.presaleId=orders.getPresaleId();
        this.shipmentSn=orders.getShipmentSn();
    }
}
