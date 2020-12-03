package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:27:12
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = -67957500330539826L;
    
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
    
    private Object orderType;
    
    private Long freightPrice;
    
    private Long couponId;
    
    private Long couponActivityId;
    
    private Long discountPrice;
    
    private Long originPrice;
    
    private Long presaleId;
    
    private Long grouponDiscount;
    
    private Integer rebateNum;
    
    private Date confirmTime;
    
    private String shipmentSn;
    
    private Object state;
    
    private Object substate;
    
    private Object beDeleted;
    
    private Date gmtCreate;
    
    private Date gmtModified;
    
    private Long grouponId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getOrderType() {
        return orderType;
    }

    public void setOrderType(Object orderType) {
        this.orderType = orderType;
    }

    public Long getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Long freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(Long couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Long originPrice) {
        this.originPrice = originPrice;
    }

    public Long getPresaleId() {
        return presaleId;
    }

    public void setPresaleId(Long presaleId) {
        this.presaleId = presaleId;
    }

    public Long getGrouponDiscount() {
        return grouponDiscount;
    }

    public void setGrouponDiscount(Long grouponDiscount) {
        this.grouponDiscount = grouponDiscount;
    }

    public Integer getRebateNum() {
        return rebateNum;
    }

    public void setRebateNum(Integer rebateNum) {
        this.rebateNum = rebateNum;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getShipmentSn() {
        return shipmentSn;
    }

    public void setShipmentSn(String shipmentSn) {
        this.shipmentSn = shipmentSn;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getSubstate() {
        return substate;
    }

    public void setSubstate(Object substate) {
        this.substate = substate;
    }

    public Object getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Object beDeleted) {
        this.beDeleted = beDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(Long grouponId) {
        this.grouponId = grouponId;
    }

}