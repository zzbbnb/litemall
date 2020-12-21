package com.example.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.order.mapper")
@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.ooad","com.example.order"})
public class OrederApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrederApplication.class, args);
    }

}
