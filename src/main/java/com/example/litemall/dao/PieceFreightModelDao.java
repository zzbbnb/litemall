package com.example.litemall.dao;

import com.example.litemall.model.po.PieceFreightModel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (PieceFreightModel)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-03 11:28:25
 */
public interface PieceFreightModelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PieceFreightModel queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PieceFreightModel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pieceFreightModel 实例对象
     * @return 对象列表
     */
    List<PieceFreightModel> queryAll(PieceFreightModel pieceFreightModel);

    /**
     * 新增数据
     *
     * @param pieceFreightModel 实例对象
     * @return 影响行数
     */
    int insert(PieceFreightModel pieceFreightModel);

    /**
     * 修改数据
     *
     * @param pieceFreightModel 实例对象
     * @return 影响行数
     */
    int update(PieceFreightModel pieceFreightModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}