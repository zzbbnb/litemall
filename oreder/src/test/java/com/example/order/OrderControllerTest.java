package com.example.order;

import cn.edu.xmu.ooad.util.JwtHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/***
 * @author yansong chen
 * @time 2020-12-14 16:59
 * @return
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(classes = OrederApplication.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void statestest()throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l,100);
        String responseString = this.mvc.perform(get("/orders/states").header("authorization", token))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }
}
