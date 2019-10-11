package com.jmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author jumping-張文平
 * @Date 2019/10/11 17:14
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.jmall.mapper")
public class ItemApplcation {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplcation.class,args);
    }
}
