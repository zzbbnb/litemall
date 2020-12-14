package com.example.freight.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @program: core
 * @description: weightFreightModelPo
 * @author: alex101
 * @create: 2020-12-09 16:35
 **/
@Data
@TableName("weight_freight_model")
public class WeightFreightModelPo {
    @TableId(type= IdType.AUTO)
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
}
