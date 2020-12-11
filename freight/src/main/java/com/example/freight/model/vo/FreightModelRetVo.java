package com.example.freight.model.vo;

import com.example.freight.model.bo.FreightModelBo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: core
 * @description: FreightRetVo
 * @author: alex101
 * @create: 2020-12-10 12:25
 **/
@Data
public class FreightModelRetVo {
    private Long id;
    private String name;
    private int type;
    private int unit;
    @JsonProperty(value = "default")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private boolean defaultModel;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

    public FreightModelRetVo(FreightModelBo freightModelBo)
    {
        id = freightModelBo.getId();
        name = freightModelBo.getName();
        type = freightModelBo.getType();
        unit = freightModelBo.getUnit();
        defaultModel = freightModelBo.isDefaultModel();
        gmtCreate  = freightModelBo.getGmtCreate();
        gmtModified = freightModelBo.getGmtModified();
    }


}
