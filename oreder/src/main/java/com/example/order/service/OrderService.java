package com.example.order.service;

import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.order.dao.OrderModelDao;
import com.example.order.model.bo.NewOrder;
import com.example.order.model.vo.NewOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * @author yansong chen
 * @time 2020-12-10 16:15
 * @return
 */
@Service
public class OrderService {
    @Autowired
    OrderModelDao orderModeldao;

    /**
    * @Description:
    * @Param: []
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: yansong chen
    * @Date: 2020-12-13 1:20
    */
    @Transactional
    public ReturnObject GetOrderStatus(Long username)
    {
        return orderModeldao.GetOrderStatus(username);
    }


    @Transactional
    public ReturnObject GetListOrder(Long id,String orderSn,int state,
                                     String begintime,String endtime,int page,int pageSize)
    {
        return orderModeldao.GetListOrder(id,orderSn,state,begintime,endtime,page,pageSize);
    }

    @Transactional
    public ReturnObject PostOrder(NewOrder newOrder)
    {
        return orderModeldao.PostNewOrdere(newOrder);
    }

    @Transactional
    public ReturnObject GetOrderDetail(Long id,int order_id)
    {
        return orderModeldao.GetOrderDetail(id,order_id);
    }


    @Transactional
    public ReturnObject PostGroupon_Normal(Long id)
    {
        return orderModeldao.PostGroupon_Normal(id);
    }
}
