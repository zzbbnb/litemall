package com.example.freight.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @program: core
 * @description: freightModelPo
 * @author: alex101
 * @create: 2020-12-09 16:02
 **/
@TableName("freight_model")
@Data
public class FreightModelPo {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long shopId;
    private String name;
    private boolean defaultModel;
    private int unit;
    private int type;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
