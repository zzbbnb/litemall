package com.example.freight.dao;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.freight.controller.FreightController;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.mapper.WeightFreightModelMapper;
import com.example.freight.model.bo.WeightFreightModelBo;
import com.example.freight.model.po.FreightModelPo;
import com.example.freight.model.po.WeightFreightModelPo;
import com.example.freight.model.vo.WeightModelInfoVo;
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
    FreightModelMapper mapper;
    public ReturnObject insertWeightItems(Long shopId, Long id, WeightModelInfoVo vo)
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
            QueryWrapper<WeightFreightModelPo> queryWrapper = new QueryWrapper<WeightFreightModelPo>().eq("regionId",vo.getRegionId());
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

}
