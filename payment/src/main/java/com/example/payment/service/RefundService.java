package com.example.payment.service;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.payment.dao.PaymentDao;
import com.example.payment.dao.RefundDao;
import com.example.payment.model.vo.AmountVo;
import com.example.payment.model.vo.PaymentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: core
 * @description: refund service
 * @author: alex101
 * @create: 2020-12-17 15:18
 **/
@Service
public class RefundService {

    @Autowired
    RefundDao refundDao;
    @Autowired
    PaymentDao paymentDao;

    public ReturnObject createPayment(Long id, PaymentInfoVo vo)
    {
        refundDao.createPayment(id,vo);
        return null;
    }

    /**
     * @Description: 管理员创建退款信息 todo：mapper
     * @Param:  [shopId, paymentId, amountVo]
     * @return: cn.edu.xmu.ooad.util.ReturnObject
     * @Author: lzn
     * @Date 2020/12/17
     */
    public ReturnObject postRefundsByPayments(Long shopId, Long paymentId, AmountVo amountVo)
    {
        if(shopId.equals(paymentDao.getShopIdByPaymentId(paymentId)))
            return refundDao.postRefundsByPayments(paymentId, amountVo);
        else
            return new ReturnObject(ResponseCode.RESOURCE_ID_OUTSCOPE);
    }

    /**
     * @Description: 管理员查询售后单的退款信息
     * @Param:  [orderId]
     * @return: cn.edu.xmu.ooad.util.ReturnObject
     * @Author: lzn
     * @Date 2020/12/17
     */
    public ReturnObject getRefundsByAftersaleId(Long aftersaleId)
    {
        return refundDao.getRefundsByAftersaleId(aftersaleId);
    }

    /**
     * @Description: 根据订单id查询退款信息
     * @Param:  [orderId]
     * @return: cn.edu.xmu.ooad.util.ReturnObject
     * @Author: lzn
     * @Date 2020/12/17
     */
    public ReturnObject getRefundsByOrderId(Long orderId)
    {
        return refundDao.getRefundsByOrderId(orderId);
    }


}
