package com.example.litemall.controller;

import com.example.litemall.model.po.Refund;
import com.example.litemall.service.RefundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Refund)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:29:24
 */
@RestController
@RequestMapping("refund")
public class RefundController {
    /**
     * 服务对象
     */
    @Resource
    private RefundService refundService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Refund selectOne(Long id) {
        return this.refundService.queryById(id);
    }

}