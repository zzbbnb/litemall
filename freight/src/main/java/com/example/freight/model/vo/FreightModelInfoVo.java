package com.example.freight.model.vo;

import lombok.Data;

import java.io.PrintWriter;

/**
 * @program: core
 * @description: 运费模板信息Vo
 * @author: alex101
 * @create: 2020-12-11 22:41
 **/
@Data
public class FreightModelInfoVo {
    private String name;

    private int type;

    private int unit;
}
