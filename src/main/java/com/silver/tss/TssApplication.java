package com.silver.tss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * UESTC-SICE-108
 * Topic Selection System
 *
 * @author Yuchen_Chiang
 */
@SpringBootApplication
@MapperScan("com.silver.tss.dao")
@EnableSwagger2
public class TssApplication {

    public static void main(String[] args) {
        SpringApplication.run(TssApplication.class, args);
    }
}
