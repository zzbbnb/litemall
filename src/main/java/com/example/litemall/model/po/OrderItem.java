package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderItem)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:26:47
 */
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -64223334551263270L;
    
    private Long id;
    
    private Long orderId;
    
    private Long goodsSkuId;
    
    private Integer quantity;
    
    private Long price;
    
    private Long discount;
    
    private String name;
    
    private Long couponId;
    
    private Long couponActivityId;
    
    private Long beShareId;
    
    private Date gmtCreate;
    
    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getBeShareId() {
        return beShareId;
    }

    public void setBeShareId(Long beShareId) {
        this.beShareId = beShareId;
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

}