package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (Payment)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:28:06
 */
public class Payment implements Serializable {
    private static final long serialVersionUID = -96504588637287987L;
    
    private Long id;
    
    private Long amount;
    
    private Long actualAmount;
    
    private Object paymentPattern;
    
    private Date payTime;
    
    private String paySn;
    
    private Date beginTime;
    
    private Date endTime;
    
    private Long orderId;
    
    private Object state;
    
    private Date gmtCreate;
    
    private Date gmtModified;
    
    private Long aftersaleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Object getPaymentPattern() {
        return paymentPattern;
    }

    public void setPaymentPattern(Object paymentPattern) {
        this.paymentPattern = paymentPattern;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
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

    public Long getAftersaleId() {
        return aftersaleId;
    }

    public void setAftersaleId(Long aftersaleId) {
        this.aftersaleId = aftersaleId;
    }

}