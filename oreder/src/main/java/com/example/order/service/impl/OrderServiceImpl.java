package com.example.order.service.impl;

import com.example.order.dao.OrderModelDao;
import com.example.orderservice.OrderServiceDubbo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * @author yansong chen
 * @time 2020-12-21 1:18
 * @description:
 */
@DubboService(version = "0.0.1-SNAPSHOT")
public class OrderServiceImpl implements OrderServiceDubbo {
    @Autowired
    private OrderModelDao orderModelDao;

    @Override
    public Long GetShopIdByOrderId(Long id) {
        orderModelDao.GetShopIdByOrderId(id);
        return null;
    }

    @Override
    public Long GetUserIdByOrderId(Long id) {
        orderModelDao.GetUserIdByOrderId(id);
        return null;
    }
}
