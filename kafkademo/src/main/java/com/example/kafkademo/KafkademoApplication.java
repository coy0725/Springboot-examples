package com.example.kafkademo;

import com.example.kafkademo.sender.KafkaSender;
import com.example.kafkademo.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@EnableScheduling
@SpringBootApplication
public class KafkademoApplication {
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private PrintService printService;
    public static void main(String[] args) {
        SpringApplication.run(KafkademoApplication.class, args);
    }
    //然后每隔6秒执行一次
    @Scheduled(fixedRate = 1000 * 6)
    public void testKafka() throws Exception {
        kafkaSender.sendTest();
        printService.doPrint();
    }
}
