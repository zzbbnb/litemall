package com.example.freight.dao;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.freight.controller.FreightController;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.mapper.WeightFreightModelMapper;
import com.example.freight.model.bo.WeightFreightModelBo;
import com.example.freight.model.po.FreightModelPo;
import com.example.freight.model.po.PieceFreightModelPo;
import com.example.freight.model.po.WeightFreightModelPo;
import com.example.freight.model.vo.PieceFreightModelInfoVo;
import com.example.freight.model.vo.WeightFreightModelInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: core
 * @description: weight items dao
 * @author: alex101
 * @create: 2020-12-14 11:55
 **/
@Component
public class WeightFreightDao {
    private static final Logger logger = LoggerFactory.getLogger(FreightController.class);

    @Autowired
    WeightFreightModelMapper weightFreightModelMapper;
    @Autowired
    FreightModelMapper freightModelMapper;

    @Autowired
    FreightModelMapper mapper;
    public ReturnObject insertWeightItems(Long shopId, Long id, WeightFreightModelInfoVo vo)
    {

        ReturnObject<Object> returnObject;
        FreightModelPo freightModelPo = mapper.selectById(id);
        if(freightModelPo==null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopid = "+shopId+" id = "+id);
        }else if(!freightModelPo.getShopId().equals(shopId))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:"+freightModelPo.getShopId()+" not equal to path shop Id:"+shopId);
        }else {
            QueryWrapper<WeightFreightModelPo> queryWrapper = new QueryWrapper<WeightFreightModelPo>().eq("region_id",vo.getRegionId()).eq("freight_model_id",id);
            int cnt = weightFreightModelMapper.selectCount(queryWrapper);
            if(cnt!=0)
            {
                returnObject = new ReturnObject<>(ResponseCode.REGION_SAME);
            }else {
                WeightFreightModelPo weightFreightModelPo = new WeightFreightModelPo();
                weightFreightModelPo.setAbovePrice(vo.getAbovePrice());
                weightFreightModelPo.setFiftyPrice(vo.getFiftyPrice());
                weightFreightModelPo.setFirstWeight(vo.getFirstWeight());
                weightFreightModelPo.setFreightModelId(id);
                weightFreightModelPo.setHundredPrice(vo.getHundredPrice());
                weightFreightModelPo.setRegionId(vo.getRegionId());
                weightFreightModelPo.setTenPrice(vo.getTenPrice());
                weightFreightModelPo.setTrihunPrice(vo.getTrihunPrice());
                weightFreightModelPo.setGmtCreate(LocalDateTime.now());
                weightFreightModelPo.setGmtModified(LocalDateTime.now());
                weightFreightModelMapper.insert(weightFreightModelPo);
                returnObject = new ReturnObject<>(ResponseCode.OK);
            }
        }
        return returnObject;
    }

    public ReturnObject getWeightItem(Long shopId,Long id)
    {
        ReturnObject<Object> returnObject;
        FreightModelPo freightModelPo = mapper.selectById(id);
        if(freightModelPo==null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopid = "+shopId+" id = "+id);
        }else if(!freightModelPo.getShopId().equals(shopId))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:"+freightModelPo.getShopId()+" not equal to path shop Id:"+shopId);
        }else {
            QueryWrapper<WeightFreightModelPo> weightFreightModelPoQueryWrapper = new QueryWrapper<WeightFreightModelPo>().eq("freightModelId",id);
            List<WeightFreightModelPo> weightFreightModelPos = weightFreightModelMapper.selectList(weightFreightModelPoQueryWrapper);
            List<WeightFreightModelBo> weightFreightModelBos = new ArrayList<WeightFreightModelBo>(weightFreightModelPos.size());
            for (WeightFreightModelPo po:weightFreightModelPos)
            {
                weightFreightModelBos.add(new WeightFreightModelBo(po));
            }
            returnObject = new ReturnObject<>(weightFreightModelBos);
        }
        return returnObject;
    }


    /**
     * @Description: 店家或管理员修改重量运费模板明细
     * @Param:  [id, weightFreightModelInfoVo]
     * @return: {@link ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    public ReturnObject putWeightItems(Long shopId, Long id, WeightFreightModelInfoVo weightFreightModelInfoVo)
    {
        ReturnObject returnObject;
        QueryWrapper<WeightFreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        WeightFreightModelPo weightFreightModelPo = weightFreightModelMapper.selectOne(queryWrapper);
        if(weightFreightModelPo == null)
        {
            return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        QueryWrapper<WeightFreightModelPo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("region_id", weightFreightModelInfoVo.getRegionId()).eq("freight_model_id", weightFreightModelPo.getFreightModelId());;
        WeightFreightModelPo weightFreightModelPo1 = weightFreightModelMapper.selectOne(queryWrapper1);


        FreightModelPo freightModelPo = freightModelMapper.selectById(weightFreightModelPo.getFreightModelId());
        if(!freightModelPo.getShopId().equals(shopId))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
        }
        else if(weightFreightModelPo1 != null)
        {
            returnObject = new ReturnObject<>(ResponseCode.REGION_SAME);
        }
        else
        {
            UpdateWrapper<WeightFreightModelPo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);

            weightFreightModelPo.setGmtModified(LocalDateTime.now());
            weightFreightModelPo.setRegionId(weightFreightModelInfoVo.getRegionId());

            weightFreightModelPo.setAbovePrice(weightFreightModelInfoVo.getAbovePrice());
            weightFreightModelPo.setFiftyPrice(weightFreightModelInfoVo.getFiftyPrice());
            weightFreightModelPo.setFirstWeight(weightFreightModelInfoVo.getFirstWeight());
            weightFreightModelPo.setFreightModelId(id);
            weightFreightModelPo.setHundredPrice(weightFreightModelInfoVo.getHundredPrice());
            weightFreightModelPo.setTenPrice(weightFreightModelInfoVo.getTenPrice());
            weightFreightModelPo.setTrihunPrice(weightFreightModelInfoVo.getTrihunPrice());


            weightFreightModelMapper.update(weightFreightModelPo, updateWrapper);
            returnObject = new ReturnObject<>(ResponseCode.OK);
        }
        return returnObject;
    }


    /**
     * @Description: 店家或管理员删掉重量运费模板明细
     * @Param:  [id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    public ReturnObject delWeightItems(Long shopId, Long id)
    {
        logger.info("in delWeightItems dao");
        ReturnObject returnObject;
        QueryWrapper<WeightFreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        WeightFreightModelPo weightFreightModelPo = weightFreightModelMapper.selectOne(queryWrapper);

        if(weightFreightModelPo == null)
        {
            logger.info("weightFreigtmodelpo is null");
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        }else {
            logger.info("ready to select freight model by id");
            FreightModelPo freightModelPo = freightModelMapper.selectById(weightFreightModelPo.getFreightModelId());

            logger.info("freight Model shop id "+freightModelPo.getShopId());
            logger.info(freightModelPo.toString());

            if(!freightModelPo.getShopId().equals(shopId))
            {
                returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            }else {
                weightFreightModelMapper.delete(queryWrapper);
                returnObject = new ReturnObject<>(ResponseCode.OK);
            }
        }


        return returnObject;
    }
}
