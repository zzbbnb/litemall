package com.example.freight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.freight.mapper")
public class FreightApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreightApplication.class, args);
    }

}
