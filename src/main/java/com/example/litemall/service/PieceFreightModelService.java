package com.example.litemall.service;

import com.example.litemall.model.po.PieceFreightModel;
import java.util.List;

/**
 * (PieceFreightModel)表服务接口
 *
 * @author makejava
 * @since 2020-12-03 11:28:25
 */
public interface PieceFreightModelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PieceFreightModel queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PieceFreightModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param pieceFreightModel 实例对象
     * @return 实例对象
     */
    PieceFreightModel insert(PieceFreightModel pieceFreightModel);

    /**
     * 修改数据
     *
     * @param pieceFreightModel 实例对象
     * @return 实例对象
     */
    PieceFreightModel update(PieceFreightModel pieceFreightModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}