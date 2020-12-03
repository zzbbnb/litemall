package com.example.litemall.controller;

import com.example.litemall.model.po.FreightModel;
import com.example.litemall.service.FreightModelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FreightModel)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:25:30
 */
@RestController
@RequestMapping("freightModel")
public class FreightModelController {
    /**
     * 服务对象
     */
    @Resource
    private FreightModelService freightModelService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FreightModel selectOne(Long id) {
        return this.freightModelService.queryById(id);
    }

}