package com.example.freight.service;

import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.example.freight.dao.FreightModelDao;
import com.example.freight.dao.PieceFreightModelDao;
import com.example.freight.model.vo.PieceFreightModelVo;
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

    @Autowired
    PieceFreightModelDao pieceFreightModelDao;

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


    /*
     * @Description: 管理员定义件数模板明细
     * @Param:  [shopId, id, pieceFreightModelVo]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @Transactional
    public ReturnObject setPieceItems(Long shopId, Long id, PieceFreightModelVo pieceFreightModelVo)
    {
        ReturnObject returnObject = freightModelDao.examIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightModelDao.setPieceItems(id, pieceFreightModelVo);
        }
        else
        {
            return returnObject;
        }
    }


    /*
     * @Description: 店家或管理员查询件数运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @Transactional
    public ReturnObject getPieceItems(Long shopId, Long id)
    {
        ReturnObject returnObject = freightModelDao.examIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightModelDao.getPieceItems(id);
        }
        else
        {
            return returnObject;
        }
    }

    /*
     * @Description: 店家或管理员修改件数运费模板明细
     * @Param:  [shopId, id, pieceFreightModelVo]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @Transactional
    public ReturnObject putPieceItems(Long shopId, Long id, PieceFreightModelVo pieceFreightModelVo)
    {
        ReturnObject returnObject = freightModelDao.examFreightModelIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightModelDao.putPieceItems(id, pieceFreightModelVo);
        }
        else
        {
            return returnObject;
        }
    }


    /*
     * @Description: 店家或管理员删掉件数运费模板明细
     * @Param:  [shopId, id]
     * @return: {@link cn.edu.xmu.ooad.util.ReturnObject}
     * @Author: lzn
     * @Date 2020/12/14
     **/
    @Transactional
    public ReturnObject delPieceItems(Long shopId, Long id)
    {
        ReturnObject returnObject = freightModelDao.examFreightModelIdScope(shopId, id);
        if(returnObject.getCode() == ResponseCode.OK)
        {
            return pieceFreightModelDao.delPieceItems(id);
        }
        else
        {
            return returnObject;
        }
    }
}
