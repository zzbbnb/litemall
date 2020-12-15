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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.sound.midi.Soundbank;

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
    public void setDefaultFreightModel1() throws Exception {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freight_models/-1/default").header("authorization", token))
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
    public void setDefaultFreightModel2() throws Exception {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freight_models/1/default").header("authorization", token))
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
    public void setDefaultFreightModel3() throws Exception {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(post("/freight/shops/56/freight_models/2/default").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";
        JSONAssert.assertEquals(expectedResponse, responseString, true);
        FreightModelPo freightModelPo = freightModelMapper.selectById(2);
        Assert.state(freightModelPo.isDefaultModel() == true, "默认模板设置失败！");
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
    public void getFreightModelSummary1() throws Exception {
        String token = new JwtHelper().createToken(100L, 100l, 100);
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

    /**
     * @Description: 测试正常增加运费模板
     * @Param: []
     * @return: void
     * @Author: alex101
     * @Date: 2020/12/15
     */
    @Test
    public void addFreightModel() throws Exception {

        String requireJson = null;
        requireJson = "{\"name\":\"sim\",\"type\":0,\"unit\":500}";
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(post("/freight/shops/100/freightmodels").header("authorization", token).contentType("application/json;charset=UTF-8").content(requireJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        String id = responseString.substring(24, 27);
        String URL = "/freight/shops/100/freightmodels/" + id;
        String expectString = this.mvc.perform(get(URL).header("authorization", token).contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(expectString, responseString, true);
    }

    /**
     * @Description: 定义运费模板时重名
     * @Param: []
     * @return: void
     * @Author: alex101
     * @Date: 2020/12/15
     */
    @Test
    public void addFreightModel1() throws Exception {
        String requireJson = null;
        requireJson = "{\"name\":\"3V2QG1\",\"type\":0,\"unit\":500}";
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(post("/freight/shops/100/freightmodels").header("authorization", token).contentType("application/json;charset=UTF-8").content(requireJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.FREIGHTNAME_SAME.getCode()))
                .andReturn().getResponse().getContentAsString();

    }

    /**
     * @Description: 获取商品运费模板 第一页
     * @Param: []
     * @return: void
     * @Author: alex101
     * @Date: 2020/12/15
     */
    @Test
    public void getGoodsFreightModels() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(get("/freight/shops/58/freightmodels").header("authorization", token).contentType("application/json;charset=UTF-8"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectResponse = "{\"errno\":0,\"data\":{\"total\":9,\"pages\":1,\"pageSize\":10,\"page\":1,\"list\":[{\"id\":56,\"name\":\"MD13A7\",\"type\":0,\"unit\":64,\"gmtCreate\":\"2020-12-10 09:33:28\",\"gmtModified\":\"2020-12-10 09:33:28\",\"default\":0},{\"id\":125,\"name\":\"R2125M\",\"type\":1,\"unit\":91,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":219,\"name\":\"LANF5F\",\"type\":1,\"unit\":52,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":252,\"name\":\"FFUWZ9\",\"type\":0,\"unit\":22,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":0},{\"id\":253,\"name\":\"9JY74Y\",\"type\":1,\"unit\":35,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":0},{\"id\":270,\"name\":\"BT6GQA\",\"type\":1,\"unit\":43,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":272,\"name\":\"4YDZT6\",\"type\":0,\"unit\":4,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":285,\"name\":\"BU9T9U\",\"type\":1,\"unit\":53,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":424,\"name\":\"3QFV3U\",\"type\":0,\"unit\":1,\"gmtCreate\":\"2020-12-10 09:33:30\",\"gmtModified\":\"2020-12-10 09:33:30\",\"default\":1}]},\"errmsg\":\"成功\"}";
        JSONAssert.assertEquals(responseString,expectResponse,true);
    }

    /**
    * @Description: 获得商品运费模板，按名查询
    * @Param:
    * @return:
    * @Author: alex101
    * @Date: 2020/12/15
    */
    @Test
    public void getGoodsFreightModels1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(get("/freight/shops/58/freightmodels").header("authorization", token).queryParam("name","MD13A7").contentType("application/json;charset=UTF-8"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectResponse = "{\"errno\":0,\"data\":{\"total\":1,\"pages\":1,\"pageSize\":10,\"page\":1,\"list\":[{\"id\":56,\"name\":\"MD13A7\",\"type\":0,\"unit\":64,\"gmtCreate\":\"2020-12-10 09:33:28\",\"gmtModified\":\"2020-12-10 09:33:28\",\"default\":0}]},\"errmsg\":\"成功\"}";
        JSONAssert.assertEquals(responseString,expectResponse,true);
        System.out.println(responseString);
    }

    /***
    * @Description: 测试第二页
    * @Param: []
    * @return: void
    * @Author: alex101
    * @Date: 2020/12/15
    */
    @Test
    public void getGoodsFreightModels2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(get("/freight/shops/58/freightmodels").header("authorization", token)
                .queryParam("page","2")
                .queryParam("pageSize","5")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectResponse = "{\"errno\":0,\"data\":{\"total\":9,\"pages\":2,\"pageSize\":5,\"page\":2,\"list\":[{\"id\":270,\"name\":\"BT6GQA\",\"type\":1,\"unit\":43,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":272,\"name\":\"4YDZT6\",\"type\":0,\"unit\":4,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":285,\"name\":\"BU9T9U\",\"type\":1,\"unit\":53,\"gmtCreate\":\"2020-12-10 09:33:29\",\"gmtModified\":\"2020-12-10 09:33:29\",\"default\":1},{\"id\":424,\"name\":\"3QFV3U\",\"type\":0,\"unit\":1,\"gmtCreate\":\"2020-12-10 09:33:30\",\"gmtModified\":\"2020-12-10 09:33:30\",\"default\":1}]},\"errmsg\":\"成功\"}";
        JSONAssert.assertEquals(responseString,expectResponse,true);
    }

    /**
    * @Description: 测试模板名重复
    * @Param:
    * @return:
    * @Author: alex101
    * @Date: 2020/12/15
    */
    @Test
    public void modifyFreightModel() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String requireJson = null;
        requireJson = "{\"name\":\"3V2QG1\",\"unit\":500}";
        String responseString = this.mvc.perform(put("/freight/shops/58/freightmodels/424").header("authorization", token)
                .contentType("application/json;charset=UTF-8").content(requireJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.FREIGHTNAME_SAME.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /** 
    * @Description: 测试正常修改模板 
    * @Param: [] 
    * @return: void 
    * @Author: alex101
    * @Date: 2020/12/15 
    */
    @Test
    public void modifyFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String requireJson = null;
        requireJson = "{\"name\":\"Sim\",\"unit\":600}";
        String responseString = this.mvc.perform(put("/freight/shops/58/freightmodels/424").header("authorization", token)
                .contentType("application/json;charset=UTF-8").content(requireJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        FreightModelPo freightModelPo = freightModelMapper.selectById(424);
        Assert.state(freightModelPo.getName().equals("Sim")==true,"模板修改失败");
        Assert.state(freightModelPo.getUnit()==600,"模板修改失败");

    }


    /** 
    * @Description: 删除运费模板
    * @Param:  
    * @return:  
    * @Author: alex101
    * @Date: 2020/12/15 
    */
    @Test
    public void deleteFreightModel() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/58/freightmodels/253").header("authorization", token)
        .contentType("application/json;charset=UTF-8"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        FreightModelPo freightModelPo = freightModelMapper.selectById(253);
        Assert.state(freightModelPo==null,"删除失败");
    }
}
