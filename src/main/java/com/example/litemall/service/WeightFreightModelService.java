package com.example.litemall.service;

import com.example.litemall.model.po.WeightFreightModel;
import java.util.List;

/**
 * (WeightFreightModel)表服务接口
 *
 * @author makejava
 * @since 2020-12-03 11:29:40
 */
public interface WeightFreightModelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeightFreightModel queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WeightFreightModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param weightFreightModel 实例对象
     * @return 实例对象
     */
    WeightFreightModel insert(WeightFreightModel weightFreightModel);

    /**
     * 修改数据
     *
     * @param weightFreightModel 实例对象
     * @return 实例对象
     */
    WeightFreightModel update(WeightFreightModel weightFreightModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}