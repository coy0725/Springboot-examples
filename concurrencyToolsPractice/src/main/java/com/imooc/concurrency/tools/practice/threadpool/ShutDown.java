package com.imooc.concurrency.tools.practice.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述：     演示关闭线程池
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        System.out.println("isShutdown："+executorService.isShutdown());
        System.out.println("isTerminated："+executorService.isTerminated());
        Thread.sleep(1500);
        List<Runnable> runnableList = executorService.shutdownNow();

        executorService.shutdown();
        System.out.println("isShutdown："+executorService.isShutdown());
        //executorService.execute(new ShutDownTask());
        System.out.println("isTerminated："+executorService.isTerminated());
        Thread.sleep(20000);
        // 为什么这行代码不会执行呢？
        System.out.println("isTerminated："+executorService.isTerminated());
        boolean b = executorService.awaitTermination(7L, TimeUnit.SECONDS);
        System.out.println(b);
        //System.out.println(executorService.isShutdown());
        //executorService.shutdown();
        //System.out.println("isShutdown："+executorService.isShutdown());
        //System.out.println("isTerminated："+executorService.isTerminated());
        //Thread.sleep(10000);
        //System.out.println(executorService.isTerminated());
        //
        //executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
