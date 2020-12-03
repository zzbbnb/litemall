package com.example.litemall.service.impl;

import com.example.litemall.model.po.WeightFreightModel;
import com.example.litemall.dao.WeightFreightModelDao;
import com.example.litemall.service.WeightFreightModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WeightFreightModel)表服务实现类
 *
 * @author makejava
 * @since 2020-12-03 11:29:40
 */
@Service("weightFreightModelService")
public class WeightFreightModelServiceImpl implements WeightFreightModelService {
    @Resource
    private WeightFreightModelDao weightFreightModelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WeightFreightModel queryById(Long id) {
        return this.weightFreightModelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WeightFreightModel> queryAllByLimit(int offset, int limit) {
        return this.weightFreightModelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param weightFreightModel 实例对象
     * @return 实例对象
     */
    @Override
    public WeightFreightModel insert(WeightFreightModel weightFreightModel) {
        this.weightFreightModelDao.insert(weightFreightModel);
        return weightFreightModel;
    }

    /**
     * 修改数据
     *
     * @param weightFreightModel 实例对象
     * @return 实例对象
     */
    @Override
    public WeightFreightModel update(WeightFreightModel weightFreightModel) {
        this.weightFreightModelDao.update(weightFreightModel);
        return this.queryById(weightFreightModel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.weightFreightModelDao.deleteById(id) > 0;
    }
}