package com.example.freight.service;

import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.dao.FreightModelDao;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.model.po.FreightModelPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: core
 * @description: 运费服务
 * @author: alex101
 * @create: 2020-12-09 17:43
 **/
@Service
public class FreightService {
    @Autowired
    FreightModelDao freightModelDao;

    /*
    /** 
    * @Description: 设置默认运费模板 
    * @Param: [shopId, id] 
    * @return: cn.edu.xmu.ooad.util.ReturnObject 
    * @Author: alex101
    * @Date: 2020/12/9 
    */
    public ReturnObject setDefaultFreightModel(@PathVariable Long shopId, @PathVariable Long id) {
        return freightModelDao.setDefaultFreightModel(shopId,id);
    }
}
