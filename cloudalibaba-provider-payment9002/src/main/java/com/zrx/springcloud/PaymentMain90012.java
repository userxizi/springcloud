package com.zrx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain90012 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain90012.class,args);
    }
}
