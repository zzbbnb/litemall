package com.example.payment.model.bo;

import cn.edu.xmu.ooad.model.VoObject;
import com.example.payment.model.po.PaymentPo;
import com.example.payment.model.vo.PaymentRetVo;
import lombok.Data;

import java.util.Date;

/**
 * @program: core
 * @description:
 * @author: alex101
 * @create: 2020-12-16 10:23
 **/
@Data
public class PaymentBo implements VoObject {
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

    public PaymentBo()
    {

    }

    public PaymentBo(PaymentPo po)
    {
        id = po.getId();
        amount = po.getAmount();
        actualAmount = po.getActualAmount();
        paymentPattern = po.getPaymentPattern();
        payTime = po.getPayTime();
        paySn = po.getPaySn();
        beginTime = po.getBeginTime();
        endTime = po.getEndTime();
        orderId = po.getOrderId();
        state = po.getState();
        gmtCreate = po.getGmtCreate();
        gmtModified = po.getGmtModified();
        aftersaleId = po.getAftersaleId();
    }

    @Override
    public Object createVo() {
        return new PaymentRetVo(this);
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
