package com.example.freight.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @program: core
 * @description: weightFreightModelPo
 * @author: alex101
 * @create: 2020-12-09 16:35
 **/
@TableName("weight_freight_model")
public class WeightFreightModelPo {
    @TableId(type= IdType.AUTO)
    Long id;
    Long freightModelId;
    Long firstWeight;
    Long firstWeightFreight;
    Long tenPrice;
    Long fiftyPrice;
    Long hundredPrice;
    Long trihunPrice;
    Long abovePrice;
    Long regionId;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;
}
