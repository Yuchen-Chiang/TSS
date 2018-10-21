package com.silver.tss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UESTC-SICE-108
 * Topic Selection System
 *
 * @author Yuchen_Chiang
 */
@SpringBootApplication
@MapperScan("com.silver.tss.dao")
public class TssApplication {

    public static void main(String[] args) {
        SpringApplication.run(TssApplication.class, args);
    }
}
