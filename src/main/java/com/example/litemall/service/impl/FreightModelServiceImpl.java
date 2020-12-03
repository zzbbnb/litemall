package com.example.litemall.service.impl;

import com.example.litemall.model.po.FreightModel;
import com.example.litemall.dao.FreightModelDao;
import com.example.litemall.service.FreightModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FreightModel)表服务实现类
 *
 * @author makejava
 * @since 2020-12-03 11:25:26
 */
@Service("freightModelService")
public class FreightModelServiceImpl implements FreightModelService {
    @Resource
    private FreightModelDao freightModelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FreightModel queryById(Long id) {
        return this.freightModelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FreightModel> queryAllByLimit(int offset, int limit) {
        return this.freightModelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param freightModel 实例对象
     * @return 实例对象
     */
    @Override
    public FreightModel insert(FreightModel freightModel) {
        this.freightModelDao.insert(freightModel);
        return freightModel;
    }

    /**
     * 修改数据
     *
     * @param freightModel 实例对象
     * @return 实例对象
     */
    @Override
    public FreightModel update(FreightModel freightModel) {
        this.freightModelDao.update(freightModel);
        return this.queryById(freightModel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.freightModelDao.deleteById(id) > 0;
    }
}