package com.example.litemall.service.impl;

import com.example.litemall.model.po.PieceFreightModel;
import com.example.litemall.dao.PieceFreightModelDao;
import com.example.litemall.service.PieceFreightModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PieceFreightModel)表服务实现类
 *
 * @author makejava
 * @since 2020-12-03 11:28:26
 */
@Service("pieceFreightModelService")
public class PieceFreightModelServiceImpl implements PieceFreightModelService {
    @Resource
    private PieceFreightModelDao pieceFreightModelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PieceFreightModel queryById(Long id) {
        return this.pieceFreightModelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<PieceFreightModel> queryAllByLimit(int offset, int limit) {
        return this.pieceFreightModelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param pieceFreightModel 实例对象
     * @return 实例对象
     */
    @Override
    public PieceFreightModel insert(PieceFreightModel pieceFreightModel) {
        this.pieceFreightModelDao.insert(pieceFreightModel);
        return pieceFreightModel;
    }

    /**
     * 修改数据
     *
     * @param pieceFreightModel 实例对象
     * @return 实例对象
     */
    @Override
    public PieceFreightModel update(PieceFreightModel pieceFreightModel) {
        this.pieceFreightModelDao.update(pieceFreightModel);
        return this.queryById(pieceFreightModel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.pieceFreightModelDao.deleteById(id) > 0;
    }
}