package com.example.freight;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.example.freight.mapper")
class FreightApplicationTests {

    @Test
    void contextLoads() {
    }

}
