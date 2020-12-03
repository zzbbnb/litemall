package com.example.litemall.controller;

import com.example.litemall.model.po.Orders;
import com.example.litemall.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Orders)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:27:12
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Orders selectOne(Long id) {
        return this.ordersService.queryById(id);
    }

}