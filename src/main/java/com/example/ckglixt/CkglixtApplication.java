package com.example.ckglixt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@MapperScan({"com.example.ckglixt.*.Mapper"})
public class CkglixtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CkglixtApplication.class, args);
    }

}
