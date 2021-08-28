package com.example.kafkademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * springboot 即成Kafka日志测试类
 * @author dengyuhui
 * @since 2020/3/5
 **/
@Slf4j
@Service
public class PrintService {
    public void doPrint() {
        int num=0;
        log.info("num is :"+num++);
    }
}
