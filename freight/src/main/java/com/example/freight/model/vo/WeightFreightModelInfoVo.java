package com.example.freight.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @program: core
 * @description: WeightFreightModelInfoVo
 * @author: alex101
 * @create: 2020-12-14 11:30
 **/
@Data
public class WeightFreightModelInfoVo
{
    @Min(value = 0,message = "首重大于0")
    @NotNull(message = "首重不得为空")
    Long firstWeight;
    Long firstWeightFreight;
    Long tenPrice;
    Long fiftyPrice;
    Long hundredPrice;
    Long trihunPrice;
    Long abovePrice;
    @NotNull(message= "regionId不得为空")
    Long regionId;

}
