package com.example.freight.dao;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.freight.controller.FreightController;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.model.bo.FreightModelBo;
import com.example.freight.model.bo.PieceFreightModelBo;
import com.example.freight.model.po.FreightModelPo;
import com.example.freight.model.po.PieceFreightModelPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @program: core
 * @description: FreightModelDao
 * @author: alex101
 * @create: 2020-12-09 18:01
 **/
@Component
public class FreightModelDao {

    private static final int weightType = 0;
    private static final int peiceType = 1;
    private static final Logger logger = LoggerFactory.getLogger(FreightController.class);

    @Autowired
    private FreightModelMapper freightModelMapper;

    /*
    /** 
    * @Description: 设置默认运费模板 
    * @Param: [shopid, id] 
    * @return: cn.edu.xmu.ooad.util.ReturnObject 
    * @Author: alex101
    * @Date: 2020/12/9 
    */
    @Transactional
    public ReturnObject setDefaultFreightModel(Long shopId,Long id)
    {

        ReturnObject returnObject;
        FreightModelPo freightModelPo = freightModelMapper.selectById(id);
        if(freightModelPo==null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopid = "+shopId+" id = "+id);
        }else if(!freightModelPo.getShopId().equals(shopId)) {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:"+freightModelPo.getShopId()+" not equal to path shop Id:"+shopId);
        }else if(freightModelPo.getType()==peiceType) {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("can't set piece type model to default "+freightModelPo.getId());
        } else{
            /*将原始的默认模板取消*/
            UpdateWrapper<FreightModelPo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("default_model",true).set("default_model",false).set("gmt_modified",LocalDateTime.now());
            freightModelMapper.update(null,updateWrapper);

            /**设置新默认模板**/
            freightModelPo.setDefaultModel(true);

            freightModelPo.setGmtModified(LocalDateTime.now());//修改时间
            freightModelMapper.updateById(freightModelPo);
            returnObject = new ReturnObject<>(ResponseCode.OK);
            logger.info("found freightModel");
        }
        return returnObject;
    }


    /*
    /** 
    * @Description: 返回模板概要 
    * @Param: [shopId, id] 
    * @return: cn.edu.xmu.ooad.util.ReturnObject 
    * @Author: alex101
    * @Date: 2020/12/10 
    */
    public ReturnObject getFreightModelSummary(Long shopId,Long id)
    {
        ReturnObject returnObject;
        FreightModelPo freightModelPo = freightModelMapper.selectById(id);
        if(freightModelPo==null)
        {
            logger.error("not found freightModel shopid = "+shopId+" id = "+id);
            returnObject =  new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        }else if(!freightModelPo.getShopId().equals(shopId)) {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:" + freightModelPo.getShopId() + " not equal to path shop Id:" + shopId);
        }else {
            FreightModelBo freightModelBo = new FreightModelBo(freightModelPo);
            returnObject = new ReturnObject<>(freightModelBo);
        }
        return returnObject;
    }


    /*
     * @Description:管理员克隆店铺的运费模板
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/10
     **/
    public ReturnObject cloneFreightModel(Long shopId, Long id)
    {
        ReturnObject returnObject;
        FreightModelPo freightModelPo = freightModelMapper.selectById(id);
        if (freightModelPo == null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopId = " + shopId + " id = " + id);
        }
        else if (!freightModelPo.getShopId().equals(shopId))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:" + freightModelPo.getShopId() + " not equal to path shop Id:" + shopId);
        }
        else
        {
            FreightModelPo freightModelPo2 = freightModelPo.objectClone();
            freightModelPo2.setGmtCreate(LocalDateTime.now());
            freightModelPo2.setName(freightModelPo.getName() + UUID.randomUUID());
            freightModelMapper.insert(freightModelPo2);
            FreightModelBo freightModelBo = new FreightModelBo(freightModelPo2);
            returnObject = new ReturnObject<>(freightModelBo);
            logger.info("found freightModel to be cloned");
        }
        return returnObject;
    }


    /*
     * @Description: (查看id与shopId是否对应)
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    public ReturnObject examIdScope(Long shopId, Long id)
    {
        ReturnObject returnObject = new ReturnObject<>(ResponseCode.OK);
        FreightModelPo freightModelPo = freightModelMapper.selectById(id);
        if (freightModelPo == null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopId = " + shopId + " id = " + id);
        }
        else if (!freightModelPo.getShopId().equals(shopId))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:" + freightModelPo.getShopId() + " not equal to path shop Id:" + shopId);
        }
        return returnObject;
    }



    /*
     * @Description: (查看FreightModelId与shopId是否对应)
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    public ReturnObject examFreightModelIdScope(Long shopId, Long id)
    {
        ReturnObject returnObject = new ReturnObject<>(ResponseCode.OK);
        QueryWrapper<FreightModelPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("freightModelId", id);
        FreightModelPo freightModelPo = freightModelMapper.selectOne(queryWrapper);
        if (freightModelPo == null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopId = " + shopId + " id = " + id);
        }
        else if (!freightModelPo.getShopId().equals(shopId))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("freightModel shop Id:" + freightModelPo.getShopId() + " not equal to path shop Id:" + shopId);
        }
        return returnObject;
    }
}
