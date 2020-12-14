package com.example.freight.controller;

import cn.edu.xmu.ooad.util.JacksonUtil;
import cn.edu.xmu.ooad.util.JwtHelper;
import cn.edu.xmu.ooad.util.ResponseCode;
import com.example.freight.FreightApplication;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.model.po.FreightModelPo;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


import java.lang.management.LockInfo;
import java.sql.Date;
import java.time.LocalDateTime;

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

    @Autowired
    private FreightModelMapper freightModelMapper;

    /**
    * @Description: 测试操作id不存在
    * @Param:
    * @return:
    * @Author: alex101
    * @Date: 2020/12/9
    */

    @Test
    public void setDefaultFreightModel1()throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l,100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freight_models/-1/default").header("authorization",token))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
    }

    /*
    /**
    * @Description: 测试路径shopId和表中的shopId无法对应
    * @Param: []
    * @return: void
    * @Author: alex101
    * @Date: 2020/12/10
    */
    @Test
    public void setDefaultFreightModel2()throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l,100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freight_models/1/default").header("authorization",token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();

    }

    /*
    /** 
    * @Description: 正常设置默认模板
    * @Param: [] 
    * @return: void 
    * @Author: alex101
    * @Date: 2020/12/10 
    */
    @Test
    public void setDefaultFreightModel3()throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l,100);
        String responseString = this.mvc.perform(post("/freight/shops/56/freight_models/2/default").header("authorization",token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";
        JSONAssert.assertEquals(expectedResponse, responseString, true);
        FreightModelPo freightModelPo = freightModelMapper.selectById(2);
        Assert.state(freightModelPo.isDefaultModel()==true, "默认模板设置失败！");
    }

    /*
    /** 
    * @Description: 正常获取运费模板概要 
    * @Param: [] 
    * @return: void 
    * @Author: alex101
    * @Date: 2020/12/10 
    */
    @Test
    public void getFreightModelSummary1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l,100);
        String responseString = this.mvc.perform(get("/freight/shops/56/freightmodels/2").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
        String expectedResponse = "{" +
                "\"errno\":0," +
                " \"data\":{" +
                " \"id\":2," +
                " \"name\":\"3SP3CG\"," +
                "\"type\":0," +
                "\"unit\":15," +
                "\"default\":0," +
                "\"gmtCreate\":\"2020-12-10 09:33:28\"," +
                "\"gmtModified\":\"2020-12-10 09:33:28\"" +
                "}," + "\"errmsg\":\"成功\"" +
                "}";
        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void addFreightModel() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l,100);
        String responseString = this.mvc.perform(get("freight/shops/100/freightmodels").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
