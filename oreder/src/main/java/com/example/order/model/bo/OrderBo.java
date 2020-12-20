package com.example.order.model.bo;

import cn.edu.xmu.ooad.model.VoObject;
import com.example.order.model.po.Orders;
import com.example.order.model.vo.NewOrderItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/***
 * @author yansong chen
 * @time 2020-12-14 12:29
 * @return
 */
@Data
public class OrderBo implements VoObject {
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
    private Byte orderType;
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
    private Byte state;
    private Byte substate;
    private Byte beDeleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Long grouponId;

    public OrderBo (Orders vo)
    {
        this.customerId=vo.getCustomerId();
        this.shopId=vo.getShopId();
        this.orderSn=vo.getOrderSn();
        this.id=vo.getId();
        this.pid=vo.getPid();
        this.consignee=vo.getConsignee();
        this.regionId=vo.getRegionId();
        this.address=vo.getAddress();
        this.freightPrice=vo.getFreightPrice();
        this.couponId=vo.getCouponId();
        this.orderType=vo.getOrderType();
        this.message=vo.getMessage();
        this.mobile=vo.getMobile();
        this.couponActivityId=vo.getCouponActivityId();
        this.discountPrice=vo.getDiscountPrice();
        this.originPrice=vo.getOriginPrice();
        this.presaleId=vo.getPresaleId();
        this.grouponDiscount=vo.getGrouponDiscount();
        this.rebateNum=vo.getRebateNum();
        this.confirmTime=vo.getConfirmTime();
        this.shipmentSn=vo.getShipmentSn();
        this.state=vo.getState();
        this.substate=vo.getSubstate();
        this.beDeleted=vo.getBeDeleted();
        this.gmtCreate=vo.getGmtCreate();
        this.gmtModified=vo.getGmtModified();
        this.grouponId=vo.getGrouponId();
    }


    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
