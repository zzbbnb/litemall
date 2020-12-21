package com.example.freight.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @program: core
 * @description: pieceFreightModel
 * @author: alex101
 * @create: 2020-12-09 16:34
 **/
@Data
@TableName("piece_freight_model")
public class PieceFreightModelPo {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long freightModelId;
    private int firstItems;
    private Long firstItemsPrice;
    private int additionalItems;
    private Long additionalItemsPrice;
    @TableField("region_id")
    private Long regionId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
