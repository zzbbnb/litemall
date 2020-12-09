package com.example.freight.dao;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.controller.FreightController;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.model.po.FreightModelPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: core
 * @description: FreightModelDao
 * @author: alex101
 * @create: 2020-12-09 18:01
 **/
@Component
public class FreightModelDao {

    private static final Logger logger = LoggerFactory.getLogger(FreightController.class);

    @Autowired
    FreightModelMapper freightModelMapper;

    /*
    /** 
    * @Description: 设置默认运费模板 
    * @Param: [shopid, id] 
    * @return: cn.edu.xmu.ooad.util.ReturnObject 
    * @Author: alex101
    * @Date: 2020/12/9 
    */
    @Transactional
    public ReturnObject setDefaultFreightModel(Long shopid,Long id)
    {

        ReturnObject returnObject;
        FreightModelPo freightModelPo = freightModelMapper.selectById(id);
        if(freightModelPo==null)
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            logger.error("not found freightModel shopid = "+shopid+" id = "+id);
        }else if(!freightModelPo.getShopId().equals(shopid))
        {
            returnObject = new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE);
            logger.error("not found freightModel shopid = "+shopid+" id = "+id);
        }else {
            freightModelPo.setDefaultModel(true);
            returnObject = new ReturnObject<>(ResponseCode.OK);
            logger.info("found feightModel");
        }
        return returnObject;
    }
}
