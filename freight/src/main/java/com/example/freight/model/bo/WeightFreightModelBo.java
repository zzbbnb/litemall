package com.example.freight.model.bo;

import cn.edu.xmu.ooad.model.VoObject;
import com.example.freight.model.po.WeightFreightModelPo;
import com.example.freight.model.vo.OrderItemComputeVo;
import com.example.freight.model.vo.WeightFreightModelRetVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: core
 * @description:
 * @author: alex101
 * @create: 2020-12-11 09:22
 **/
@Data
public class WeightFreightModelBo implements VoObject,FreightModelDetail {

    private Long id;
    private Long freightModelId;
    private Long firstWeight;
    private Long firstWeightFreight;
    private Long tenPrice;
    private Long fiftyPrice;
    private Long hundredPrice;
    private Long trihunPrice;
    private Long abovePrice;
    private Long regionId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

    public WeightFreightModelBo(WeightFreightModelPo po)
    {
        id = po.getId();
        freightModelId = po.getFreightModelId();
        firstWeight = po.getFirstWeight();
        firstWeightFreight = po.getFirstWeightFreight();
        tenPrice = po.getTenPrice();
        fiftyPrice = po.getFiftyPrice();
        hundredPrice = po.getHundredPrice();
        trihunPrice = po.getTrihunPrice();
        abovePrice = po.getAbovePrice();
        regionId = po.getRegionId();
        gmtCreate = po.getGmtCreate();
        gmtModified = po.getGmtModified();
    }

    @Override
    public Object createVo() {
        return new WeightFreightModelRetVo(this);
    }

    @Override
    public Object createSimpleVo() {
        return new WeightFreightModelRetVo(this);
    }

    @Override
    public Long getFreight(int weight,Long count) {
        return null;
    }
}
