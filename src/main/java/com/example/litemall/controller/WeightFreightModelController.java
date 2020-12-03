package com.example.litemall.controller;

import com.example.litemall.model.po.WeightFreightModel;
import com.example.litemall.service.WeightFreightModelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (WeightFreightModel)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:29:40
 */
@RestController
@RequestMapping("weightFreightModel")
public class WeightFreightModelController {
    /**
     * 服务对象
     */
    @Resource
    private WeightFreightModelService weightFreightModelService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public WeightFreightModel selectOne(Long id) {
        return this.weightFreightModelService.queryById(id);
    }

}