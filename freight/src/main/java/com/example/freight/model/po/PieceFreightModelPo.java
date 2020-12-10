package com.example.freight.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
    Long id;
    Long freightModelId;
    int firstItems;
    Long firstItemsPrice;
    int additionalItems;
    Long additionalItemsPrice;
    Long regionId;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;
}
