package com.imooc.concurrency.tools.practice.lock.condition;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author coy
 * @since 2021/12/11
 **/
public class LockCondition implements Runnable {
    
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    
    public static void main(String[] args) throws InterruptedException {
        
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(new LockCondition());
    }
    
    
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("waiting...");
        lock.lock();
        condition.await();
    }
}
