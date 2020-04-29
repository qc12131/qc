package com.qc.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author: 青菜
 * @Date: 2019/10/17 下午4:52
 * @Description: 路由限流配置
 * @Version 1.0
 */
@Configuration
public class RateLimiterConfiguration {

    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        // url
        //Mono.just(exchange.getRequest().getURI().getPath());
        // 用户
        // exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
        // 根据IP地址限流
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

    /**
     * 地址限流
     * */
//    @Bean("ipKeyResolver")
//    @Primary
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostString());
//
//    }


    /**
     * 用户限流
     *
     * */
//    @Bean("userKeyResolver")
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
//
//    }


    /**
     * 接口限流
     * */
//    @Bean("apiKeyResolver")
//    KeyResolver apiKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().value());
//    }


}
