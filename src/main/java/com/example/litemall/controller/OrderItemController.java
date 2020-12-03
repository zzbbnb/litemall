package com.example.litemall.controller;

import com.example.litemall.model.po.OrderItem;
import com.example.litemall.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OrderItem)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:26:47
 */
@RestController
@RequestMapping("orderItem")
public class OrderItemController {
    /**
     * 服务对象
     */
    @Resource
    private OrderItemService orderItemService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrderItem selectOne(Long id) {
        return this.orderItemService.queryById(id);
    }

}