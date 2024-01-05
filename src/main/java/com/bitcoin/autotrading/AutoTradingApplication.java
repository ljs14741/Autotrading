package com.bitcoin.autotrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoTradingApplication {

    /* 테스트 */
    public static void main(String[] args) {
        SpringApplication.run(AutoTradingApplication.class, args);
    }

    /*테스트*/
}