package com.example.payment.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PayPatternAndNameRetVo {
    String payPattern;
    String name;
    public PayPatternAndNameRetVo(String p, String s)
    {
        payPattern=p;
        name=s;
    }
}
