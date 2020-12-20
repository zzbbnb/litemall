package com.example.order;

import cn.edu.xmu.ooad.util.JwtHelper;
import cn.edu.xmu.ooad.util.ResponseCode;
import com.example.order.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/***
 * @author yansong chen
 * @time 2020-12-17 23:46
 * @description:
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = OrederApplication.class)
@Transactional
@Slf4j
public class OrderTestCYS {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    public void setDefaultFreightModel1() throws Exception {
        String token = new JwtHelper().createToken(1L, 0L, 100);
        String responseString = this.mvc.perform(get("/orders")
                .header("authorization", token))
                //.queryParam("orderSn","2016102364965"))
                //.andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        log.debug(responseString);
    }

/*    @Test
    public void setDefaultFreightModel2() throws Exception {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        String responseString = this.mvc.perform(post("/freight/shops/1/freight_models/1/default").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.errno").value(ResponseCode.RESOURCE_ID_OUTSCOPE.getCode()))
                .andReturn().getResponse().getContentAsString();

    }*/
}
