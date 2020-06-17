package com.zrx.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.zrx.springcloud.dao"})
public class MyBatisConfig {
}
