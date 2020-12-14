package com.example.freight.service;

import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.dao.FreightModelDao;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.model.bo.FreightModelBo;
import com.example.freight.model.po.FreightModelPo;
import com.example.freight.model.vo.FreightModelInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Name;

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

    /**
    * @Description: 增加运费模板
    * @Param: [id, vo]
    * @return: cn.edu.xmu.ooad.util.ReturnObject
    * @Author: alex101
    * @Date: 2020/12/12
    */
    public ReturnObject addFreightModel(Long id,FreightModelInfoVo vo)
    {
        return freightModelDao.addFreightModel(id,vo);
    }

    public ReturnObject<PageInfo<VoObject>> getGoodsFreightModel(Long id,String name,int page,int pageSize)
    {
         ReturnObject<PageInfo<VoObject>> ret =freightModelDao.getGoodsFreightModel(id,name,page,pageSize);
         return ret;
    }

    public  ReturnObject modifyFreightModel(Long shopId, Long id,FreightModelInfoVo vo)
    {
        return freightModelDao.modifyFreightModel(shopId,id,vo);
    }

    public ReturnObject deleteFreightModel(Long shopId,Long id)
    {
        return freightModelDao.deleteFreightModel(shopId,id);
    }


}
