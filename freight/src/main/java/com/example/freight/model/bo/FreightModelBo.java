package com.example.freight.model.bo;

import cn.edu.xmu.ooad.model.VoObject;
import com.example.freight.model.po.FreightModelPo;
import com.example.freight.model.vo.FreightModelRetVo;
import com.example.freight.model.vo.OrderItemComputeVo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: core
 * @description: FreightModelBo
 * @author: alex101
 * @create: 2020-12-11 09:20
 **/
@Data
public class FreightModelBo implements VoObject {
    private FreightModelDetail freightModelDetail;
    private Long id;
    private Long shopId;
    private String name;
    private boolean defaultModel;
    private int unit;
    private int type;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

    public FreightModelBo(FreightModelPo freightModelPo)
    {
        id = freightModelPo.getId();
        shopId = freightModelPo.getShopId();
        name = freightModelPo.getName();
        defaultModel = freightModelPo.isDefaultModel();
        unit = freightModelPo.getUnit();
        type = freightModelPo.getType();
        gmtCreate = freightModelPo.getGmtCreate();
        gmtModified = freightModelPo.getGmtModified();
    }

    Long computeFreight(OrderItemComputeVo orderItemComputeVo)
    {
        return freightModelDetail.getFreight(orderItemComputeVo);
    }
    @Override
    public Object createVo() {
        return new FreightModelRetVo(this);
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
