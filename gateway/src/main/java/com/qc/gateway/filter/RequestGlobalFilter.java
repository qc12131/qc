package com.qc.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.util.List;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 * @Author: 青菜
 * @Date: 2019/10/17 下午7:35
 * @Description: 全局拦截器，作用所有的微服务
 * 1. 对请求头中参数进行处理 from 参数进行清洗
 * 2. 重写StripPrefix = 1,支持全局
 * 支持swagger添加X-Forwarded-Prefix header  （F SR2 已经支持，不需要自己维护）
 * @Version 1.0
 */
//@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 清洗请求头中from 参数
        ServerHttpRequest request = exchange.getRequest().mutate()
                // .headers(httpHeaders -> httpHeaders.remove(SecurityConstants.FROM))
                .build();

        // 2. 重写StripPrefix
        addOriginalRequestUrl(exchange, request.getURI());
        String rawPath = request.getURI().getRawPath();
//        String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(rawPath, "/"))
//                .skip(1L).collect(Collectors.joining("/"));
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String params = "";
        List<String> reqStr = queryParams.get("reqStr");
        params = "?reqStr=" + reqStr.get(0);
        List<String> token = queryParams.get("token");
        String tokenString = "";
        String sessionString = "";
        String reqStrString = "";
        if (CollectionUtils.isEmpty(token)) {
            tokenString = token.get(0);
            params = "?reqStr=" + reqStr.get(0) + "&token=" + tokenString;
        }
        List<String> session = queryParams.get("session");
        if (CollectionUtils.isEmpty(session)) {
            sessionString = session.get(0);
            params = "?reqStr=" + reqStr.get(0) + "&session=" + sessionString;
        }
        ServerHttpRequest newRequest = request.mutate()
                .path(rawPath + URLEncoder.encode(params))
                .build();


        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());

        return chain.filter(exchange.mutate()
                .request(newRequest.mutate()
                        .build()).build());
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
