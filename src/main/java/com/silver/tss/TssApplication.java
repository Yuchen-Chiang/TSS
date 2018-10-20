package com.silver.tss;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TssApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TssApplication.class, args);
    }
}
