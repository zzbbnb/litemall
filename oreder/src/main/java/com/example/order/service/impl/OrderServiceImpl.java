package com.example.order.service.impl;

import com.example.order.dao.OrderModelDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * @author yansong chen
 * @time 2020-12-21 1:18
 * @description:
 */
@DubboService(version = "0.0.1-SNAPSHOT")
public class OrderServiceImpl {
    @Autowired
    private OrderModelDao orderModelDao;
}
