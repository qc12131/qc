package com.qccloud.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.qccloud.core.config.bean.HostsConfigBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

/**
 * @Author: 青菜
 * @Date: 2019-06-20 10:59
 * @Description: http 请求util
 * @Version 1.0
 */
@Data
@Slf4j
@Configuration
//@RefreshScope
//@ConditionalOnExpression("!'${host}'.isEmpty()")
//@ConfigurationProperties(prefix = "host")
@EnableConfigurationProperties(HostsConfigBean.class)
public class RestTemplateUtil {


    @Autowired
    RestTemplate restTemplate;


    @Autowired
    HostsConfigBean hostsConfigBean;

//    private boolean hostsConfigBean.isDebug();
//
//    private String hostsConfigBean.getDomain();

    public String doGet(String url) {
        if(hostsConfigBean.isDebug()) {
            return doDebugGet(url);
        }
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    public <T> T doGet(String url, Class<T> clazz) {
        if(hostsConfigBean.isDebug()) {
            return doDebugGet(url,clazz);
        }
        T t = restTemplate.getForObject(url, clazz);
        return t;
    }

    private <T> T doDebugGet(String url, Class<T> clazz) {
        try {
            url = hostsConfigBean.getDomain() + "/api/wechat/proxy?url=" + URLEncoder.encode(url, "utf-8");
            return doGet(url,clazz);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    private String doDebugGet(String url) {
        try {
            url = hostsConfigBean.getDomain() + "/api/wechat/proxy?url=" + URLEncoder.encode(url, "utf-8");
            return doGet(url);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public String doPost(String url, String params) {
        if(hostsConfigBean.isDebug()) {
            return doDebugPost(url, params);
        }
        String result = restTemplate.postForObject(url, params, String.class);
        return result;
    }

    public String doPost(String url, JSONObject params) {
        if(hostsConfigBean.isDebug()) {
            return doDebugPost(url, params);
        }
        String result = restTemplate.postForObject(url, params, String.class);
        return result;
    }

    public <T> T  doPost(String url, JSONObject params, Class<T> clazz) {
        if(hostsConfigBean.isDebug()) {
            return doDebugPost(url, params, clazz);
        }
        T t = restTemplate.postForObject(url, params, clazz);
        return t;
    }

    private <T> T doDebugPost(String url, JSONObject params, Class<T> clazz) {
        try {
            url = hostsConfigBean.getDomain() + "/api/wechat/proxyPost?url=" + URLEncoder.encode(url, "utf-8");
            return doPost(url, params,clazz);
        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    private String doDebugPost(String url, String params) {
        try {
            url = hostsConfigBean.getDomain() + "/api/wechat/proxyPost?url=" + URLEncoder.encode(url, "utf-8");
            return doPost(url, params);
        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
    }


    private String doDebugPost(String url, JSONObject params) {
        try {
            url = hostsConfigBean.getDomain() + "/api/wechat/proxyPost?url=" + URLEncoder.encode(url, "utf-8");
            return doPost(url, params);
        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
