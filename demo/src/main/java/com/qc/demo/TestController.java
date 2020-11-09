package com.qc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 青菜
 * @Date: 2020/4/29 8:12 下午
 * @Description:
 * @Version 1.0
 */
@RestController
public class TestController {

    @Value("${test}")
    protected String test;

    @GetMapping("test")
    private String test(){
        return test;
    }
}
