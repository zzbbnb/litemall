package com.example.freight.service;

import com.example.freight.model.bo.FreightModelBo;
import com.example.freight.model.po.FreightModelPo;
import org.springframework.stereotype.Service;

/**
* @Description: dubbo服务，其他组提供，仅用于根据skuid得到freightModel,目前仅测试使用
* @Param:
* @return:
* @Author: alex101
* @Date: 2020/12/14
*/
@Service
public interface IFreightModelService {
    public FreightModelBo getFreightIdBySkuId(Long skuId);
    public Integer getWeightBySkuId(Long skuId);
}
