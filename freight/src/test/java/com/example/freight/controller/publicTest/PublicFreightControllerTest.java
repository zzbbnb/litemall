package com.example.freight.controller.publicTest;

import cn.edu.xmu.ooad.util.JwtHelper;
import cn.edu.xmu.ooad.util.ResponseCode;
import com.example.freight.FreightApplication;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: oomall
 * @description:
 * @author: alex101
 * @create: 2020-12-21 12:07
 **/
@AutoConfigureMockMvc
@Transactional
@SpringBootTest(classes = FreightApplication.class)   //标识本类是一个SpringBootTest
@Slf4j
public class PublicFreightControllerTest {

    @Value("${public-test.managementgate}")
    private String managementGate="localhost:8080";

    @Value("${public-test.mallgate}")
    private String mallGate="localhost:8080";

    private WebTestClient manageClient;

    private WebTestClient mallClient;


    @Before
    public void setUp(){
        System.out.println("in setup");

        this.manageClient = WebTestClient.bindToServer()
                .baseUrl("http://"+managementGate)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .build();

        this.mallClient = WebTestClient.bindToServer()
                .baseUrl("http://"+mallGate)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .build();
    }




    @Autowired
    private MockMvc mvc;

    @Test
    public void changeFreightModel1() throws Exception
    {
        String token = new JwtHelper().createToken(100L, 100l, 100);
        System.out.println(mallGate);
        String freightJson = "{\"name\": \"freightModeTest\",\"unit\": 90}";
        byte[] responseString =
                manageClient.put().uri("/shops/{shopId}/freightmodels/{id}",295,88888)
                        .header("authorization",token)
                        .bodyValue(freightJson)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody()
                        .jsonPath("$.errno").isEqualTo(ResponseCode.OK.getCode())
                        .returnResult()
                        .getResponseBodyContent();

    }



}
