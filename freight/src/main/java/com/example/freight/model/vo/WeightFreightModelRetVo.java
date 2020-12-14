package com.example.freight.model.vo;

import com.example.freight.model.bo.WeightFreightModelBo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: core
 * @description: weight freight ret vo
 * @author: alex101
 * @create: 2020-12-14 14:52
 **/
@Data
public class WeightFreightModelRetVo {
    private Long id;
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
    public WeightFreightModelRetVo(WeightFreightModelBo bo)
    {
        id = bo.getId();
        firstWeight = bo.getFirstWeight();
        firstWeightFreight = bo.getFirstWeightFreight();
        tenPrice = bo.getTenPrice();
        fiftyPrice =bo.getFiftyPrice();
        hundredPrice = bo.getHundredPrice();
        trihunPrice = bo.getTrihunPrice();
        abovePrice = bo.getAbovePrice();
        regionId = bo.getRegionId();
        gmtCreate = bo.getGmtCreate();
        gmtModified = bo.getGmtModified();
    }
}
