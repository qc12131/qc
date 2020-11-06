package com.qccloud.core.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 青菜
 * @Date: 2019/10/25 下午4:39
 * @Description:
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hosts")
public class HostsConfigBean {

    private boolean isDebug;
    private String domain;


}
