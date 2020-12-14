package com.example.freight.dao;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.freight.controller.FreightController;
import com.example.freight.mapper.PieceFreightModelMapper;
import com.example.freight.model.bo.PieceFreightModelBo;
import com.example.freight.model.po.PieceFreightModelPo;
import com.example.freight.model.vo.PieceFreightModelInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

 /**
 * @program:
 * @despciption:
 * @author: lzn
 * @create: 2020/12/14 1:16
 */

@Component
public class PieceFreightDao
{
    private static final Logger logger = LoggerFactory.getLogger(FreightController.class);

    @Autowired
    private PieceFreightModelMapper pieceFreightModelMapper;

    /**
     * @Description: 管理员定义件数模板明细
     * @Param:  [freightModelBo]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    public ReturnObject setPieceItems(Long id, PieceFreightModelInfoVo pieceFreightModelInfoVo)
    {
        ReturnObject returnObject;
        QueryWrapper<PieceFreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("freightModelId", id).eq("regionId", pieceFreightModelInfoVo.getRegionId());
        PieceFreightModelPo pieceFreightModelPo = pieceFreightModelMapper.selectOne(queryWrapper);
        if (pieceFreightModelPo != null)
        {
            returnObject = new ReturnObject<>(ResponseCode.REGION_SAME);
            logger.error("has same RegionId " + "id: " + pieceFreightModelPo.getRegionId());
        }
        else
        {
            pieceFreightModelPo.setFreightModelId(id);
            pieceFreightModelPo.setAdditionalItems(pieceFreightModelInfoVo.getAdditionalItems());
            pieceFreightModelPo.setAdditionalItemsPrice(pieceFreightModelInfoVo.getAdditionalItemsPrice());
            pieceFreightModelPo.setFirstItems(pieceFreightModelInfoVo.getFirstItem());
            pieceFreightModelPo.setFirstItemsPrice(pieceFreightModelInfoVo.getFirstItemPrice());
            pieceFreightModelPo.setRegionId(pieceFreightModelInfoVo.getRegionId());
            pieceFreightModelPo.setGmtCreate(LocalDateTime.now());
            pieceFreightModelPo.setGmtModified(LocalDateTime.now());
            pieceFreightModelMapper.insert(pieceFreightModelPo);
            returnObject = new ReturnObject<>(new PieceFreightModelBo(pieceFreightModelPo));
        }
        return returnObject;
    }

    /**
     * @Description: 店家或管理员查询件数运费模板明细
     * @Param:  [id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    public ReturnObject getPieceItems(Long id)
    {
        ReturnObject<List> returnObject;
        QueryWrapper<PieceFreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("freightModelId", id);
        List<PieceFreightModelPo> pieceFreightModelPoList = pieceFreightModelMapper.selectList(queryWrapper);
        List<PieceFreightModelBo> pieceFreightModelBoList = new ArrayList<>();
        for (PieceFreightModelPo pieceFreightModelPo : pieceFreightModelPoList)
        {
            pieceFreightModelBoList.add(new PieceFreightModelBo(pieceFreightModelPo));
        }
        returnObject = new ReturnObject<>(pieceFreightModelBoList);
        return returnObject;
    }


    /**
     * @Description: 店家或管理员修改件数运费模板明细
     * @Param:  [id, pieceFreightModelInfoVo]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    public ReturnObject putPieceItems(Long id, PieceFreightModelInfoVo pieceFreightModelInfoVo)
    {
        ReturnObject returnObject;
        QueryWrapper<PieceFreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        PieceFreightModelPo pieceFreightModelPo = pieceFreightModelMapper.selectOne(queryWrapper);

        QueryWrapper<PieceFreightModelPo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("regionId", pieceFreightModelInfoVo.getRegionId());
        PieceFreightModelPo pieceFreightModelPo1 = pieceFreightModelMapper.selectOne(queryWrapper1);

        if(pieceFreightModelPo == null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        else if(pieceFreightModelPo1 != null)
        {
            returnObject = new ReturnObject<>(ResponseCode.REGION_SAME);
        }
        else
        {
            UpdateWrapper<PieceFreightModelPo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            pieceFreightModelPo.setGmtModified(LocalDateTime.now());
            pieceFreightModelPo.setRegionId(pieceFreightModelInfoVo.getRegionId());
            pieceFreightModelPo.setFirstItemsPrice(pieceFreightModelInfoVo.getFirstItemPrice());
            pieceFreightModelPo.setFirstItems(pieceFreightModelInfoVo.getFirstItem());
            pieceFreightModelPo.setAdditionalItems(pieceFreightModelInfoVo.getAdditionalItems());
            pieceFreightModelPo.setAdditionalItemsPrice(pieceFreightModelInfoVo.getAdditionalItemsPrice());
            pieceFreightModelMapper.update(pieceFreightModelPo, updateWrapper);
            returnObject = new ReturnObject<>(ResponseCode.OK);
        }
        return returnObject;
    }


    /**
     * @Description: 店家或管理员删掉件数运费模板明细
     * @Param:  [id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    public ReturnObject delPieceItems(Long id)
    {
        ReturnObject returnObject;
        QueryWrapper<PieceFreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        PieceFreightModelPo pieceFreightModelPo = pieceFreightModelMapper.selectOne(queryWrapper);

        if(pieceFreightModelPo == null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        pieceFreightModelMapper.delete(queryWrapper);
        returnObject = new ReturnObject<>(ResponseCode.OK);
        return returnObject;
    }
}

