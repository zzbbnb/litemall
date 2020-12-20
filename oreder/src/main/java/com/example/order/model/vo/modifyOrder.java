package com.example.order.model.vo;

import lombok.Data;

/***
 * @author yansong chen
 * @time 2020-12-17 23:13
 * @description:
 */
@Data
public class modifyOrder {
    private String consignee;
    private Long regionId;
    private String address;
    private String mobile;
}
