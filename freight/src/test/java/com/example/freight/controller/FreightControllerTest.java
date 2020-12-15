package com.example.freight.controller;

import cn.edu.xmu.ooad.util.JacksonUtil;
import cn.edu.xmu.ooad.util.JwtHelper;
import cn.edu.xmu.ooad.util.ResponseCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.freight.FreightApplication;
import com.example.freight.mapper.FreightModelMapper;
import com.example.freight.mapper.PieceFreightModelMapper;
import com.example.freight.model.po.FreightModelPo;

import com.example.freight.model.po.PieceFreightModelPo;
import com.example.freight.model.vo.PieceFreightModelInfoVo;
import com.example.freight.model.vo.WeightFreightModelInfoVo;
import com.google.gson.JsonObject;
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

    @Autowired
    private PieceFreightModelMapper pieceFreightModelMapper;

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


    /**
     * @Description: 克隆运费模板（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void cloneFreightModel1() throws Exception
    {
        String token= new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/9/clone").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();
        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";
        JSONAssert.assertEquals(expectedResponse, responseString, false);


        JSONObject responseData = JSONObject.parseObject(responseString).getJSONObject("data");
        Long id = responseData.getLong("id");
        String queryResponseString = this.mvc.perform(get("/freight/shops/1/freightmodels/" + String.valueOf(id)).header("authorization", token))
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andExpect(jsonPath("$.errmsg").value(ResponseCode.OK.getMessage()))
                .andExpect(jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();;

        JSONAssert.assertEquals(queryResponseString, responseString, true);
    }


    /**
     * @Description: 克隆模板，资源不存在
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void cloneFreightModel2() throws Exception
    {
        String token= new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/20/clone").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 克隆模板，操作的id不是自己的对象 （暂时通不过，后端状态码需要统一更改200->403）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void cloneFreightModel3() throws Exception
    {
        String token= new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/13/clone").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 定义件数运费模板（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void setPieceFreightModel1() throws Exception
    {
        String token= new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 123L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/9/pieceItems").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);
        JSONObject responseData = JSONObject.parseObject(responseString).getJSONObject("data");
        Long id = responseData.getLong("id");

        PieceFreightModelPo pieceFreightModelPo = pieceFreightModelMapper.selectById(id);

        Assert.state(pieceFreightModelPo != null, "件数运费模板定义失败");
    }

    /**
     * @Description: 定义件数运费模板（资源不存在）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void setPieceFreightModel2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 123L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/20/pieceItems").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 定义件数运费模板（操作的id不是自己的）（暂时通不过，后端状态码需要统一更改200->403）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void setPieceFreightModel3() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 123L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/13/pieceItems").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 定义件数运费模板（运费模板中该地区已定义）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void setPieceFreightModel4() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 200L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(post("/freight/shops/1/freightmodels/9/pieceItems").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.REGION_SAME.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 查询件数运费模板（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void getPieceFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(get("/freight/shops/1/freightmodels/9/pieceItems").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andExpect(jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 查询件数运费模板（资源不存在）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void getPieceFreightModel2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(get("/freight/shops/1/freightmodels/20/pieceItems").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 查询件数运费模板（操作的id不是自己的）（暂时通不过，后端状态码需要统一更改200->403）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void getPieceFreightModel3() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(get("/freight/shops/1/freightmodels/20/pieceItems").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 修改重量模板明细（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putWeightFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        WeightFreightModelInfoVo vo = new WeightFreightModelInfoVo();
        vo.setRegionId(1000L);
        vo.setFirstWeight(10L);
        vo.setFirstWeightFreight(10L);
        vo.setTenPrice(10L);
        vo.setFiftyPrice(10L);
        vo.setHundredPrice(10L);
        vo.setTrihunPrice(10L);
        vo.setAbovePrice(10L);
        String WeightFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/weightItems/200").header("authorization", token).contentType("application/json;charset=UTF-8").content(WeightFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 修改重量模板明细（资源不存在）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putWeightFreightModel2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        WeightFreightModelInfoVo vo = new WeightFreightModelInfoVo();
        vo.setRegionId(1000L);
        vo.setFirstWeight(10L);
        vo.setFirstWeightFreight(10L);
        vo.setTenPrice(10L);
        vo.setFiftyPrice(10L);
        vo.setHundredPrice(10L);
        vo.setTrihunPrice(10L);
        vo.setAbovePrice(10L);
        String WeightFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/weightItems/20000").header("authorization", token).contentType("application/json;charset=UTF-8").content(WeightFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 修改重量模板明细（操作的id不是自己的）（暂时通不过，后端状态码需要统一更改200->403）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putWeightFreightModel3() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        WeightFreightModelInfoVo vo = new WeightFreightModelInfoVo();
        vo.setRegionId(1000L);
        vo.setFirstWeight(10L);
        vo.setFirstWeightFreight(10L);
        vo.setTenPrice(10L);
        vo.setFiftyPrice(10L);
        vo.setHundredPrice(10L);
        vo.setTrihunPrice(10L);
        vo.setAbovePrice(10L);
        String WeightFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/weightItems/209").header("authorization", token).contentType("application/json;charset=UTF-8").content(WeightFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 修改重量模板明细（该地区已定义）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putWeightFreightModel4() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        WeightFreightModelInfoVo vo = new WeightFreightModelInfoVo();
        vo.setRegionId(200L);
        vo.setFirstWeight(10L);
        vo.setFirstWeightFreight(10L);
        vo.setTenPrice(10L);
        vo.setFiftyPrice(10L);
        vo.setHundredPrice(10L);
        vo.setTrihunPrice(10L);
        vo.setAbovePrice(10L);
        String WeightFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/weightItems/200").header("authorization", token).contentType("application/json;charset=UTF-8").content(WeightFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 修改件数模板明细（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putPieceFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 1000L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/pieceItems/203").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 修改件数模板明细（资源不存在）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putPieceFreightModel2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 1000L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/pieceItems/20000").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 修改件数模板明细（操作的id不是自己的）（暂时通不过，后端状态码需要统一更改200->403）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putPieceFreightModel3() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 1000L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/pieceItems/209").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 修改件数模板明细（该地区已定义）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void putPieceFreightModel4() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        PieceFreightModelInfoVo vo = new PieceFreightModelInfoVo();
        Long testRegionId = 200L;
        Long testAddtionalItemsPrice = 0L;
        Long testFirstItemPrice = 1L;
        vo.setRegionId(testRegionId);
        vo.setAdditionalItemsPrice(testAddtionalItemsPrice);
        vo.setFirstItemPrice(testFirstItemPrice);
        String PieceFreightModelJson = JacksonUtil.toJson(vo);
        String responseString = this.mvc.perform(put("/freight/shops/1/pieceItems/203").header("authorization", token).contentType("application/json;charset=UTF-8").content(PieceFreightModelJson))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.REGION_SAME.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 删除重量模板明细（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void delWeightFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/1/weightItems/203").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 删除重量模板明细（资源不存在）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void delWeightFreightModel2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/1/weightItems/20000").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 删除重量模板明细（操作的id不是自己的）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void delWeightFreightModel3() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/1/weightItems/209").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }


    /**
     * @Description: 删除件数模板明细（成功）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void delPieceFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/1/pieceItems/203").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.OK.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 删除件数模板明细（资源不存在）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void delPieceFreightModel2() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/1/pieceItems/20000").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_NOTEXIST.getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @Description: 删除件数模板明细（操作的id不是自己的）
     * @Param:  []
     * @return: void
     * @Author: lzn
     * @Date 2020/12/15
     */
    @Test
    public void delPieceFreightModel3() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100L, 100);
        String responseString = this.mvc.perform(delete("/freight/shops/1/pieceItems/209").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();
    }
}
