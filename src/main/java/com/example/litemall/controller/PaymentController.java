package com.example.litemall.controller;

import com.example.litemall.model.po.Payment;
import com.example.litemall.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Payment)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:28:06
 */
@RestController
@RequestMapping("payment")
public class PaymentController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Payment selectOne(Long id) {
        return this.paymentService.queryById(id);
    }

}