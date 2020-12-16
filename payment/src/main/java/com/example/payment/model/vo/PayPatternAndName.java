package com.example.payment.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PayPatternAndName {
    String payPattern;
    String name;
    public PayPatternAndName(String p,String s)
    {
        payPattern=p;
        name=s;
    }
}
