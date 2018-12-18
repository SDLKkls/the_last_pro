package com.baizhi.cmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.cmm.mapper")
public class TheLastProApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheLastProApplication.class, args);
    }

}

