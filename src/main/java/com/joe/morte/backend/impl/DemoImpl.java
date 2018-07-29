package com.joe.morte.backend.impl;

//import com.alibaba.dubbo.config.annotation.Service;
import com.joe.morte.backend.api.Demo;
import org.springframework.stereotype.Service;

/**
 * Created by yiikou on 2018-07-28.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DemoImpl implements Demo {
    /*
    test: http://127.0.0.1:8080/dubboAPI/com.joe.morte.backend.api.Demo/greeting
     */
    @Override
    public String greeting(String msg) {
        return "Hello"+msg;
    }
}
