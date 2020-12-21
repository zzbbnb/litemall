package com.example.orderservice;

/***
 * @author yansong chen
 * @time 2020-12-21 10:35
 * @description:
 */
public interface OrderServiceDubbo {
    Long GetShopIdByOrderId(Long id);

    Long GetUserIdByOrderId(Long id);
}
