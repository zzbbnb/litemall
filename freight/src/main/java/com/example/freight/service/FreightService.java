package com.example.freight.service;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.dao.FreightModelDao;
import com.example.freight.dao.PieceFreightDao;
import com.example.freight.model.vo.PieceFreightModelInfoVo;
import cn.edu.xmu.ooad.model.VoObject;
import com.example.freight.dao.WeightFreightDao;
import com.example.freight.model.vo.FreightModelInfoVo;
import com.example.freight.model.vo.WeightFreightModelInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: core
 * @description: 运费服务
 * @author: alex101
 * @create: 2020-12-09 17:43
 */
@Service
public class FreightService {
    @Autowired
    FreightModelDao freightModelDao;

    @Autowired

    PieceFreightDao pieceFreightDao;

    WeightFreightDao weightFreightDao;

    /** 
    * @Description: 设置默认运费模板 
    * @Param: [shopId, id] 
    * @return: cn.edu.xmu.ooad.util.ReturnObject 
    * @Author: alex101
    * @Date: 2020/12/9 
    */
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public ReturnObject addFreightModel(Long id,FreightModelInfoVo vo)
    {
        return freightModelDao.addFreightModel(id,vo);
    }
  
  
    /**
     * @Description: 管理员克隆店铺的运费模板
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/10
     */
    @Transactional
    public ReturnObject cloneFreightModel(@PathVariable Long shopId, @PathVariable Long id)
    {
        return freightModelDao.cloneFreightModel(shopId, id);
    }

  
    /**
     * @Description: 管理员定义件数模板明细
     * @Param:  [shopId, id, pieceFreightModelInfoVo]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    @Transactional
    public ReturnObject setPieceItems(Long shopId, Long id, PieceFreightModelInfoVo pieceFreightModelInfoVo)
    {
        ReturnObject returnObject = freightModelDao.examIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightDao.setPieceItems(id, pieceFreightModelInfoVo);
        }
        else
        {
            return returnObject;
        }
    }


    /**
     * @Description: 店家或管理员查询件数运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    @Transactional
    public ReturnObject getPieceItems(Long shopId, Long id)
    {
        ReturnObject returnObject = freightModelDao.examIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightDao.getPieceItems(id);
        }
        else
        {
            return returnObject;
        }
    }

    /**
     * @Description: 店家或管理员修改件数运费模板明细
     * @Param:  [shopId, id, pieceFreightModelInfoVo]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    @Transactional
    public ReturnObject putPieceItems(Long shopId, Long id, PieceFreightModelInfoVo pieceFreightModelInfoVo)
    {
        ReturnObject returnObject = freightModelDao.examFreightModelIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightDao.putPieceItems(id, pieceFreightModelInfoVo);
        }
        else
        {
            return returnObject;
        }
    }


    /**
     * @Description: 店家或管理员删掉件数运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    @Transactional
    public ReturnObject delPieceItems(Long shopId, Long id)
    {
        ReturnObject returnObject = freightModelDao.examFreightModelIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightDao.delPieceItems(id);
        }
        else
        {
            return returnObject;
        }
    }


    /**
     * @Description: 店家或管理员修改件数模板明细
     * @Param:  [shopId, id, weightFreightModelInfoVo]
     * @return: {@link ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    @Transactional
    public ReturnObject putWeightItems(Long shopId, Long id, WeightFreightModelInfoVo weightFreightModelInfoVo)
    {
        ReturnObject returnObject = freightModelDao.examFreightModelIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return weightFreightDao.putWeightItems(id, weightFreightModelInfoVo);
        }
        else
        {
            return returnObject;
        }
    }


    /**
     * @Description: 店家或管理员删掉重量运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     */
    @Transactional
    public ReturnObject delWeightItems(Long shopId, Long id)
    {
        ReturnObject returnObject = freightModelDao.examFreightModelIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return weightFreightDao.delWeightItems(id);
        }
        else
        {
            return returnObject;
        }
    }

    public ReturnObject<PageInfo<VoObject>> getGoodsFreightModel(Long id,String name,int page,int pageSize)
    {
         ReturnObject<PageInfo<VoObject>> ret =freightModelDao.getGoodsFreightModel(id,name,page,pageSize);
         return ret;
    }

    @Transactional(rollbackFor = Exception.class)
    public  ReturnObject modifyFreightModel(Long shopId, Long id,FreightModelInfoVo vo)
    {
        return freightModelDao.modifyFreightModel(shopId,id,vo);
    }

    public ReturnObject deleteFreightModel(Long shopId,Long id)
    {
        return freightModelDao.deleteFreightModel(shopId,id);
    }

    @Transactional(rollbackFor = Exception.class)
    public ReturnObject addWeightItem(Long shopId, Long id, WeightFreightModelInfoVo vo)
    {
        return weightFreightDao.insertWeightItems(shopId,id,vo);
    }

    public ReturnObject getWeightItem(Long shopId,Long id)
    {
        return weightFreightDao.getWeightItem(shopId,id);
    }
}
