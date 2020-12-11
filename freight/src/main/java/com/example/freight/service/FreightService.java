package com.example.freight.service;

import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.dao.FreightModelDao;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.model.po.FreightModelPo;
import com.example.freight.model.vo.FreightModelInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public ReturnObject setDefaultFreightModel(Long shopId, Long id) {
        return freightModelDao.setDefaultFreightModel(shopId,id);
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
        return freightModelDao.getFreightModelSummary(shopId,id);
    }

  
    /*
     * @Description: 管理员克隆店铺的运费模板
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/10
     **/
    @Transactional
    public ReturnObject cloneFreightModel(@PathVariable Long shopId, @PathVariable Long id)
    {
        return freightModelDao.cloneFreightModel(shopId, id);
    }
}
