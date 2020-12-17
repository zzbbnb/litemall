package com.example.payment.service;

import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.payment.dao.RefundDao;
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

    public ReturnObject createPayment(Long id, PaymentInfoVo vo)
    {
        refundDao.createPayment(id,vo);
        return null;
    }

}
