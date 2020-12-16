package com.example.payment.model.vo;

import com.example.payment.result.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class State {
    int code;
    String name;


    public State(ResponseCode re)
    {
        this.code=re.getCode();
        this.name=re.getMessage();
    }
    public State(int c,String s)
    {
        code=c;
        name=s;
    }
}
