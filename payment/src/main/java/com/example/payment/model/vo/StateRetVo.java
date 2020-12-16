package com.example.payment.model.vo;

import lombok.Data;

/**
 * @program: core
 * @description: statevo
 * @author: alex101
 * @create: 2020-12-16 10:10
 **/
@Data
public class StateRetVo {
    Byte code;
    String name;

    public StateRetVo(Byte code) {
        this.code = code;
        switch (code)
        {
            case 0:
                name="未支付";
                break;
            case 1:
                name = "已支付";
                break;
            case 2:
                name = "支付失败";
                break;
            default:
                name = "error state";
                break;
        }
    }
}

