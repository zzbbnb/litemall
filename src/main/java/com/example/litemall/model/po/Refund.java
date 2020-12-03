package com.example.litemall.model.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (Refund)实体类
 *
 * @author makejava
 * @since 2020-12-03 11:29:24
 */
public class Refund implements Serializable {
    private static final long serialVersionUID = -28974192174322658L;
    
    private Long id;
    
    private Long paymentId;
    
    private Long amout;
    
    private String paySn;
    
    private Long billId;
    
    private Object state;
    
    private Date gmtCreate;
    
    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getAmout() {
        return amout;
    }

    public void setAmout(Long amout) {
        this.amout = amout;
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

}