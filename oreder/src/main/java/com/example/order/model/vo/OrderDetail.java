package com.example.order.model.vo;

import com.example.order.model.bo.OrderBo;
import com.example.order.model.bo.OrderItemBo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/***
 * @author yansong chen
 * @time 2020-12-15 16:38
 * @return
 */
@Data
public class OrderDetail {
    private Long id;
    private String orderSn;

    private customer customers;

    private shop shops;
    private Long pid;
    private int orderType;
    private int state;
    private int subState;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private LocalDateTime confirmTime;
    private Long originPrice;
    private Long discountPrice;
    private Long freightPrice;
    private Integer rebateNum;
    private String message;
    private Long regionId;
    private String address;
    private String mobile;
    private String consignee;
    private Long couponId;
    private Long grouponId;
    private Long presaleId;
    private String shipmentSn;

    /*
    private Long couponActivityId;
    private Long grouponDiscount;
    private int beDeleted;*/



    private List<orderitem> orderitems;

    public OrderDetail(OrderBo orderBo)
    {
        this.orderSn=orderBo.getOrderSn();
        this.id=orderBo.getId();
        this.pid=orderBo.getPid();
        this.consignee=orderBo.getConsignee();
        this.regionId=orderBo.getRegionId();
        this.address=orderBo.getAddress();
        this.freightPrice=orderBo.getFreightPrice();
        this.couponId=orderBo.getCouponId();
        this.orderType=orderBo.getOrderType();
        this.message=orderBo.getMessage();
        this.mobile=orderBo.getMobile();
        //this.couponActivityId=orderBo.getCouponActivityId();
        this.discountPrice=orderBo.getDiscountPrice();
        this.originPrice=orderBo.getOriginPrice();
        this.presaleId=orderBo.getPresaleId();
        //this.grouponDiscount=orderBo.getGrouponDiscount();
        this.rebateNum=orderBo.getRebateNum();
        this.confirmTime=orderBo.getConfirmTime();
        this.shipmentSn=orderBo.getShipmentSn();
        this.state=orderBo.getState();
        this.subState=orderBo.getSubstate();
        //this.beDeleted=orderBo.getBeDeleted();
        this.gmtCreate=orderBo.getGmtCreate();
        this.gmtModified=orderBo.getGmtModified();
        this.grouponId=orderBo.getGrouponId();
    }

    @Data
    private class customer{
        private  Long id;
        private String username;
        private String name;
    }

    @Data
    private class shop{
        private Long id;
        private String name;
        private int state;
        private String gmtCreate;
        private String gmtModified;
    }

    @Data
    public class orderitem{
        private Long orderId;
        private Long goodsSkuId;
        private Integer quantity;
        private Long price;
        private Long discount;
        private String name;
        private Long couponActivityId;
        private Long beShareId;

        public orderitem(OrderItemBo orderItemBo)
        {
            this.beShareId=orderItemBo.getBeShareId();
            this.couponActivityId=orderItemBo.getCouponActivityId();
            this.orderId=orderItemBo.getOrderId();
            this.goodsSkuId=orderItemBo.getGoodsSkuId();
            this.quantity=orderItemBo.getQuantity();
            this.price=orderItemBo.getPrice();
            this.name=orderItemBo.getName();
            this.discount=orderItemBo.getDiscount();
        }

    }
}
