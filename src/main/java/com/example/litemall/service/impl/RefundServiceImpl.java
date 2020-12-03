package com.example.litemall.service.impl;

import com.example.litemall.model.po.Refund;
import com.example.litemall.dao.RefundDao;
import com.example.litemall.service.RefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Refund)表服务实现类
 *
 * @author makejava
 * @since 2020-12-03 11:29:24
 */
@Service("refundService")
public class RefundServiceImpl implements RefundService {
    @Resource
    private RefundDao refundDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Refund queryById(Long id) {
        return this.refundDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Refund> queryAllByLimit(int offset, int limit) {
        return this.refundDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param refund 实例对象
     * @return 实例对象
     */
    @Override
    public Refund insert(Refund refund) {
        this.refundDao.insert(refund);
        return refund;
    }

    /**
     * 修改数据
     *
     * @param refund 实例对象
     * @return 实例对象
     */
    @Override
    public Refund update(Refund refund) {
        this.refundDao.update(refund);
        return this.queryById(refund.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.refundDao.deleteById(id) > 0;
    }
}