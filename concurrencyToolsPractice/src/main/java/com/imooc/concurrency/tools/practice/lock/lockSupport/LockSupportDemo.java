package com.imooc.concurrency.tools.practice.lock.lockSupport;

import java.util.concurrent.locks.LockSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * @author coy
 * @since 2021/8/31
 **/
@Slf4j
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        //unPark();
        //interrupt();
        parkNacos();
    }

    private static void parkNacos() {
        log.info("parkNacos  start");
        Thread thread = new Thread(()->{
            log.info("child thread begin park");
            LockSupport.parkNanos(10000);
            log.info("child thread unPark");
        });
        thread.start();

        log.info("parkNacos  end\n");
    }

    private static void unPark() throws InterruptedException {
        log.info("unPark  start");
        Thread thread = new Thread(()->{
            log.info("child thread begin park");
            LockSupport.park();
            log.info("child thread unPark");
        });

        thread.start();
        Thread.sleep(1000);
        log.info("main thread  unPark thread");
        LockSupport.unpark(thread);
        log.info("unPark  end\n");
    }


    private static void interrupt() throws InterruptedException {

        log.info("interrupt  start");
        Thread thread = new Thread(()->{
            System.out.println("child thread begin park");
            LockSupport.park();
            System.out.println("child thread unPark");
        });

        thread.start();
        Thread.sleep(1000);
        log.info("main thread  unPark thread");
        thread.interrupt();

        log.info("interrupt  end\n");
    }
}
