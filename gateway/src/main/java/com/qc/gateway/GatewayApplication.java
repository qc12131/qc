package com.qc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @Author: 青菜
 * @Date: 2019/11/7 下午4:45
 * @Description: 网管服务
 * @Version 1.0
 */
@EnableEurekaClient
@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
