package com.example.payment.model.po;

import java.util.Date;

public class PaymentPo {
    private Long id;

    private Long amount;

    private Long actualAmount;

    private String paymentPattern;

    private Date payTime;

    private String paySn;

    private Date beginTime;

    private Date endTime;

    private Long orderId;

    private Byte state;

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

    public String getPaymentPattern() {
        return paymentPattern;
    }

    public void setPaymentPattern(String paymentPattern) {
        this.paymentPattern = paymentPattern == null ? null : paymentPattern.trim();
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
        this.paySn = paySn == null ? null : paySn.trim();
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
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