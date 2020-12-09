package com.example.freight.controller;

import cn.edu.xmu.ooad.util.JacksonUtil;
import cn.edu.xmu.ooad.util.JwtHelper;
import com.example.freight.FreightApplication;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


import java.lang.management.LockInfo;

/**
 * @program: core
 * @description: TEST1
 * @author: alex101
 * @create: 2020-12-09 18:27
 **/
@AutoConfigureMockMvc
@Transactional
@SpringBootTest(classes = FreightApplication.class)   //标识本类是一个SpringBootTest
public class FreightControllerTest {

    @Autowired
    private MockMvc mvc;


    /**
    * @Description: 测试模板id不存在
    * @Param:
    * @return:
    * @Author: alex101
    * @Date: 2020/12/9
    */
    @Test
    public void setDefaultFreightModel1()throws Exception
    {
        new JwtHelper();
//        String token = new JwtHelper().createToken(100L, 100l,100);
//        String responseString = this.mvc.perform(post("/freight/shops/{1}/freight_models/{1}/default").header("authorization",token))
//                .andExpect(status().is5xxServerError())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andReturn().getResponse().getContentAsString();
    }

}
