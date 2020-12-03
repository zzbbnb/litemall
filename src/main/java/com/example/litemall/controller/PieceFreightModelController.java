package com.example.litemall.controller;

import com.example.litemall.model.po.PieceFreightModel;
import com.example.litemall.service.PieceFreightModelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PieceFreightModel)表控制层
 *
 * @author makejava
 * @since 2020-12-03 11:28:26
 */
@RestController
@RequestMapping("pieceFreightModel")
public class PieceFreightModelController {
    /**
     * 服务对象
     */
    @Resource
    private PieceFreightModelService pieceFreightModelService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PieceFreightModel selectOne(Long id) {
        return this.pieceFreightModelService.queryById(id);
    }

}